package Gy.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Gy.UI.UI;
import Gy.business.Message;
import Gy.control.Global.STATUS;

public class Controlor implements Runnable {
	private UI ui;
	private Message gymessage;
	private Socket sc;
	SendThread sendthread;
	RecThread recthread; 
	

	public Controlor() {
		System.err.println("new cl");
		gymessage = new Message();
		ui = new UI();
		sendthread = new SendThread();
		recthread = new RecThread();
	}

	@Override
	public void run() {
		while (true) {
			if (ui.isRunning) {
				ui.startSending();
				createSocket(ui.getIP(), ui.getPort());
				//1.判断发送任务是否完成 
				startSendTask();
				//2.判断接收任务是否完成
				startReceiveTask();	
//				if (sendthread.status==STATUS.finished && recthread.status==STATUS.finished) {
				flashResutl(recthread.getRecMessage());
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
			 sendthread.setSendCount(ui.getSendCount()); //设置发送次数
			recthread.status = STATUS.starting;
			new Thread(recthread).start();
		}
	}

	private void startSendTask() {
		// 空闲才执行启动任务
		System.err.println("发送任务startSendTask:" + sendthread.status);
		if (sendthread.status == STATUS.idle) {
			// 开始任务
			sendthread.setSocket(sc);
			sendthread.setSendCount(ui.getSendCount()); //设置发送次数
//			sendthread.setSendCount(10);
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
		if (sc == null || sc.isConnected() == false) {
			try {
				System.err.println("创建连接完成" + sc);
				sc = new Socket(strip, port);
			} catch (UnknownHostException e) {
				return isCreateSuccess;
			} catch (IOException e) {
				return isCreateSuccess;
			}
		}
		isCreateSuccess = sc.isConnected();
		return isCreateSuccess;
	}

	public boolean sendMessages() {
		String strmessgage = Message.getMessage(ui.getMessage());
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
		ui.areaResult.setText(strresult); // 更新接收结果
//		ui.isRunning = false; // 设置发送结束
		ui.reflashUI(); // 刷新界面 分两种情况 1.待发送 2.发送中
	}





	// --------------------------------------------------------------------
	public static void main(String[] args) {
		Controlor cl = new Controlor();
		// cl.createSocket("115.29.198.101", 8988);
		new Thread(cl).start();
		// SendThread st = new SendThread();
		// st.setSocket(cl.sc);
		// st.setThreadStatus(true);
		// new Thread(st).start();
		//
		// RecThread rc = new RecThread();
		// rc.setSocket(cl.sc);
		// rc.setThreadStatus(true);
		// new Thread(rc).start();

	}

}
