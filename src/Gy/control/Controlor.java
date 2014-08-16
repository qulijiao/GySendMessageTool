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

import Gy.UI.UI;
import Gy.business.Message;
import Gy.business.MessageFactory;
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class Controlor implements Runnable {
	private UI ui; 
	
	public Queue<Message> receiveMsg =new LinkedList<Message>(); 
//	private Message gymessage;
	private Socket sc;
	SendThread sendthread;
	RecThread recthread; 
	private boolean isRegedit = false;
	private boolean isAuthentication = false;
	
	public Controlor() { 
		ui = new UI();
		
		sendthread = new SendThread();
		recthread = new RecThread();
	}

	@Override
	public void run() {
		
	
		while (true) {
			System.err.println("-----------------------------------------------");
			if(!createSocket(ui.getIP(), ui.getPort())){
				System.err.println("����ʧЧ ");
			};	
			startSendTask();
			sleep(1000);
			
		}
		/*
		while (true) { 
			if (ui.isRunning) {
				ui.startSending( );
				if(!createSocket(ui.getIP(), ui.getPort())){
					System.err.println("����ʧЧ ");
				};
				//1.�жϷ��������Ƿ���� 
				startSendTask();
				//2.�жϽ��������Ƿ����
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
		*/
		 
	}

	private void startReceiveTask() {
		System.err.println("��������:" + recthread.status);
		if (recthread.status == STATUS.idle) {
			// ��ʼ����
			recthread.setSocket(sc);
//			 sendthread.setSendCount(ui.getSendCount()); //���÷��ʹ���
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
			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139017e"));
			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139027e"));
			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139037e"));
			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139047e"));
			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139057e"));
			sendthread.setSendingMessage(new SendMessage("7e01020001013055773110000139067e"));
//			sendthread.setSendCount(ui.getSendCount()); //���÷��ʹ���
			//������Ϣ����
			sendthread.setSendingMessage(MessageFactory.readMessages(ui.getMessage()));
			//�����߳����� 
			sendthread.status = STATUS.starting;
			new Thread(sendthread).start();
		}
	}

	// ��ʱ����
	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean createSocket(String strip, int port) {
		boolean isCreateSuccess = false;
//		System.err.println("�ж��Ƿ������ѶϿ�:"+sc.isClosed());
		if (sc == null || sc.isConnected() == false|| sc.isClosed() == true) {
//			System.err.println("��������------------------");
			try {
//				sc = new Socket(strip, port);
				sc = new Socket("www.baidu.com", 80);
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
		String strResultTrainsed ="";
		System.err.println("���ý��:"+strresult.length());
		for ( String mes : MessageFactory.readMessages(strresult)  ) {
//			System.err.println("�������ս��:");
			strResultTrainsed =strResultTrainsed+ mes +"\n";
		}
//		System.err.println("strResultTrainsed:"+strResultTrainsed);
		ui.areaResult.setText(strResultTrainsed); // ���½��ս��
//		ui.isRunning = false; // ���÷��ͽ���
		ui.reflashUI(); // ˢ�½��� ��������� 1.������ 2.������
	}





	// --------------------------------------------------------------------
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.err.println("start");
//		Socket sc = new Socket("115.29.198.101",8988);
		Socket sc = new Socket("www.baidu.com", 80);
		Controlor cl = new Controlor();
		cl.sc = sc;
		// cl.createSocket("115.29.198.101", 8988);
		new Thread(cl).start(); 
		
	}

}


//02010100013055773110010039007e ../../public/Log.cpp(452) 2161358592