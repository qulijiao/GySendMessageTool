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
import java.util.LinkedList;
import java.util.Queue;

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

	@Override
	public void run() {
		status=STATUS.runing;
		int i = 0;
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
				int intcount = is.available();
				while (intcount > 0) {
					if (recmessage.length()>100000) {
						recmessage=""; 	//超过一千个字符清空 
						System.err.println("清除 接收内容");
					}
					String tmpmessage = "";
					System.err.println("接收到的个数:" + intcount);
					byte[] buf = new byte[intcount];
					is.read(buf);
					tmpmessage = getHexString(buf);
					System.err.println("接收结果:" + tmpmessage);
					recmessage = recmessage + tmpmessage;
					intcount = is.available(); //接收字节数
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
//				try {
//					is.close();
//				} catch (IOException e) { 
//					e.printStackTrace();
//				}
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
		int b = -126;
		System.err.println(Integer.toHexString(-56));
		String result = "123456";
		result = result.substring(result.length() - 2);
		System.err.println(result);
	}
}
