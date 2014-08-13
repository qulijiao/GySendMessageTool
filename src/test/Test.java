package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import Gy.control.Global;

public class Test {

	/**
	 * 根据消息头和消息体算出校验码
	 */
	public static byte getCheckOut(byte[] msg) {
		int verify = 0;
		int[] msgValue = new int[msg.length - 2]; 
		// 把消息头跟消息体进行转换
		for (int i = 0; i < msgValue.length; i++) {
			msgValue[i] = msg[i + 1];
		} 
		verify = msgValue[0] ^ msgValue[1];
		// 吧消息头跟消息体进行异或，得出校验码
		for (int j = 2; j < msgValue.length - 1; j++) {
			verify = verify ^ msgValue[j];
		} 
		return (byte) (verify & 0xFF);
	}

	public static String getCheckOut2(String  strmsg) {
		byte[] msg = Global.HexString2Bytes(strmsg);
		int verify = 0; 
		verify = msg[1] ^ msg[2]; //初始化从7e后面开始
		// 把消息头跟消息体进行异或，得出校验码
		for(int j = 3; j < msg.length -2  ; j++) {
			verify = verify ^ msg[j];
		}
		int result = (int)(verify & 0xff);  
		String strResult =Integer.toHexString(result);  //得出校验码字符串
		if (strResult.length()<2) {
			strResult = "0"+strResult;
		}
		System.err.println("22"+strResult);
		strResult = strmsg.substring(0, strmsg.length()-4) + strResult+"7e";
		return strResult;
	}
	public static void main(String[] args) throws IOException { 
		String strmsg = "7e0200003c0183591014613a0000000000008400030158733806cd1ffe00000016014a14081317064001040000004d2b04000001f4030200004b04464646462403000000250400000000300131007e"; 
		System.err.println(getCheckOut2(strmsg)); 
		
	}
}
