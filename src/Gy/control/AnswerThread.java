package Gy.control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Gy.business.Message;

public class AnswerThread implements Runnable {
	
	@Override
	public void run() {
		

	}
	static void testQ(Queue<String> queue2){
		for (String s :queue2 ) {
			System.err.println(s);
//			if (condition) {
//				
//			}
		}
	}
	public static void main(String[] args) {
//		Queue<String> queue = new LinkedList<String>();
//		queue.add("1");
//		queue.add("2");
//		queue.add("3");
		Queue<Message> list = new LinkedList<Message>();
		Message msg = new Message("aaa");
		list.add(msg);
		list.add(new Message("123"));
		list.remove(msg);
		System.err.println(list);
//		testQ(queue);
	}

}
