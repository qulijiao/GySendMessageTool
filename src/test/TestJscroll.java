package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class TestJscroll extends JFrame {
public TestJscroll(){
//	JPanel jpanelMsgView = new JPanel();
//	jpanelMsgView.setBackground(Color.BLUE);
//	jpanelMsgView.setSize(300,300);
//	JTextArea jta = new JTextArea(4,14);
//	JScrollPane js = new JScrollPane(jta);
//	jpanelMsgView.add(js);	
//	
//	GridBagLayout layoutview = new GridBagLayout();
//	GridBagConstraints gbcview = new GridBagConstraints();	
//	jpanelMsgView.setLayout(layoutview);
//	gbcview.insets = new Insets(0, 0, 0, 10); //���
//	gbcview.fill = GridBagConstraints.BOTH;  		//������������ӽ����������ʾλ��
//	gbcview.anchor= GridBagConstraints.WEST;
//	gbcview.gridwidth=0;
//	gbcview.weightx = 0;
//	gbcview.weighty=0;    
//    layoutview.setConstraints(jpanelMsgView, gbcview);  
//
//	
	
	
	
//	Container cp;
//	cp = getContentPane();
//	cp.setLayout(null);
//	cp.add(jpanelMsgView);
	
	int framewidth = 400;
	int frameheight=400;
	JPanel jpanelMsgView = new JPanel();
	jpanelMsgView.setSize(framewidth,frameheight);
	GridBagLayout layoutview = new GridBagLayout();
	GridBagConstraints gbcview = new GridBagConstraints(); //����һ��GridBagConstraints��
	gbcview.insets = new Insets(0, 0, 0, 10); //���
	gbcview.fill = GridBagConstraints.BOTH;  		//������������ӽ����������ʾλ��
	gbcview.anchor= GridBagConstraints.WEST;
	jpanelMsgView.setLayout(layoutview);
	
	
	JTabbedPane jTabbedpane = new JTabbedPane();//TAB ���ѡ������
	jTabbedpane.addTab("test",null,jpanelMsgView,"first");  //��Ӳ鿴��Ϣpanel
//	jTabbedpane.setMnemonicAt(1, KeyEvent.VK_F); //��2��tabҳ��ݼ�ΪF
	
	//��Ϣ�鿴 ���� uiԪ�ض��� 
	JButton btnstart = new JButton("��ʼ");
 
	JLabel jlabelSendMsg = new JLabel("������Ϣ:");		 
	JTextArea strSendingMSG = new JTextArea(10,43);
	strSendingMSG.setLineWrap(true);			//�Զ�����
	JScrollPane jscrolSend = new JScrollPane(strSendingMSG);
	JLabel jlabelRecMsg = new JLabel("������Ϣ:");
	JTextArea strReceiveMSG = new JTextArea(10,43);
	strReceiveMSG.setLineWrap(true);			//�Զ�����	
	JScrollPane jscrolrec = new JScrollPane(strReceiveMSG); 
	JLabel jlabelUIadjust = new JLabel("ASDsd");    //�����Ű�ʹ��
	jpanelMsgView.add(jlabelSendMsg); 
	jpanelMsgView.add(jscrolSend); //��ӹ�����
	jpanelMsgView.add(jlabelRecMsg) ; 
	jpanelMsgView.add(jscrolrec);	
//	jpanelMsgView.add(jlabelUIadjust);
//	jpanelMsgView.add(btnstart);
	gbcview.gridwidth=0;
	gbcview.weightx = 0;
	gbcview.weighty=0;    
    layoutview.setConstraints(jlabelSendMsg, gbcview);  
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=0;        
    layoutview.setConstraints(jscrolSend, gbcview);     //������
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=0;        
    layoutview.setConstraints(jlabelRecMsg, gbcview);  
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=0;        
    layoutview.setConstraints(strReceiveMSG, gbcview); 
    //���������������UI
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=1;        
    layoutview.setConstraints(jlabelUIadjust, gbcview);         
	gbcview.fill = GridBagConstraints.NONE;  		//������������ӽ����������ʾλ��
	gbcview.anchor= GridBagConstraints.EAST;
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=0;        
    layoutview.setConstraints(btnstart, gbcview);         
    
    Container maincontainer;	 
//    mainJPanel.add( );
    
	maincontainer = this.getContentPane();
	maincontainer.setLayout(null);
	maincontainer.add(jlabelSendMsg); 
	setVisible(true);
	setSize(315, 339);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public static void main(String[] args) {
	new TestJscroll();
}
}
