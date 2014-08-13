package Gy.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import Gy.business.Message;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	List<String> messageList = new ArrayList<String>();  //��Ϣ����
	STATUS status = STATUS.idle;
	Socket sc;
	static int i = 0;
	int sendCount = 0;

	public void setSendCount(int count) {
		if (count < 1) {
			sendCount = 1;
		}
		sendCount = count;
	}

	public void setSocket(Socket sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		status = STATUS.runing;
		System.err.println("�����߳̿���:" + status+" ����:"+sc.getInetAddress());		
		while (status == STATUS.runing && i < sendCount) {
			i++;
			System.err.println("��������" + i); 
			OutputStream os=null;
			try {
				os = sc.getOutputStream();
//				PrintWriter out = new PrintWriter(sc.getOutputStream());				
//				String src = "7e01020001013055773110000139087e";
//				os.write(Global.HexString2Bytes(src));
//				os.flush();
				Controlor.sleep(2000);
				String sendmessage = getMessage( );
				while (sendmessage != "") {
					System.err.println("�������ݣ�"+sendmessage);
					os.write(Global.HexString2Bytes(sendmessage));
					os.flush();	
					sendmessage = getMessage();
					Controlor.sleep(3000);
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
				status = STATUS.finished;
			} catch (IOException e) {
				e.printStackTrace();
				status = STATUS.finished;
			}
			// �˳������߳�
			if (i >= sendCount) {
				System.err.println("�˳������߳�");
				i = 0;
				// ��ɺ�����״̬Ϊfinished 
				status = STATUS.finished;
			}
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setMessage(List<String> readMessages) {
		messageList.addAll(readMessages);
		System.err.println(messageList); 
	}
	private String getMessage(){
		if (messageList.size()<1) {
			return "";
		}
		String result=messageList.get(0);		
		messageList.remove(0);
		return result;
	}
	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket sc = new Socket("115.29.198.101",8988);
		OutputStream os;
		os = sc.getOutputStream();
//		PrintWriter out = new PrintWriter(sc.getOutputStream());				
		String src = "7e01020001013055773110000139087e";
		os.write(Global.HexString2Bytes(src));
		System.err.println(sc.isClosed());
		os.flush();
		System.err.println(os);
//		os.close();
		System.err.println(sc);
//		sc.close();
		System.err.println(sc);
		Controlor.sleep(3000);
		System.err.println(sc.isConnected());
		System.err.println(sc.isClosed());
		
		
		
	}
}


