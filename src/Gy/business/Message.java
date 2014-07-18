package Gy.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.OutputStream;
import java.net.UnknownHostException;

public class Message {
	boolean isrunning = false;
	int index;
	
	private static int serialnum = 0;

	public static String getMessage(String strmessage) {
		String strResult="";
		if (strmessage.length()<30) {
			strResult = "";
		}
		serialnum++;
		if (serialnum>65535) {
			serialnum=0;
		}
		String strSerialnum = Integer.toHexString(serialnum);
		int intlength = strSerialnum.length();
		if (intlength<4) {
			for (int i = 0; i < 4-intlength; i++) {
				strSerialnum= "0"+strSerialnum;
			}
		}
		strResult = strmessage.substring(0, 22)+strSerialnum +strmessage.substring(26);		
		return strResult;
	}

	public void sendmessage() {
		Message tgys = new Message();
		Socket sc = null;
		OutputStream os;
		int i = 0;
		try {
			sc = new Socket("192.168.1.180", 8988);
			os = sc.getOutputStream();
			PrintWriter out = new PrintWriter(sc.getOutputStream());
			// out.print("7e1e39322600103177553001020002017e");
			// out.print("7e01020002013055773110002632391e7e");
			// out.flush();
			String src = "7e01020002013055773110002632391e7e";
			os.write(tgys.HexString2Bytes(src));
			os.flush();
			System.err.println(i);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.err.println("fff");
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

 




	private byte[] HexString2Bytes(String src) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Message t = new Message();
//		for (int i = 0; i <  11; i++) {			
//			System.err.println(getMessage("7e02000073013055773110000200000ff00004000301914e5f0719ac39000000000000140702160746010"));
//		}
		System.err.println((char)126);
		System.err.println((byte)'A');
	}

}
