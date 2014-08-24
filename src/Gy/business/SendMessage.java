package Gy.business;

import Gy.business.MessageStatus.SENDMSGSTATUS;

public class SendMessage extends Message {

	SENDMSGSTATUS STATUS;
	

	public SendMessage(String strMsg) {
		super(strMsg);
		STATUS = SENDMSGSTATUS.beginning;
	}

	/**
	 * ���÷��ʹ��� ����״̬ ����ʱ��
	 * */
	public void setSended() {
		sendcount++;
		STATUS = SENDMSGSTATUS.sended;
		sendTime = System.currentTimeMillis();
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
		// beginning,sending,sended,successed,finished;
		if (STATUS == SENDMSGSTATUS.beginning) {
			return false;
		} else if (STATUS == SENDMSGSTATUS.sending) {
			return false;
		}
		return true;
	}

}
