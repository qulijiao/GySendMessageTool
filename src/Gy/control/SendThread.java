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
import Gy.business.MessageStatus;
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	public Queue<Message> sendingMsgQueue =new LinkedList<Message>();  //��Ϣ����	
	STATUS threadstatus = STATUS.idle;
	Socket sc;  
	OutputStream os=null;
	boolean isRegedit = true ; //�Ƿ��ͼ�Ȩ��Ϣ
	public static List<Message> sendedMsgList = new ArrayList<Message>(); 
	private int sendingcount=1 ;
	private int sendFrequency=50;  //��ʱ����ʱ�� 
	
	//�������Ͷ��� ����Ѿ�Ӧ����Ӷ�����ɾ�� 
	public boolean isMsgSendSuccesessed(String serialnum){
		for(Message sendmsg :sendingMsgQueue ){
			if (sendmsg.curserial.equals(serialnum)) {
				sendingMsgQueue.remove(sendmsg);
				return true;
			}
		}
		return false;
	}
	
	//������Ϣ�ķ��ʹ��� ����Ѿ����͹����жϴ������С������ֵ���ٷ���һ��
	public void setSendingCount(int intcount){
		if (intcount>1) {			
			sendingcount =intcount;
		}
	}
	/**
	 * �����Ϸ�����������false 
	 * */
	public boolean isGoing2Send(Message currentMsg){
		boolean result = false ;		
		if (currentMsg == null) {
			Global.info("��Ϣ����Ϊ��" );
			return false;
		} 	
		String strmsg = currentMsg.getMsgContent(); 
		if (strmsg.equals("") || strmsg== null) {  //����Ϣ����
			return false;
		}
		//newmsg,sengding,sended,sendsuccessed,recieved,recAnalysising,recanswering,recremoving,finished;
		if (currentMsg.status == MessageStatus.STATUS.newmsg) {
			return true;
		}
		//�ѷ��� ���ۼƷ��ʹ���С������ֵ ���ϴη���ʱ�䳬������ֵ��50�룩
		if (currentMsg.status== MessageStatus.STATUS.sended   
				&& currentMsg.sendcount < sendingcount
				&& currentMsg.sendTime +sendFrequency >System.currentTimeMillis()  ) {
			return true;
		}		
		return result;
	}

	public void setSocket(Socket sc) {
		this.sc = sc;
	}
    
	//�����Ϣ���� 
	public void addSendingMessage(Message sendMsg){
		sendingMsgQueue.add(sendMsg);
	}
	
	//��ȡ�����͵���Ϣ
	private Message getSendingMessage(){
		Message curMsg =null;
		if (!sendingMsgQueue.isEmpty()) {
			curMsg = sendingMsgQueue.remove();
		}
		return curMsg;
	}
	
	@Override
	public void run() {
		threadstatus = STATUS.runing; 
		Global.info("�����߳̿���:"+ threadstatus+" ����:"+sc.getInetAddress()); 		
		while (threadstatus == STATUS.runing ) {
			try {
				os = sc.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Controlor.sleep(1000);
			System.err.println("������:"+sendingMsgQueue.size()+"�ѷ���:"+sendedMsgList.size());			
			Message  currentMsg= getSendingMessage();
			if (!isGoing2Send(currentMsg)) {  //�����Ϸ�����������Ϊ��
				continue;
			} 
			try {
//					String src = "7e01020001013055773110000139087e"; 
					String strmsg = currentMsg.getMsgContent();
					System.err.println("����������:"+strmsg);
					os.write(Global.HexString2Bytes(strmsg));
					os.flush(); 
					currentMsg.setSended();         //����Ϊ�ѷ���״̬ 
					sendingMsgQueue.add(currentMsg); //���²������
					sendedMsgList.add(currentMsg);  //��ӵ��ѷ��Ͷ����� 
					Controlor.sleep(1000);  
			}  catch (IOException e) {
				e.printStackTrace(); 
				sendingMsgQueue.add(currentMsg);  //�����쳣����Ҫ�Ѹ���Ϣ��ض���
//				status = STATUS.finished;
			}
			// �˳������߳�  �����˳����� socketһֱ����״̬
			// status = STATUS.finished;
		}
	}
 
	//����Ϣ���뵽������Ϣ����
	public void addSendingMessages(List<String> readMessages) {
		for (int i = 0; i < readMessages.size(); i++) {
			addSendingMessage( new SendMessage( readMessages.get(i) ));
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
		st.addSendingMessage(new SendMessage("7e01020001013055773110000139017e"));
		new Thread(st).start();
	}
}


