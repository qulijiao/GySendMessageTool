package Gy.business;
 

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Gy.control.Global;

public class MessageFactory { 
	
	
	// 返回剩余字符串 另外把消息加入到接收队列
	public static String  createRecMessage(String strMsg,Queue recMsgQueue){
		if (recMsgQueue == null ) {
			Global.debug("接收队列为空，不做处理");
			return strMsg;
		}
		List<ReceiveMessage> messagesList = new ArrayList<ReceiveMessage>(); 
		String strAfterTrains = strMsg; 
		while (strAfterTrains.indexOf("7e") > -1) {
			int beginIndex = strAfterTrains.indexOf("7e");
			int endIndex = strAfterTrains.indexOf("7e", beginIndex + 1);
			if (endIndex == -1) {
				break;  //如果消息体没有7e结尾则跳出循环
			}
			messagesList.add(new ReceiveMessage(strAfterTrains.substring(beginIndex, endIndex + 2)));
			strAfterTrains = strAfterTrains.substring(endIndex + 2);
		}
//		System.err.println(messagesList);
		recMsgQueue.addAll(messagesList);
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
				break;  //如果消息体没有7e结尾则跳出循环
			}
			// 添加消息 并更新校验码 
			messagesList.add(Global.getCheckOut(strAfterTrains.substring(beginIndex, endIndex + 2))); // 截止到7e
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
				break;  //如果消息体没有7e结尾则跳出循环
			}
			// 添加消息 并更新校验码 
			messagesList.add(Global.getCheckOut(strAfterTrains.substring(beginIndex, endIndex + 2))); // 截止到7e
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
