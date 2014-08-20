package Gy.business;
 

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Gy.control.Global;

public class MessageFactory { 	
	
	// ����ʣ���ַ��� �������Ϣ���뵽���ն���
	public static String  createMessageQueue(String strMsg,Queue recMsgQueue){
		if (recMsgQueue == null ) {
			Global.debug("���ն���Ϊ�գ���������");
			return strMsg;
		}
		List<ReceiveMessage> messagesList = new ArrayList<ReceiveMessage>(); 
		String strAfterTrains = strMsg; 
		while (strAfterTrains.indexOf("7e") > -1) {
			int beginIndex = strAfterTrains.indexOf("7e");
			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //�����Ϣ��û��7e��β������ѭ��
			}
			messagesList.add(new ReceiveMessage(strAfterTrains.substring(beginIndex, endIndex + 2)));
			strAfterTrains = strAfterTrains.substring(endIndex + 2);
		}
//		System.err.println(messagesList);
		recMsgQueue.addAll(messagesList);
		return strAfterTrains;
	}
	
	/**
	 * ���ַ���ת������Ϣ���У������뵽����MsgQueue�У�ͬʱ����ʣ����ַ���
	 * @param type 0��������Ϣ 1���������Ϣ
	 * @param MsgQueue ��Ϣ����
	 * */
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
	 
	
	public static List<String> readStringMsg(String strresult) {
		List<String> messagesList = new ArrayList<String>();
//		messagesList.clear(); 
		String strAfterTrains = strresult; 
		while (strAfterTrains.indexOf("7e") > -1) {
			int beginIndex = strAfterTrains.indexOf("7e");
			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //�����Ϣ��û��7e��β������ѭ��
			}
			// �����Ϣ ������У���� 
			messagesList.add(Global.getCheckOut(strAfterTrains.substring(beginIndex, endIndex + 2))); // ��ֹ��7e
			strAfterTrains = strAfterTrains.substring(endIndex + 2);
		}
		System.err.println(messagesList);
		return messagesList;
	}

	public static String readStringMsg(String strresult,List<String> resultList) {
		List<String> messagesList = new ArrayList<String>();
		System.err.println("resultList:"+resultList);
//		messagesList.clear(); 
		String strAfterTrains = strresult; 
		while (strAfterTrains.indexOf("7e") > -1) {
			int beginIndex = strAfterTrains.indexOf("7e");
			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //�����Ϣ��û��7e��β������ѭ��
			}
			// �����Ϣ ������У���� 
			messagesList.add(Global.getCheckOut(strAfterTrains.substring(beginIndex, endIndex + 2))); // ��ֹ��7e
			strAfterTrains = strAfterTrains.substring(endIndex + 2);
		}
//		System.err.println(messagesList);
		System.err.println("messagesList:"+messagesList);
		resultList.addAll( messagesList);
		return strAfterTrains;
	}	
	
	static void testString(String msg){
		msg = "1";
	}
	public static void main(String[] args) {
//		String str =MessageFactory.readStringMsg("7esdf7e7easdfadfasdfadf7e7e1111111117e7e1117e",null);
//		System.err.println("str:"+str);
		String str="2";
		testString(str);
		System.err.println(str);
	}
}
