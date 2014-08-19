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
	int waitTime = 5;  //接收线程等待5秒
	public Queue<ReceiveMessage> receiveMsgQueue =new LinkedList<ReceiveMessage>();  //消息队列
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
		//状态： starting,runing,finished,idle;
		while (status==STATUS.runing ) {
			System.err.println("接收任务-------------------"+i);
			Controlor.sleep(1000);
			i++;
			/*****
			 * 方法一 每次读取一个字节 try { InputStream is = sc.getInputStream(); if
			 * (!sc.isConnected()) { System.err.println("连接失效 "); continue; }
			 * int intcount = is.available();
			 * System.err.println("rc is running"+intcount); while (intcount>0)
			 * { System.err.println("read="+ getHexString(is.read())); }
			 * Controlor.sleep(1000); } catch (IOException e) {
			 * e.printStackTrace(); }
			 **/

			/*** 第二种方法 加缓冲区 * */
			InputStream is=null;
			try {
				is = sc.getInputStream();
				System.err.println("is:"+is);				
				int intcount = is.available();
				System.err.println("接收到的个数:" + intcount);
				while (intcount > 0) {
//					if (recmessage.length()>100000) {
//						recmessage=""; 	//超过一千个字符清空 
//						System.err.println("清除 接收内容");
//					}
					String tmpmessage = "";
					byte[] buf = new byte[intcount];
					is.read(buf);
					tmpmessage = getHexString(buf);
					System.err.println("接收结果:" + tmpmessage);
					recmessage = recmessage + tmpmessage;    //防止出现消息接收一半情况 
					recmessage = MessageFactory.createRecMessage(recmessage, receiveMsgQueue); 
					intcount = is.available(); //接收字节数
					System.err.println("接收隊列個數:"+receiveMsgQueue.size());
					System.err.println("recmessage:"+recmessage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}  				
		}
//		status = STATUS.finished;
		//完成后设置状态为finished
		status =STATUS.finished;
		System.err.println("接收完成"); 
	}

	/**
	 * 返回十六进制字符
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
