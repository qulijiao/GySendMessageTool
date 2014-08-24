package Gy.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.xml.internal.ws.resources.SenderMessages;

import Gy.business.Message;
import Gy.business.MessageStatus;
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	public Queue<Message> sendingMsgQueue =new LinkedList<Message>();  //消息队列	
	STATUS threadstatus = STATUS.idle;
	Socket sc;  
	OutputStream os=null;
	boolean isRegedit = true ; //是否发送鉴权消息
	public static List<Message> sendedMsgList = new ArrayList<Message>(); 
	private int sendingcount=1 ;
	private int sendFrequency=50;  //超时补传时间 
	
	//遍历发送队列 如果已经应答则从队列中删除 
	public boolean isMsgSendSuccesessed(String serialnum){
		for(Message sendmsg :sendingMsgQueue ){
			if (sendmsg.curserial.equals(serialnum)) {
				sendingMsgQueue.remove(sendmsg);
				return true;
			}
		}
		return false;
	}
	
	//设置消息的发送次数 如果已经发送过则判断次数如果小于设置值则再发送一次
	public void setSendingCount(int intcount){
		if (intcount>1) {			
			sendingcount =intcount;
		}
	}
	/**
	 * 不符合发送条件返回false 
	 * */
	public boolean isGoing2Send(Message currentMsg){
		boolean result = false ;		
		if (currentMsg == null) {
			Global.info("消息队列为空" );
			return false;
		} 	
		String strmsg = currentMsg.getMsgContent(); 
		if (strmsg.equals("") || strmsg== null) {  //空消息屏蔽
			return false;
		}
		//newmsg,sengding,sended,sendsuccessed,recieved,recAnalysising,recanswering,recremoving,finished;
		if (currentMsg.status == MessageStatus.STATUS.newmsg) {
			return true;
		}
		//已发送 且累计发送次数小于设置值 且上次发送时间超过设置值（50秒）
		if (currentMsg.status== MessageStatus.STATUS.sended   
				&& currentMsg.sendcount < sendingcount
				&& currentMsg.sendTime +sendFrequency >System.currentTimeMillis()  ) {
			return true;
		}		
		return result;
	}

	public void setSocket(Socket sc) {
		this.sc = sc;
	}
    
	//添加消息队列 
	public void addSendingMessage(Message sendMsg){
		sendingMsgQueue.add(sendMsg);
	}
	
	//获取待发送的消息
	private Message getSendingMessage(){
		Message curMsg =null;
		if (!sendingMsgQueue.isEmpty()) {
			curMsg = sendingMsgQueue.remove();
		}
		return curMsg;
	}
	
	@Override
	public void run() {
		threadstatus = STATUS.runing; 
		Global.info("发送线程开启:"+ threadstatus+" 连接:"+sc.getInetAddress()); 		
		while (threadstatus == STATUS.runing ) {
			try {
				os = sc.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Controlor.sleep(1000);
			System.err.println("待发送:"+sendingMsgQueue.size()+"已发送:"+sendedMsgList.size());			
			Message  currentMsg= getSendingMessage();
			if (!isGoing2Send(currentMsg)) {  //不符合发送条件返回为假
				continue;
			} 
			try {
//					String src = "7e01020001013055773110000139087e"; 
					String strmsg = currentMsg.getMsgContent();
					System.err.println("待发送内容:"+strmsg);
					os.write(Global.HexString2Bytes(strmsg));
					os.flush(); 
					currentMsg.setSended();         //设置为已发送状态 
					sendingMsgQueue.add(currentMsg); //重新插入队列
					sendedMsgList.add(currentMsg);  //添加到已发送队列中 
					Controlor.sleep(1000);  
			}  catch (IOException e) {
				e.printStackTrace(); 
				sendingMsgQueue.add(currentMsg);  //出现异常则需要把该消息插回队列
//				status = STATUS.finished;
			}
			// 退出发送线程  不做退出处理 socket一直保持状态
			// status = STATUS.finished;
		}
	}
 
	//把消息插入到发送消息队列
	public void addSendingMessages(List<String> readMessages) {
		for (int i = 0; i < readMessages.size(); i++) {
			addSendingMessage( new SendMessage( readMessages.get(i) ));
		} 
		
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		SendThread st = new SendThread();	
		Socket sc = new Socket("115.29.198.101",8988);
//		OutputStream os;
//		os = sc.getOutputStream();
//		PrintWriter out = new PrintWriter(sc.getOutputStream());				
		String src = "7e01020001013055773110000139087e";
//		os.write(Global.HexString2Bytes(src)); 
		st.setSocket(sc);
		st.addSendingMessage(new SendMessage("7e01020001013055773110000139017e"));
		new Thread(st).start();
	}
}


