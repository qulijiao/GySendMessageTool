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
	public MessageStatus.STATUS status;
	public int sendcount = 0;    //���ʹ���
	public long sendTime;		  //�ϴη���ʱ��	
	public static int serialnum = 99; //��ˮID 
	public String curserial ;
	public String msgid ;
	public Message(String strmessage) {

		status =STATUS.newmsg; 
		this.strMsg =setMsgSerialnum(strmessage);
		this.strMsg =getCheckOut( this.strMsg); //����У���� 
		if (strmessage.length()>26) {
			msgid =  strMsg.substring(2, 6); 
			curserial =   strMsg.substring(22, 26);		
		}		
	}
	//��ȡ��Ϣ����
	public String getMsgContent() {
		return this.strMsg;
	}

	public static String setMsgSerialnum(String strmessage) {
		String strResult = "";
		if (strmessage.length() < 28) {
//			strResult = "";
			return strmessage;
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
//		System.err.println(strResult);
		return strResult;
	}
 
	/**
	 * ������Ϣ����״̬ �������ѷ��ʹ�����״̬����¼����ʱ��
	 * */
	@Override
	public void setSended() {
		sendcount++;
		status = MessageStatus.STATUS.sended;
		sendTime = System.currentTimeMillis();

	}
	/**
	 * �Ƿ������ 
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
	
	public static String getCheckOut(String  strmsg) {
		if (strmsg.length()<28) {
			return strmsg;
		}
		byte[] msg = Global.HexString2Bytes(strmsg);
		int verify = 0; 
		verify = msg[1] ^ msg[2]; //��ʼ����7e���濪ʼ
		// ����Ϣͷ����Ϣ�������򣬵ó�У����
		for(int j = 3; j < msg.length -2  ; j++) {
			verify = verify ^ msg[j];
		}
		int result = (int)(verify & 0xff);   //ͨ��������ʹת����ʱ��ֻ�����1��2λ�ַ�
		String strResult =Integer.toHexString(result);  //�ó�У�����ַ���
		if (strResult.length()<2) {
			strResult = "0"+strResult;
		}		
		strResult = strmsg.substring(0, strmsg.length()-4) + strResult+"7e";
		return strResult;
	}
	public static void main(String[] args) { 
////		System.err.println(Integer.toHexString(65535));
//		System.err.println(new Message("7e111111111111111111111111111111111117e").getMsgContent());
//		System.err.println(new Message("7e111111111111111111111111111111111117e").getMsgContent());
//		System.err.println(new Message("7e111111111111111111111111111111111117e").getMsgContent());
////		msgid = Integer.valueOf( strMsg.substring(2, 6));
//		System.err.println("7e111111111111111111110003111111111037e".substring(22, 26));
		System.err.println(new Message("7e0704-----"));
	}

}
