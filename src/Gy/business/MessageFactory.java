package Gy.business;
 

import java.util.ArrayList;
import java.util.List;

import Gy.control.Global;

public class MessageFactory { 
	
	
	
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
		resultList =messagesList;
		return strAfterTrains;
	}	
	public static void main(String[] args) {
		String str =MessageFactory.readStringMsg("7esdf7e7easdfadfasdfadf7e7e1111111117e7e1117e",null);
		System.err.println("str:"+str);
	}
}
