package Gy.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import Gy.business.Message;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	List<String> messageList = new ArrayList<String>();  //消息队列
	STATUS status = STATUS.idle;
	Socket sc;
	static int i = 0;
	static int loopsendcount = 3;
	int sendCount = 0;
	boolean isRegedit = true ; //是否发送鉴权消息

	public void setSendCount(int count) {
		if (count < 1) {
			sendCount = 1;
		}
		sendCount = count;
	}

	public void setSocket(Socket sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		status = STATUS.runing;
		System.err.println("发送线程开启:" + status+" 连接:"+sc.getInetAddress());		
		while (status == STATUS.runing ) {
			i++;
			System.err.println("发送任务：" + i); 
			OutputStream os=null;
			try {
				os = sc.getOutputStream();
//				PrintWriter out = new PrintWriter(sc.getOutputStream());
				if (isRegedit) {	
					String src = "7e01020001013055773110000139087e";
					System.err.println("发送鉴权消息："+src);
					os.write(Global.HexString2Bytes(src));
					os.flush();
					isRegedit= false;
					Controlor.sleep(1000);
				}
				//获取单条消息 
				String sendmessage = getMessage( ); 
//				while (sendmessage != "") {				
				while(loopsendcount>0){
					System.err.println("发送内容次数:"+loopsendcount+" ,msg="+sendmessage);
					os.write(Global.HexString2Bytes(sendmessage));
					os.flush();	
//					sendmessage = getMessage();
					Controlor.sleep(5000);
					loopsendcount--;
				}
				System.err.println("完成发送循环");
			} catch (UnknownHostException e) {
				e.printStackTrace();
				status = STATUS.finished;
			} catch (IOException e) {
				e.printStackTrace();
				status = STATUS.finished;
			}
			// 退出发送线程
			if (true) {
				System.err.println("退出发送线程");
				i = 0;
				// 完成后设置状态为finished 
				status = STATUS.finished;
			}
//			try {
//				System.err.println("关闭outputscream");
//				os.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	public void setMessage(List<String> readMessages) {
		messageList.addAll(readMessages);
		System.err.println(messageList); 
	}
	private String getMessage(){
		if (messageList.size()<1) {
			return "";
		}
		String result=messageList.get(0);		
		messageList.remove(0);
		return result;
	}
	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket sc = new Socket("115.29.198.101",8988);
		OutputStream os;
		os = sc.getOutputStream();
//		PrintWriter out = new PrintWriter(sc.getOutputStream());				
		String src = "7e01020001013055773110000139087e";
		os.write(Global.HexString2Bytes(src));
		System.err.println(sc.isClosed());
		os.flush();
		System.err.println(os);
//		os.close();
		System.err.println(sc);
//		sc.close();
		System.err.println(sc);
		Controlor.sleep(3000);
		System.err.println(sc.isConnected());
		System.err.println(sc.isClosed());
		
		
		
	}
}


