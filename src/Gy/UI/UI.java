package Gy.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Gy.control.Controlor;

public class UI extends JFrame {
	private UI ui ;
	public boolean isRunning = false;  //运行状态
	//界面元素定义：
	Container cp;
	JPanel p1, p2, p3, p4;
	public JTextField textip, textport,textTimes;
	public JButton btnstart,btnstop;
	public JTextArea areaMessage,areaResult;
	private JLabel labcount;
//	Controlor cl;
	
	//初始化 界面
	public UI() { 
		ui = this;
		cp = getContentPane();
		cp.setLayout(null);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p1.setSize(500, 350);
		// p1.setBackground(Color.BLUE);

		// 文本框
		areaMessage = new JTextArea(10, 40);
		areaResult= new JTextArea(10, 40);
		textip = new JTextField(10);
		textport = new JTextField(3);
		textTimes = new JTextField(4);
		btnstart = new JButton("开始");
		btnstop = new JButton("停止");
		btnstop.setEnabled(false);
		labcount = new JLabel("发送次数:");
//		btnSend.setEnabled(false);
		areaMessage.setLineWrap(true);   //允许文本框换行显示
		areaResult.setLineWrap(true);   //允许文本框换行显示
//		areaMessage.setText("7e02000073013055773110000200000ff00004000301914e5f0719ac39000000000000140702160746010");
//		areaMessage.setText("7e01020002013055773110002632391e7e");
//		areaMessage.setText("7e01020002013055773110002630391e7e");
		areaMessage.setText("7e01020001013055773110000139087e");		
//		textip.setText("192.168.1.180");
		textip.setText("115.29.198.101");		
		textport.setText("8988");
		textTimes.setText("1");
		//添加按钮监听事件
		btnstart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				areaResult.setText("");
				isRunning = true;
				ui.validate();
//				System.err.println(ui.getPort());
			}
		});
		btnstop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				areaResult.setText("");
//				isRunning = false;
				ui.validate();
//				System.err.println(ui.getPort());
			}
		});		
		p1.add(areaMessage);
		p1.add(areaResult);
		
		p2.add(textip);
		p2.add(textport);
		p3.add(labcount);
		p3.add(textTimes);
		
		p4.add(btnstart);
		p4.add(btnstop);
		
		
		//位置调整
		p2.setBounds(0, 350, 200, 30);
		p3.setBounds(200, 350, 125, 30);
		p4.setBounds(325, 350, 175, 30);
		p2.setBackground(Color.yellow);
		p3.setBackground(Color.green);
		p4.setBackground(Color.red);
		cp.add(p1);
		cp.add(p2);
		cp.add(p3);
		cp.add(p4);

		setSize(500, 500);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		System.err.println(area.getText());
	}

	public void reflashUI( ){ 
		btnstart.setEnabled(!isRunning);
		this.validate();
	}

	public void startSending( ){ 
		btnstart.setEnabled(false);
		this.validate(); //刷新界面
	}
	public void finishSending( ){ 
		isRunning=false;
		btnstart.setEnabled(true);
		this.validate();
	}	
	
	public String getIP(){
		return textip.getText();
	}
	public int getPort(){
		int port = -1 ;
		String strport = textport.getText();
		try {			
			port = Integer.parseInt(strport) ;
		} catch (Exception e) {
			System.err.println("error");
			
		}
		return port;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui =new UI();
		
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return this.areaMessage.getText();
	} 
	public int getSendCount(){
		int count ;
		try {			
			count =Integer.valueOf(textTimes.getText());
		} catch (Exception e) {
			count = 1;
		}
		return count;
	}

}