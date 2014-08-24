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
//	gbcview.insets = new Insets(0, 0, 0, 10); //间隔
//	gbcview.fill = GridBagConstraints.BOTH;  		//是用来控制添加进的组件的显示位置
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
	GridBagConstraints gbcview = new GridBagConstraints(); //定义一个GridBagConstraints，
	gbcview.insets = new Insets(0, 0, 0, 10); //间隔
	gbcview.fill = GridBagConstraints.BOTH;  		//是用来控制添加进的组件的显示位置
	gbcview.anchor= GridBagConstraints.WEST;
	jpanelMsgView.setLayout(layoutview);
	
	
	JTabbedPane jTabbedpane = new JTabbedPane();//TAB 存放选项卡的组件
	jTabbedpane.addTab("test",null,jpanelMsgView,"first");  //添加查看消息panel
//	jTabbedpane.setMnemonicAt(1, KeyEvent.VK_F); //第2个tab页快捷键为F
	
	//消息查看 界面 ui元素定义 
	JButton btnstart = new JButton("开始");
 
	JLabel jlabelSendMsg = new JLabel("发送消息:");		 
	JTextArea strSendingMSG = new JTextArea(10,43);
	strSendingMSG.setLineWrap(true);			//自动换行
	JScrollPane jscrolSend = new JScrollPane(strSendingMSG);
	JLabel jlabelRecMsg = new JLabel("接收消息:");
	JTextArea strReceiveMSG = new JTextArea(10,43);
	strReceiveMSG.setLineWrap(true);			//自动换行	
	JScrollPane jscrolrec = new JScrollPane(strReceiveMSG); 
	JLabel jlabelUIadjust = new JLabel("ASDsd");    //界面排版使用
	jpanelMsgView.add(jlabelSendMsg); 
	jpanelMsgView.add(jscrolSend); //添加滚动条
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
    layoutview.setConstraints(jscrolSend, gbcview);     //滚动条
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=0;        
    layoutview.setConstraints(jlabelRecMsg, gbcview);  
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=0;        
    layoutview.setConstraints(strReceiveMSG, gbcview); 
    //下面这个用来调整UI
    gbcview.gridwidth=0;
    gbcview.weightx = 0;
    gbcview.weighty=1;        
    layoutview.setConstraints(jlabelUIadjust, gbcview);         
	gbcview.fill = GridBagConstraints.NONE;  		//是用来控制添加进的组件的显示位置
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
