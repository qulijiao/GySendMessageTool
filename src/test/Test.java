package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test {
	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream("d:\\test.txt");
		   try{
	            int len;//��ʾ��������ݣ�ʮ���Ƶ���ʽ��ʾ��	          
	            while((len = is.read())!=-1){
	                 System.out.println("len="+(char)len); 
	           }
	           is.close();
	     }catch(Exception e){
	            e.printStackTrace();
	     }
	}
}
