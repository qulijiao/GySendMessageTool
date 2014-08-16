package Gy.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.xml.internal.ws.resources.SenderMessages;

import Gy.business.Message;
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	public Queue<SendMessage> sendingMsg =new LinkedList<SendMessage>();  //��Ϣ����
	STATUS status = STATUS.idle;
	Socket sc;  
	boolean isRegedit = true ; //�Ƿ��ͼ�Ȩ��Ϣ
	 
 

	public void setSocket(Socket sc) {
		this.sc = sc;
	}
    
	//�����Ϣ����
	public void setSendingMessage(SendMessage sendMsg){
		sendingMsg.add(sendMsg);
	}
	
	private SendMessage getSendingMessage(){
		SendMessage curMsg =null;
		if (!sendingMsg.isEmpty()) {
			curMsg = sendingMsg.remove();
		}
		return curMsg;
	}
	
	@Override
	public void run() {
		status = STATUS.runing; 
		Global.info("�����߳̿���:"+ status+" ����:"+sc.getInetAddress());
		while (status == STATUS.runing ) {
			SendMessage  currentMsg= getSendingMessage();
			if (currentMsg == null) {
				Global.info("��Ϣ����Ϊ��" );
				Controlor.sleep(1000);
			}
			if ( currentMsg.isSended()) {
				//������Կ����ظ����� Ŀǰ��ʱֻ����һ�� 
				continue; //����Ѿ����͹����˳�����ѭ��
			} 
			OutputStream os=null;
			try {
				os = sc.getOutputStream();  
//					String src = "7e01020001013055773110000139087e";
//					String src = "7e01020001013055773110000131087e";
//					System.err.println("���ͼ�Ȩ��Ϣ��"+src);
					String strmsg = currentMsg.getMsgContent(); 
					os.write(Global.HexString2Bytes(strmsg));
					os.flush(); 
					Controlor.sleep(1000);  
			}  catch (IOException e) {
				e.printStackTrace();
				status = STATUS.finished;
			}
			// �˳������߳�  �����˳����� socketһֱ����״̬
			// status = STATUS.finished;
		}
	}


	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket sc = new Socket("115.29.198.101",8988);
		OutputStream os;
		os = sc.getOutputStream();
//		PrintWriter out = new PrintWriter(sc.getOutputStream());				
		String src = "7e01020001013055773110000139087e";
		os.write(Global.HexString2Bytes(src)); 
		
		
	}
}


