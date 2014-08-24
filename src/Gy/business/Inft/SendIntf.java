package Gy.business.Inft;

import Gy.business.MessageStatus.SENDMSGSTATUS;

public interface SendIntf {
	/**
	 * 设置发送次数 发送状态 发送时间
	 * */
	public void setSended();
	
	
	/**
	 * 确认消息是否已发送   
	 * */
	public boolean isSended() ;
}
