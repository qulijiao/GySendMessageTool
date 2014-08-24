package Gy.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
	  
	private Socket sc;
	SendThread sendthread;
	RecThread recthread; 
	GPSThread gpsthread;
//	private boolean isRegedit = false;
//	private boolean isAuthentication = false;
	
	public Controlor(){ 
		ui = new TabUI();  
		sendthread = new SendThread();
		recthread  = new RecThread();
		gpsthread  = new GPSThread(0);
	}
	@Override
	public void run(){  		
		while (true){
//			System.err.println(ui.isRunning);
			if (ui.isRunning) {
				ui.startSending( );
				if(!createSocket(ui.getIP(), ui.getPort())){
					System.err.println("连接失效 ");
				};
				//0.插入消息
				setMessage();
				//1.判断发送任务是否完成 
				startSendTask( );
				//2.判断接收任务是否完成
				startReceiveTask();	
				startGPSTask();  //开启gps定时汇报任务				
//				if (sendthread.status==STATUS.finished && recthread.status==STATUS.finished) {
				flashResutl(recthread.receiveMsgQueue);
				dealAnsweredMsg();
				if (sendthread.threadstatus==STATUS.finished ) {
					ui.finishSending(); 
					sendthread.threadstatus=STATUS.idle;  
				}
//				System.err.println("---------------------mainthread running---------------------");
			}
			sleep(2000);
		} 
	}
	
	private  void  dealAnsweredMsg(){
		List<Message> listrecMessage = new ArrayList<Message>();
//		listrecMessage.addAll(recthread.receiveMsgQueue ) ;
		for( Message recmsg :recthread.receiveMsgQueue  ){
			System.err.println("dealAnsweredMsg:"+recmsg.getMsgContent());
			if (recmsg.msgid.equals( "8001") ) {
				System.err.println("recmsg.msgid:"+recmsg.msgid);
				if(sendthread.isMsgSendSuccesessed(recmsg.getMsgContent())){
					System.err.println("answerMsg移除消息:"+recmsg.getMsgContent());					
//					recthread.receiveMsgQueue.remove(recmsg);
					listrecMessage.add(recmsg);
				}
			}
		}
		recthread.receiveMsgQueue.removeAll(listrecMessage );
	}
	
	//定时从界面取消息发送
	private void setMessage(){
		//添加发送队列
//		MessageFactory.createMessageQueue(ui.getStrSendingMSG(), sendthread.sendingMsgQueue, 0);
		sendthread.sendingMsgQueue.addAll(MessageFactory.getMesssageList(ui.getStrSendingMSG()));
		//获取剩下的字符串
		ui.setStrSendingMSG(MessageFactory.getLeftMsgString(ui.getStrSendingMSG()));
	}
	
	private void startGPSTask() {
		if (ui.isSendGPSMsg()) {			
			if (gpsthread.status == STATUS.idle) {
				System.err.println("GPS任务:" ); 
				gpsthread.setGPSsendFrequency(ui.getGPSsendFrequency());
				new Thread(gpsthread).start();
				gpsthread.status = STATUS.starting;
			}
		}else {
			System.err.println("停止gps發送");
			gpsthread.status = STATUS.idle;
		}
		}
	
	private void startReceiveTask() {
		System.err.println("接收任务:" + recthread.status);
		if (recthread.status == STATUS.idle) {
			// 开始任务 
			recthread.setSocket(sc);
//			sendthread.setSendCount(ui.getSendCount()); //设置发送次数
			recthread.status = STATUS.starting;
			new Thread(recthread).start();
		}
	}

	
	private void startSendTask() {
		// 空闲才执行启动任务
//		System.err.println("发送任务startSendTask:" + sendthread.status);
		if (sendthread.threadstatus == STATUS.idle) {
			// 开始任务
			sendthread.setSocket(sc);
//			sendthread.setSendCount(ui.getSendCount()); //设置发送次数
			//设置消息内容
//			sendthread.addSendingMessages(MessageFactory.readStringMsg(ui.getStrSendingMSG()));
			//设置线程启动 
			sendthread.threadstatus = STATUS.starting;
			new Thread(sendthread).start();
		}
	}
 
	/**
	 *延时函数
	 * */
	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建socket连接，如果连接中断则重新连接
	 * */
	private boolean createSocket(String strip, int port) {
		boolean isCreateSuccess = false;
//		System.err.println("sc:"+sc);
//		System.err.println("判定是否连接已断开:"+sc.isClosed());
		if (sc == null || sc.isConnected() == false|| sc.isClosed() == true) {
			System.err.println("创建连接------------------");
			try {
				sc = new Socket(strip, port);
//				sc = new Socket("www.baidu.com", 80);
				sendthread.setSocket(sc);
//				System.err.println("创建连接完成" + sc);
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
//		System.err.println("当前sc:"+sc);
		return isCreateSuccess;
	}
	/**
	 * 刷新界面 把接收信息发送给界面
	 * 
	private void flashResutl(String strresult) {
		String strResultTrainsed ="";
		System.err.println("接收内容:"+strresult.length());
		for ( Message msg : MessageFactory.getMesssageList(strresult)  ) {
//			System.err.println("分析接收结果:");
			strResultTrainsed =strResultTrainsed+ msg.getMsgContent() +"\n";
		} 
		ui.setStrReceiveMSG(strResultTrainsed); // 更新接收结果 
		ui.reflashUI(); // 刷新界面 分两种情况 1.待发送 2.发送中
	} 
	*/
	/**
	 * 刷新界面 把接收信息发送给界面 
	 * */
	private void flashResutl(Queue<Message> recMsgQueue){
		String strResultTrainsed ="";
		for (Message recmsg:  recMsgQueue) {
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