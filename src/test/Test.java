package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import Gy.control.Global;

public class Test {

	/**
	 * ������Ϣͷ����Ϣ�����У����
	 */
	public static byte getCheckOut(byte[] msg) {
		int verify = 0;
		int[] msgValue = new int[msg.length - 2]; 
		// ����Ϣͷ����Ϣ�����ת��
		for (int i = 0; i < msgValue.length; i++) {
			msgValue[i] = msg[i + 1];
		} 
		verify = msgValue[0] ^ msgValue[1];
		// ����Ϣͷ����Ϣ�������򣬵ó�У����
		for (int j = 2; j < msgValue.length - 1; j++) {
			verify = verify ^ msgValue[j];
		} 
		return (byte) (verify & 0xFF);
	}

	public static String getCheckOut2(String  strmsg) {
		byte[] msg = Global.HexString2Bytes(strmsg);
		int verify = 0; 
		verify = msg[1] ^ msg[2]; //��ʼ����7e���濪ʼ
		// ����Ϣͷ����Ϣ�������򣬵ó�У����
		for(int j = 3; j < msg.length -2  ; j++) {
			verify = verify ^ msg[j];
		}
		int result = (int)(verify & 0xff);  
		String strResult =Integer.toHexString(result);  //�ó�У�����ַ���
		if (strResult.length()<2) {
			strResult = "0"+strResult;
		}
		System.err.println("22"+strResult);
		strResult = strmsg.substring(0, strmsg.length()-4) + strResult+"7e";
		return strResult;
	}
	public static void main(String[] args) throws IOException { 
		String strmsg = "7e870100070130557731100053c21408131200053a7e"; 
		System.err.println(getCheckOut2(strmsg)); 
		
	}
}
