package Gy.business;

import Gy.business.MessageStatus.RECEIVEMSGSTATUS;

public class ReceiveMessage extends Message {
	RECEIVEMSGSTATUS STATUS;
	public ReceiveMessage(String strMsg) {
		super(strMsg);
		STATUS= RECEIVEMSGSTATUS.recieved;  //表示刚接收到
		// TODO Auto-generated constructor stub
	}

}
