package test;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class Notepad {
 public static void main(String[] args)
 {
  JFrame jf=new JFrame();
  JPanel jp=new JPanel(); 
  JTextArea jt=new JTextArea();
  jt.setLineWrap(true);
  JScrollPane js=new JScrollPane(jt);
  jp.setLayout(new GridLayout(1,1));//加上这句
  jp.add(js);
  jf.add(jp); 
  //jf.add(jt);去掉这句
  jf.setSize(800,600);
  jf.setVisible(true);

 }

}