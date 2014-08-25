package Gy.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.OutputStream;
import java.net.UnknownHostException;

import Gy.business.MessageStatus.SENDMSGSTATUS;
import Gy.business.intf.SendInft;
import Gy.control.Global;

public class Message implements SendInft {
	private String strMsg ;
	boolean isrunning = false;
	int index;
	//·¢ËÍ×´Ì¬
	SENDMSGSTATUS STATUS=SENDMSGSTATUS.beginning ;
	
	private static int serialnum = 0;

	public Message(String strmessage){
		this.strMsg =strmessage;
		this.strMsg = getMessage(this.strMsg);
		this.strMsg= Global.getCheckOut(this.strMsg);
		System.err.println(strMsg);
	}
	
	public String getMsgContent(){
		return strMsg;
	}
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

	public void sendmessage2() {
		Message tgys = new Message("");
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
			os.write(Global.HexString2Bytes(src));
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

 




 

	public static void main(String[] args) {
		Message t = new Message("");
//		for (int i = 0; i <  11; i++) {			
//			System.err.println(getMessage("7e02000073013055773110000200000ff00004000301914e5f0719ac39000000000000140702160746010"));
//		}
		t.STATUS =SENDMSGSTATUS.finished;
		System.err.println(t.STATUS);
	}

	
	@Override
	public void SetSended() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSended() {
		// TODO Auto-generated method stub
		return false;
	}

}
