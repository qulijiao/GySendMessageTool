package Gy.UI;

import java.awt.Color;
import java.awt.Container; 

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 

public class UI extends JFrame {
	private UI ui ;
	public boolean isRunning = false;  //����״̬
	//����Ԫ�ض��壺
	Container cp;
	JPanel p1, p2, p3, p4;
	public JTextField textip, textport,textTimes;
	public JButton btnstart,btnstop;
	public JTextArea areaMessage,areaResult;
	private JLabel labcount;
//	Controlor cl;
	
	//��ʼ�� ����
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

		// �ı���
		areaMessage = new JTextArea(10, 40);
		areaResult= new JTextArea(10, 40);
		textip = new JTextField(10);
		textport = new JTextField(3);
		textTimes = new JTextField(4);
		btnstart = new JButton("��ʼ");
		btnstop = new JButton("ֹͣ");
		btnstop.setEnabled(false);
		labcount = new JLabel("���ʹ���:");
//		btnSend.setEnabled(false);
		areaMessage.setLineWrap(true);   //�����ı�������ʾ
		areaResult.setLineWrap(true);   //�����ı�������ʾ
//		areaMessage.setText("7e02000073013055773110000200000ff00004000301914e5f0719ac39000000000000140702160746010");
//		areaMessage.setText("7e01020002013055773110002632391e7e");
		areaMessage.setText("7e010200010130557731100002390b7e");  //��Ȩ
		//ע�᣺
//		areaMessage.setText("7e0100002d0130557731100001000b000047593030315479706530303031000000000000000000000000303030303030390bc3f64130303030397d7e");
//		areaMessage.setText("7e01020001013055773110000139087e");
//		areaMessage.setText("7e0200003c0183591014613a0000000000008400030158733806cd1ffe00000016014a14081317064001040000004d2b04000001f4030200004b04464646462403000000250400000000300131dc7e"); //�� 9�� 
		
//		textip.setText("192.168.1.180");
		textip.setText("115.29.198.101");
//		textip.setText("guyi2013.vicp.cc");
//		textport.setText("7888");
		textport.setText("8988");
		textTimes.setText("1");
		//��Ӱ�ť�����¼�
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
		
		
		//λ�õ���
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
		this.validate(); //ˢ�½���
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

	public String getStrSendingMSG() {
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