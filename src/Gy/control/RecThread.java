package Gy.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import Gy.business.MessageFactory;
import Gy.business.ReceiveMessage;
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class RecThread implements Runnable {
	Socket sc;
	String strread = ""; 
	String recmessage = "";
	STATUS status= STATUS.idle;
	int waitTime = 5;  //�����̵߳ȴ�5��
	public Queue<ReceiveMessage> receiveMsgQueue =new LinkedList<ReceiveMessage>();  //��Ϣ����
	public String getRecMessage() {
//		System.err.println();
		return recmessage;
	}

	public void setSocket(Socket sc) {
		this.sc = sc;
	}
	private void addreceiveMsgQueue(List<String> msglist){
		for (int i = 0; i < msglist.size(); i++) {
			receiveMsgQueue.add(new ReceiveMessage(msglist.get(i)));
		}
	}
	@Override
	public void run() {
		status=STATUS.runing;
		int i = 0;
		System.err.println("rec:"+sc);
		//״̬�� starting,runing,finished,idle;
		while (status==STATUS.runing ) {
			System.err.println("��������-------------------"+i);
			Controlor.sleep(1000);
			i++;
			/*****
			 * ����һ ÿ�ζ�ȡһ���ֽ� try { InputStream is = sc.getInputStream(); if
			 * (!sc.isConnected()) { System.err.println("����ʧЧ "); continue; }
			 * int intcount = is.available();
			 * System.err.println("rc is running"+intcount); while (intcount>0)
			 * { System.err.println("read="+ getHexString(is.read())); }
			 * Controlor.sleep(1000); } catch (IOException e) {
			 * e.printStackTrace(); }
			 **/

			/*** �ڶ��ַ��� �ӻ����� * */
			InputStream is=null;
			try {
				is = sc.getInputStream();
				System.err.println("is:"+is);				
				int intcount = is.available();
				System.err.println("���յ��ĸ���:" + intcount);
				while (intcount > 0) {
//					if (recmessage.length()>100000) {
//						recmessage=""; 	//����һǧ���ַ���� 
//						System.err.println("��� ��������");
//					}
					String tmpmessage = "";
					byte[] buf = new byte[intcount];
					is.read(buf);
					tmpmessage = getHexString(buf);
					System.err.println("���ս��:" + tmpmessage);
					recmessage = recmessage + tmpmessage;    //��ֹ������Ϣ����һ����� 
					recmessage = MessageFactory.createRecMessage(recmessage, receiveMsgQueue); 
					intcount = is.available(); //�����ֽ���
					System.err.println("������Ђ���:"+receiveMsgQueue.size());
					System.err.println("recmessage:"+recmessage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}  				
		}
//		status = STATUS.finished;
		//��ɺ�����״̬Ϊfinished
		status =STATUS.finished;
		System.err.println("�������"); 
	}

	/**
	 * ����ʮ�������ַ�
	 * ***/
	public String getHexString(int b) {
		String result = Integer.toHexString(b);
		if (result.length() == 1) {
			result = "0" + result;
		} else if (result.length() > 2) {
			result = result.substring(result.length() - 2);
		}
		return result;
	}

	public String getHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			String tmp = getHexString((b[i]));

			result = result + getHexString((b[i]));
			// System.err.println(result);
		}
		return result;

	}
 
	public static void main(String[] args) {
//		int b = -126;
//		System.err.println(Integer.toHexString(-56));
//		String result = "123456";
//		result = result.substring(result.length() - 2);
//		System.err.println(result);
		  Queue<ReceiveMessage> tt =new LinkedList<ReceiveMessage>();
		  Queue<ReceiveMessage> tt2 =new LinkedList<ReceiveMessage>();
//		  tt.add(new ReceiveMessage("1"));
		  tt.add(new ReceiveMessage("2a"));
		  tt.add(new ReceiveMessage("23"));
		  tt.add(new ReceiveMessage("24"));
		  tt.add(new ReceiveMessage("25"));
		  tt2.addAll(tt);
		  System.err.println(tt2.remove().getMsgContent());
		
	}
}
