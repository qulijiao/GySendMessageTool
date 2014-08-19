package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestQueue {
public static void main(String[] args) {
	Queue<String> queue = new LinkedList<String>();  
    queue.offer("Hello");  
    queue.offer("World!");  
    queue.offer("ÄãºÃ£¡");  
//    System.out.println(queue.size());  
    String str;  
//    while((str=queue.poll())!=null){  
//        System.out.print(str);  
//    }  
    while (!queue.isEmpty()) {
//    	System.out.println(queue.size());  
    	   System.out.println(queue.remove());
    	  }
    System.out.println();  
    
    List<String> list = new ArrayList<String>();
    list.add("111111111111111");
    list.add("2");
    list.add("3");
    System.err.println(list.get(0));
    list.remove(0);
    System.err.println("---"+list.get(0));
    list.remove(0);
    System.err.println("---"+list.get(0));
    list.add("4");
    list.remove(0);
    System.err.println("---"+list.get(0)); 
}
}
