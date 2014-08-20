package test.Thread;

import java.util.LinkedList;
import java.util.Queue;

import Gy.business.SendMessage;

public class TestSync   {
static Queue queue ;
static String strpublic ;  
public TestSync(){
	queue =new LinkedList<String>();
	queue.add("1");
	queue.add("2");
	strpublic="TestSync";
}
 
public static void main(String[] args) {
	TestSync ts = new TestSync(); 
 
//	queue.remove();
//	queue.remove();
//	TestSendThread tsend = new TestSendThread();
//	TestAddThread tadd = new TestAddThread();
//	TestSend2 tsend2 = new TestSend2();
//	new Thread(tsend).start();
//	new Thread(tadd).start();
//	new Thread(tsend2).start();
    TicketSouce mt=new TicketSouce();
    //基于火车票创建三个窗口
    new Thread(mt,"a").start();
    new Thread(mt,"b").start();
    new Thread(mt,"c").start();
    new Thread(mt,"d").start();
    new Thread(mt,"e").start();
    new Thread(mt,"f").start();
    
}	

}
