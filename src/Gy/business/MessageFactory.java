package Gy.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MessageFactory {

	public static List<String> readMessages(String str) {
		List<String> messageList = new ArrayList<String>();
		while (str.indexOf("7e") > -1) {
			int startindex = str.indexOf("7e");
			int endindex = str.indexOf("7e", startindex+1);
			if (endindex > startindex) {
				messageList.add(str.substring(startindex, endindex + 2));
				str = str.substring(endindex + 2);
//				System.err.println(str);
			}else {
				break;
			}
		}
		System.err.println("MessageFactory 解析总消息数:"+messageList.size());
		return messageList;
	}

	public static void main(String[] args) {
//		System.err.println("123456".indexOf("34")); // 2
//		System.err.println("7easdfa7esdf".indexOf("7e"));
//		System.err.println("7easdfa7esdf".indexOf("7e",2));
//		System.err.println(MessageFactory.readMessages("7e8001000501305577311048d50001010200297e7e870100070130557731101badc2140719174930a37e"));
		String strResultTrainsed ="";
//		System.err.println("设置结果");
		for ( String mes : MessageFactory.readMessages("7e8001000501305577311048d50001010200297e7e870100070130557731101badc2140719174930a37e")  ) {
			System.err.println("mes:  "+mes);
			strResultTrainsed =strResultTrainsed+ mes +"\n";
		}
		System.err.println("strResultTrainsed:"+strResultTrainsed);
		
	}
}
