package Gy.business;
 

import java.util.ArrayList;
import java.util.List;

import Gy.control.Global;

public class MessageFactory { 
	
	
	
	public static List<String> readMessages(String strresult) {
		List<String> messagesList = new ArrayList<String>();
//		messagesList.clear(); 
		String strAfterTrains = strresult; 
		while (strAfterTrains.indexOf("7e") > -1) {
			int beginIndex = strAfterTrains.indexOf("7e");
			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //如果消息体没有7e结尾则跳出循环
			}
			// 添加消息 并更新校验码 
			messagesList.add(Global.getCheckOut(strAfterTrains.substring(beginIndex, endIndex + 2))); // 截止到7e
			strAfterTrains = strAfterTrains.substring(endIndex + 2);
		}
		System.err.println(messagesList);
		return messagesList;
	}

	public static void main(String[] args) {
		MessageFactory.readMessages("7esdf7e7easdfadfasdfadf7e7e1111111117e7e111");
	}
}
