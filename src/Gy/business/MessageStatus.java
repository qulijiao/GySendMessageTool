package Gy.business;

public class MessageStatus {
	public enum SENDMSGSTATUS {
        beginning,sending,sended,successed,finished;
    }
	public enum RECEIVEMSGSTATUS {
        recieved,Analysising,answering,answered,successed,removing,finished;
    }
	//ͳһΪ��Ϣ״̬
	public enum STATUS {
        newmsg,sengding,sended,sendsuccessed,recieved,recAnalysising,recanswering,recremoving,finished;
    }
	
}
