package Gy.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Gy.business.Message;
import Gy.control.Global.STATUS;

public class SendThread implements Runnable {
	STATUS status = STATUS.idle;
	Socket sc;
	static int i = 0;
	int sendCount = 0;

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
		System.err.println("发送线程开启:" + status);
		while (status == STATUS.runing && i < sendCount) {
			i++;
			System.err.println("发送任务：" + i);
			Controlor.sleep(1000);
			try {
				OutputStream os;
				os = sc.getOutputStream();
				PrintWriter out = new PrintWriter(sc.getOutputStream());
				String src = "7e01020001013055773110000139087e";
				os.write(Global.HexString2Bytes(src));
				os.flush(); 
				Controlor.sleep(1000);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				status = STATUS.finished;
			} catch (IOException e) {
				e.printStackTrace();
				status = STATUS.finished;
			}
			// 退出发送线程
			if (i >= sendCount) {
				System.err.println("退出发送线程");
				i = 0;
				// 完成后设置状态为finished 
				status = STATUS.finished;
			}
		}
	}
}
