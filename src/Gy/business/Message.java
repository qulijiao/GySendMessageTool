package Gy.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.OutputStream;
import java.net.UnknownHostException;

import Gy.business.Inft.ReceiveIntf;
import Gy.business.Inft.SendIntf;
import Gy.business.MessageStatus.SENDMSGSTATUS;
import Gy.business.MessageStatus.STATUS;
import Gy.control.Global;

public class Message implements SendIntf, ReceiveIntf {
	private String strMsg; 
	private MessageStatus.STATUS status;
	int sendcount = 0;    //发送次数
	long sendTime;		  //上次发送时间	
	private static int serialnum = 0; //流水ID 

	public Message(String strMsg) {
		status =STATUS.newmsg;
		this.strMsg = strMsg;
	}
	//获取消息内容
	public String getMsgContent() {
		return strMsg;
	}

	public static String getMessage(String strmessage) {
		String strResult = "";
		if (strmessage.length() < 30) {
			strResult = "";
		}
		serialnum++;
		if (serialnum > 65535) {
			serialnum = 0;
		}
		String strSerialnum = Integer.toHexString(serialnum);
		int intlength = strSerialnum.length();
		if (intlength < 4) {
			for (int i = 0; i < 4 - intlength; i++) {
				strSerialnum = "0" + strSerialnum;
			}
		}
		strResult = strmessage.substring(0, 22) + strSerialnum
				+ strmessage.substring(26);
		return strResult;
	}
 
	/**
	 * 设置消息发送状态 如设置已发送次数、状态、记录发送时间
	 * */
	@Override
	public void setSended() {
		sendcount++;
		status = MessageStatus.STATUS.sended;
		sendTime = System.currentTimeMillis();

	}
	/**
	 * 是否发送完成 
	 * */
	@Override
	public boolean isSended() {
		// newmsg,sengding,sended,sendsuccessed,recieved,recAnalysising,recanswering,recremoving,finished;
		switch (status) {
		case sended:
			return true;
		case sendsuccessed:
			return true;
		case finished:
			return true;			
		default:
			return false;
		} 
	}

	public static void main(String[] args) {
		Message t = new Message(""); 
		System.err.println(t.isSended());
	}

}
