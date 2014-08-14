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
import Gy.business.MessageFactory;
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

		 
	}

	private void startReceiveTask() {
		System.err.println("��������:" + recthread.status);
		if (recthread.status == STATUS.idle) {
			// ��ʼ����
			recthread.setSocket(sc);
			 sendthread.setSendCount(ui.getSendCount()); //���÷��ʹ���
			recthread.status = STATUS.starting;
			new Thread(recthread).start();
		}
	}

	private void startSendTask() {
		// ���в�ִ����������
		System.err.println("��������startSendTask:" + sendthread.status);
		if (sendthread.status == STATUS.idle) {
			// ��ʼ����
			sendthread.setSocket(sc);
			sendthread.setSendCount(ui.getSendCount()); //���÷��ʹ���
			//������Ϣ����
			sendthread.setMessage(MessageFactory.readMessages(ui.getMessage()));
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
		if (sc == null || sc.isConnected() == false||sc.isClosed() == true) {
			try {
				sc = new Socket(strip, port);
				System.err.println("�����������" + sc);
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
	public static void main(String[] args) {
		System.err.println("start");
		Controlor cl = new Controlor();
		// cl.createSocket("115.29.198.101", 8988);
		new Thread(cl).start(); 

	}

}


//02010100013055773110010039007e ../../public/Log.cpp(452) 2161358592