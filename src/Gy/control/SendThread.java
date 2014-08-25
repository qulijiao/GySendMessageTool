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
	public Queue<SendMessage> sendingMsgQueue =new LinkedList<SendMessage>();  //��Ϣ����
	STATUS status = STATUS.idle;
	Socket sc;
	OutputStream os=null;
	boolean isRegedit = true ; //�Ƿ��ͼ�Ȩ��Ϣ
	public static List<SendMessage> sendedMsgList = new ArrayList<SendMessage>();
	static int testint = 3;
 

	public void setSocket(Socket sc) {
		this.sc = sc;
	}
    
	//�����Ϣ����
	public void setSendingMessage(SendMessage sendMsg){
		sendingMsgQueue.add(sendMsg);
	}
	
	private SendMessage getSendingMessage(){
		SendMessage curMsg =null;
		if (!sendingMsgQueue.isEmpty()) {
			curMsg = sendingMsgQueue.remove();
		}
		return curMsg;
	}
	
	@Override
	public void run() {
		status = STATUS.runing; 
		Global.info("�����߳̿���:"+ status+" ����:"+sc.getInetAddress()); 		
		while (status == STATUS.runing ) {
			try {
				os = sc.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Controlor.sleep(1000);
			System.err.println("������:"+sendingMsgQueue.size()+"�ѷ���:"+sendedMsgList.size());			
			SendMessage  currentMsg= getSendingMessage();
			if (currentMsg == null) {
				Global.info("��Ϣ����Ϊ��" );
				Controlor.sleep(2000);
				continue;
			} 
			try { 
//					String src = "7e01020001013055773110000139087e";
//					String src = "7e01020001013055773110000131087e";
//					System.err.println("���ͼ�Ȩ��Ϣ��"+src);
					String strmsg = currentMsg.getMsgContent(); 
					System.err.println("��������:"+strmsg);
					if (strmsg.equals("")) {  //����Ϣ����
						continue;
					}
					os.write(Global.HexString2Bytes(strmsg));
					os.flush(); 
					currentMsg.SetSended();         //����Ϊ�ѷ���״̬ 
					sendedMsgList.add(currentMsg);  //��ӵ��ѷ��Ͷ����� 
					Controlor.sleep(1000);  
			}  catch (IOException e) {
				e.printStackTrace(); 
				sendingMsgQueue.add(currentMsg);  //�����쳣����Ҫ�Ѹ���Ϣ��ض���
				try {
					os.close();
					sc.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				status = STATUS.finished;
			}
			// �˳������߳�  �����˳����� socketһֱ����״̬
			// status = STATUS.finished;
		}
	}


	public static void main(String[] args) throws UnknownHostException, IOException {
		SendThread st = new SendThread();	
		Socket sc = new Socket("115.29.198.101",8988);
//		OutputStream os;
//		os = sc.getOutputStream();
//		PrintWriter out = new PrintWriter(sc.getOutputStream());				
		String src = "7e01020001013055773110000139087e";
//		os.write(Global.HexString2Bytes(src)); 
		st.setSocket(sc);
		st.setSendingMessage(new SendMessage("7e01020001013055773110000139017e"));
		st.setSendingMessage(new SendMessage("7e01020001013055773110000139027e"));
		st.setSendingMessage(new SendMessage("7e01020001013055773110000139037e"));
		st.setSendingMessage(new SendMessage("7e01020001013055773110000139047e"));
		new Thread(st).start();
	}
	//�Ѷ��в��뵽������Ϣ����
	public void setSendingMessage(List<String> readMessages) {
		for (int i = 0; i < readMessages.size(); i++) {
			setSendingMessage( new SendMessage( readMessages.get(i) ));
		} 
		
	}
}


