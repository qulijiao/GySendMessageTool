package Gy.business;
 

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Gy.control.Global;

public class MessageFactory { 	
	
	
	 
	
	
	/**
	 * ���ַ���ת������Ϣ���У������뵽����MsgQueue�У�ͬʱ����ʣ����ַ���
	 * @param type 0��������Ϣ 1���������Ϣ
	 * @param MsgQueue ��Ϣ����

	public static String  createMessageQueue(String strMsg,Queue MsgQueue,int type){
		if (MsgQueue == null ) {
			Global.debug("���ն���Ϊ�գ���������");
			return strMsg;
		}
		List<Message> messagesList = new ArrayList<Message>(); 
		String strAfterTrains = strMsg; 
		while (strAfterTrains.indexOf("7e") > -1) {
			int beginIndex = strAfterTrains.indexOf("7e");
			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //�����Ϣ��û��7e��β������ѭ��
			}
			if (type == 1) {				
				messagesList.add(new ReceiveMessage(strAfterTrains.substring(beginIndex, endIndex + 2)));
			}else {
				messagesList.add(new SendMessage(strAfterTrains.substring(beginIndex, endIndex + 2)));				
			}
			strAfterTrains = strAfterTrains.substring(endIndex + 2);
		} 
		MsgQueue.addAll(messagesList);
		return strAfterTrains;
	}  
		 * */
	 //��ȡ�ַ���ʣ�µ����� 
	public static String getLeftMsgString(String strMsgs){
		while (strMsgs.indexOf("7e") > -1) {
			int beginIndex = strMsgs.indexOf("7e");
			int endIndex = strMsgs.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //�����Ϣ��û��7e��β������ѭ��
			}	
//			System.err.println("test:"+strMsgs.substring(beginIndex, endIndex + 2));
			strMsgs = strMsgs.substring(endIndex + 2);
		}
		return strMsgs;
	}
	
//	public static List<String> getStringMsgList(String strresult) {
//		List<String> messagesList = new ArrayList<String>();
////		messagesList.clear(); 
//		String strAfterTrains = strresult; 
//		while (strAfterTrains.indexOf("7e") > -1) {
//			int beginIndex = strAfterTrains.indexOf("7e");
//			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
//			if (endIndex == -1) {
//				break;  //�����Ϣ��û��7e��β������ѭ��
//			}
//			// �����Ϣ ������У���� 
//			messagesList.add(Message.getCheckOut(strAfterTrains.substring(beginIndex, endIndex + 2))); // ��ֹ��7e
//			strAfterTrains = strAfterTrains.substring(endIndex + 2);
//		}
//		System.err.println(messagesList);
//		return messagesList;
//	}
	

	public static List<Message> getMesssageList( String strMessages ) {
		List<Message> listresult = new ArrayList<Message>();
		while (strMessages.indexOf("7e") > -1) {
			int beginIndex = strMessages.indexOf("7e");
			int endIndex = strMessages.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //�����Ϣ��û��7e��β������ѭ��
			}
			// �����Ϣ ������У���� 
			listresult.add(new Message(strMessages.substring(beginIndex, endIndex + 2)));
			strMessages = strMessages.substring(endIndex + 2);
		}
		return listresult;
	}	
	
	public static void main(String[] args) {
//		String str =MessageFactory.readStringMsg("7esdf7e7easdfadfasdfadf7e7e1111111117e7e1117e",null);
//		System.err.println("str:"+str);
//		String str="2";
//		testString(str);
//		System.err.println(str);
		System.err.println(getLeftMsgString("7eaaaaa7exxxx7e11111117e1237e"));
	}
}
