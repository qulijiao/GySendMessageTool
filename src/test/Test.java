package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test {
	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream("d:\\test.txt");
		   try{
	            int len;//表示读入的数据（十进制的形式表示）	          
	            while((len = is.read())!=-1){
	                 System.out.println("len="+(char)len); 
	           }
	           is.close();
	     }catch(Exception e){
	            e.printStackTrace();
	     }
	}
}
