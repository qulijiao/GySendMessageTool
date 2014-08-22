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
	  
	private Socket sc;
	SendThread sendthread;
	RecThread recthread; 
	GPSThread gpsthread;
	private boolean isRegedit = false;
	private boolean isAuthentication = false;
	
	public Controlor(){ 
		ui = new TabUI(); 
//		System.err.println(ui);
		sendthread = new SendThread();
		recthread = new RecThread();
		gpsthread = new GPSThread(0);
	}
	@Override
	public void run(){

		
		while (true){
//			System.err.println(ui.isRunning);
			if (ui.isRunning) {
				ui.startSending( );
//				System.err.println("ˢ�°�ť���");
				if(!createSocket(ui.getIP(), ui.getPort())){
					System.err.println("����ʧЧ ");
				};
				//0.������Ϣ
				setMessage();
				//1.�жϷ��������Ƿ���� 
				startSendTask();
				//2.�жϽ��������Ƿ����
				startReceiveTask();	
				//3.����gps��ʱ�㱨����
				startGPSTask();
				//4.ˢ�½����� 
				flashResutl(recthread.receiveMsgQueue);
				if (sendthread.status==STATUS.finished ) {
					ui.finishSending(); 
					sendthread.status=STATUS.idle;  
				}
			}
			sleep(2000);
		} 
	}
	private void setMessage(){
		String laststrMsg =MessageFactory.createMessageQueue(ui.getStrSendingMSG(), sendthread.sendingMsgQueue, 0);
		ui.setStrSendingMSG(laststrMsg);
	}
	
	private void startGPSTask() {
		if (ui.isSendGPSMsg()&& ui.isRunning) {			
			if (gpsthread.status == STATUS.idle) {
				System.err.println("GPS����:" ); 
				gpsthread.setGPSsendFrequency(ui.getGPSsendFrequency());
				new Thread(gpsthread).start();
				gpsthread.status = STATUS.starting;
			}
		}else {
			System.err.println("ֹͣgps�l��");
			gpsthread.status = STATUS.idle;
		}
	}
	
	private void startReceiveTask() {
		System.err.println("��������:" + recthread.status);
		if (recthread.status == STATUS.idle) {
			// ��ʼ����
			recthread.setSocket(sc);
//			sendthread.setSendCount(ui.getSendCount()); //���÷��ʹ���
			recthread.status = STATUS.starting;
			new Thread(recthread).start();
		}
	}

	
	private void startSendTask() {
		// ���в�ִ����������
//		System.err.println("��������startSendTask:" + sendthread.status);
		if (sendthread.status == STATUS.idle) {
			// ��ʼ����
			sendthread.setSocket(sc);			 
			//������Ϣ���� �ж�ʱ�߳��ڴ����� ���ﲻ��Ҫ
//			sendthread.setSendingMessage(MessageFactory.readStringMsg(ui.getStrSendingMSG()));
			//�����߳����� 
			sendthread.status = STATUS.starting;
			new Thread(sendthread).start();
		}
	}
 
	/**
	 *��ʱ����
	 * */
	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����socket���ӣ���������ж�����������
	 * */
	private boolean createSocket(String strip, int port) {
		boolean isCreateSuccess = false;
//		System.err.println("sc:"+sc);
//		System.err.println("�ж��Ƿ������ѶϿ�:"+sc.isClosed());
		if (sc == null || sc.isConnected() == false|| sc.isClosed() == true) {
			System.err.println("��������------------------");
			try {
				sc = new Socket(strip, port);
//				sc = new Socket("www.baidu.com", 80);
				sendthread.setSocket(sc);
				System.err.println("�����������" + sc);
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
//		System.err.println("��ǰsc:"+sc);
		return isCreateSuccess;
	}
	/**
	 * ˢ�½��� �ѽ�����Ϣ���͸�����
	 */
	private void flashResutl(String strresult) {
		String strResultTrainsed ="";
		System.err.println("��������:"+strresult.length());
		for ( String mes : MessageFactory.readStringMsg(strresult)  ) {
//			System.err.println("�������ս��:");
			strResultTrainsed =strResultTrainsed+ mes +"\n";
		} 
		ui.setStrReceiveMSG(strResultTrainsed); // ���½��ս��
//		ui.isRunning = false; // ���÷��ͽ���
		ui.reflashUI(); // ˢ�½��� ��������� 1.������ 2.������
	}
	/**
	 * ˢ�½��� �ѽ�����Ϣ���͸�����
	 **/
	private void flashResutl(Queue<ReceiveMessage> recMsgQueue){
		String strResultTrainsed ="";
		for (ReceiveMessage recmsg:  recMsgQueue) {
			strResultTrainsed =strResultTrainsed+ recmsg.getMsgContent() +"\n";			
		}
		ui.setStrReceiveMSG(strResultTrainsed); // ���½��ս�� 
		ui.reflashUI(); // ˢ�½��� ��������� 1.������ 2.������
	}




	// --------------------------------------------------------------------
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.err.println("------------------------start------------------------");
//		Socket sc = new Socket("115.29.198.101",8988);
//		Socket sc = new Socket("www.baidu.com", 80);
		Controlor cl = new Controlor(); 
		// cl.createSocket("115.29.198.101", 8988);
		new Thread(cl).start(); 
		
	}

}


//02010100013055773110010039007e ../../public/Log.cpp(452) 2161358592