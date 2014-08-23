package Gy.business;

public class MessageStatus {
	public enum SENDMSGSTATUS {
        beginning,sending,sended,successed,finished;
    }
	public enum RECEIVEMSGSTATUS {
        recieved,Analysising,answering,answered,successed,removing,finished;
    }
	//统一为消息状态
	public enum STATUS {
        newmsg,sengding,sended,sendsuccessed,recieved,recAnalysising,recanswering,recremoving,finished;
    }
	
}
