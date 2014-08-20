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
	 * ���÷��ʹ��� ����״̬ ����ʱ��
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
	 * ȷ����Ϣ�Ƿ��ѷ���   
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
