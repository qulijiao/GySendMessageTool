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

	public static void main(String[] args) throws IOException {
//		String strmsg = "7e01020002013055773110002632391e7e";
		String strmsg = "7e01020002013055773110002632397e7e";
		strmsg="7e0100002e013055773110000100230064475930303154797065303030310000000000000000000000003030303030313101c3f6412d35433539327e7e";

		byte[] msg = Global.HexString2Bytes(strmsg);
		System.err.println((int)getCheckOut(msg));
	}
}
