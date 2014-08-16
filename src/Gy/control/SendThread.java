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
import Gy.business.SendMessage;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	public Queue<SendMessage> sendingMsg =new LinkedList<SendMessage>();  //消息队列
	STATUS status = STATUS.idle;
	Socket sc;  
	boolean isRegedit = true ; //是否发送鉴权消息
	 
 

	public void setSocket(Socket sc) {
		this.sc = sc;
	}
    
	//添加消息队列
	public void setSendingMessage(SendMessage sendMsg){
		sendingMsg.add(sendMsg);
	}
	
	private SendMessage getSendingMessage(){
		SendMessage curMsg =null;
		if (!sendingMsg.isEmpty()) {
			curMsg = sendingMsg.remove();
		}
		return curMsg;
	}
	
	@Override
	public void run() {
		status = STATUS.runing; 
		Global.info("发送线程开启:"+ status+" 连接:"+sc.getInetAddress());
		while (status == STATUS.runing ) {
			SendMessage  currentMsg= getSendingMessage();
			if (currentMsg == null) {
				Global.info("消息队列为空" );
				Controlor.sleep(1000);
			}
			if ( currentMsg.isSended()) {
				//这里可以考虑重复发送 目前暂时只发送一次 
				continue; //如果已经发送过则退出本次循环
			} 
			OutputStream os=null;
			try {
				os = sc.getOutputStream();  
//					String src = "7e01020001013055773110000139087e";
//					String src = "7e01020001013055773110000131087e";
//					System.err.println("发送鉴权消息："+src);
					String strmsg = currentMsg.getMsgContent(); 
					os.write(Global.HexString2Bytes(strmsg));
					os.flush(); 
					Controlor.sleep(1000);  
			}  catch (IOException e) {
				e.printStackTrace();
				status = STATUS.finished;
			}
			// 退出发送线程  不做退出处理 socket一直保持状态
			// status = STATUS.finished;
		}
	}


	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket sc = new Socket("115.29.198.101",8988);
		OutputStream os;
		os = sc.getOutputStream();
//		PrintWriter out = new PrintWriter(sc.getOutputStream());				
		String src = "7e01020001013055773110000139087e";
		os.write(Global.HexString2Bytes(src)); 
		
		
	}
}


