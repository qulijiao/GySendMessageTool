package Gy.business;

import Gy.business.MessageStatus.RECEIVEMSGSTATUS;

public class ReceiveMessage extends Message {
	RECEIVEMSGSTATUS STATUS;
	public ReceiveMessage(String strMsg) {
		super(strMsg);
		STATUS= RECEIVEMSGSTATUS.recieved;  //��ʾ�ս��յ�
		// TODO Auto-generated constructor stub
	}

}
