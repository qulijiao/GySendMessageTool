package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Gy.business.SendMessage;
import Gy.control.Controlor;
import Gy.control.Global;
import Gy.control.SendThread;
import Gy.control.Global.STATUS;

public class Testsocket {
	public static void main(String[] args) {
		try {
			Socket sc = new Socket("www.baidu.com", 80);
			OutputStream os = null;
			try {
				os = sc.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			} ;
			os.write(Global.HexString2Bytes(""));
			os.flush();
			os.close();
			System.err.println(sc);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
