package Gy.business;

import Gy.business.MessageStatus.SENDMSGSTATUS;

public class SendMessage extends Message {
	
	SENDMSGSTATUS STATUS; 
	private int sendcount =0;
	private double sendTime;
	public SendMessage(String strMsg) {
		super(strMsg);
		STATUS= SENDMSGSTATUS.beginning; 
	}
	/**
	 * 设置发送次数 发送状态 发送时间
	 * */
	public void SetSended(){		
		sendcount++;
		STATUS=SENDMSGSTATUS.sended;
		sendTime= System.currentTimeMillis();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
	}
	/**
	 * 确认消息是否已发送   
	 * */
	public boolean isSended() {
//		 beginning,sending,sended,successed,finished;
		if (STATUS == SENDMSGSTATUS.beginning) {
			return false;
		}else if (STATUS == SENDMSGSTATUS.sending) {
			return false;
		}
		return true;
	}

}
