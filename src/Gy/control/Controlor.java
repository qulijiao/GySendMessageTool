package Gy.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;

import Gy.UI.TabUI;
import Gy.UI.UI;
import Gy.business.Message;
import Gy.business.MessageFactory;
import Gy.business.ReceiveMessage;
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class Controlor implements Runnable {
	private TabUI ui; 
	
	public Queue<Message> receiveMsg =new LinkedList<Message>(); 
//	private Message gymessage;
	private Socket sc;
	SendThread sendthread;
	RecThread recthread; 
	private boolean isRegedit = false;
	private boolean isAuthentication = false;
	
	public Controlor(){ 
		ui = new TabUI(); 
		System.err.println(ui);
		sendthread = new SendThread();
		recthread = new RecThread();
	}
	@Override
	public void run(){  
			/* test:
			System.err.println("-----------------------------------------------");
			if(!createSocket(ui.getIP(), ui.getPort())){
				System.err.println("连接失效 ");
			};	
			startSendTask();
			sleep(1000);
			}
			*/
		
		
		while (true){
//			System.err.println(ui.isRunning);
			if (ui.isRunning) {
				ui.startSending( );
//				System.err.println("刷新按钮完成");
				if(!createSocket(ui.getIP(), ui.getPort())){
					System.err.println("连接失效 ");
				};
				//1.判断发送任务是否完成 
				startSendTask();
				//2.判断接收任务是否完成
				startReceiveTask();	
//				if (sendthread.status==STATUS.finished && recthread.status==STATUS.finished) {
				flashResutl(recthread.receiveMsgQueue);
				if (sendthread.status==STATUS.finished ) {
					ui.finishSending(); 
					sendthread.status=STATUS.idle;  
				}
//				System.err.println("---------------------mainthread running---------------------");
			}
			sleep(1000);
		} 
	}

	private void startReceiveTask() {
		System.err.println("接收任务:" + recthread.status);
		if (recthread.status == STATUS.idle) {
			// 开始任务
			recthread.setSocket(sc);
//			 sendthread.setSendCount(ui.getSendCount()); //设置发送次数
			recthread.status = STATUS.starting;
			new Thread(recthread).start();
		}
	}

	private void startSendTask() {
		// 空闲才执行启动任务
//		System.err.println("发送任务startSendTask:" + sendthread.status);
		if (sendthread.status == STATUS.idle) {
			// 开始任务
			sendthread.setSocket(sc);
			sendthread.setSendingMessage(new SendMessage(ui.getStrSendingMSG()));
			sendthread.setSendingMessage(new SendMessage(ui.getStrSendingMSG()));
			sendthread.setSendingMessage(new SendMessage(ui.getStrSendingMSG()));
			sendthread.setSendingMessage(new SendMessage(ui.getStrSendingMSG()));
//			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139017e"));
//			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139027e"));
//			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139037e"));
//			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139047e"));
//			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139057e"));
//			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139067e"));
//			sendthread.setSendCount(ui.getSendCount()); //设置发送次数
			//设置消息内容
			sendthread.setSendingMessage(MessageFactory.readStringMsg(ui.getStrSendingMSG()));
			//设置线程启动 
			sendthread.status = STATUS.starting;
			new Thread(sendthread).start();
		}
	}

	// 延时函数
	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean createSocket(String strip, int port) {
		boolean isCreateSuccess = false;
//		System.err.println("sc:"+sc);
//		System.err.println("判定是否连接已断开:"+sc.isClosed());
		if (sc == null || sc.isConnected() == false|| sc.isClosed() == true) {
			System.err.println("创建连接------------------");
			try {
				sc = new Socket(strip, port);
//				sc = new Socket("www.baidu.com", 80);
				sendthread.setSocket(sc);
				System.err.println("创建连接完成" + sc);
			} catch (UnknownHostException e) {
//				System.err.println();
				e.printStackTrace();
				return isCreateSuccess;
			} catch (IOException e) {
				e.printStackTrace();
				return isCreateSuccess;
			}
		}
		isCreateSuccess = sc.isConnected();
		System.err.println("当前sc:"+sc);
		return isCreateSuccess;
	}

	public boolean sendMessages() {
		String strmessgage = Message.getMessage(ui.getStrSendingMSG());
		byte[] bytemessage = Global.HexString2Bytes(strmessgage);
		OutputStream os;
		int i = 0;
		try {
			os = sc.getOutputStream();
			PrintWriter out = new PrintWriter(sc.getOutputStream());
			os.write(bytemessage);
			os.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// finally {
		// System.err.println("finally closed Socket");
		// try {
		// sc.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		return true;
	}

	public void flashResutl(String strresult) {
		String strResultTrainsed ="";
		System.err.println("接收内容:"+strresult.length());
		for ( String mes : MessageFactory.readStringMsg(strresult)  ) {
//			System.err.println("分析接收结果:");
			strResultTrainsed =strResultTrainsed+ mes +"\n";
		} 		
		ui.setStrReceiveMSG(strResultTrainsed); // 更新接收结果
//		ui.isRunning = false; // 设置发送结束
		ui.reflashUI(); // 刷新界面 分两种情况 1.待发送 2.发送中
	}

	public void flashResutl(Queue<ReceiveMessage> recMsgQueue){
		String strResultTrainsed ="";
		for (ReceiveMessage recmsg:  recMsgQueue) {
			strResultTrainsed =strResultTrainsed+ recmsg.getMsgContent() +"\n";			
		}
		ui.setStrReceiveMSG(strResultTrainsed); // 更新接收结果 
		ui.reflashUI(); // 刷新界面 分两种情况 1.待发送 2.发送中
	}




	// --------------------------------------------------------------------
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.err.println("start");
//		Socket sc = new Socket("115.29.198.101",8988);
//		Socket sc = new Socket("www.baidu.com", 80);
		Controlor cl = new Controlor();
//		cl.sc = sc;
		// cl.createSocket("115.29.198.101", 8988);
		new Thread(cl).start(); 
		
	}

}


//02010100013055773110010039007e ../../public/Log.cpp(452) 2161358592