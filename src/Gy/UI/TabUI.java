package Gy.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Gy.business.Message;

public class TabUI extends JFrame {
	TabUI tabui;
	//1.消息变量定义
	JTextField strIP, strPORT,strSendCount;		//定义IP ,端口 ,消息发送次数 	
	// 消息发送、接收窗体 
	JTextArea strSendingMSG = new JTextArea(10,69);  //这里如果太大滚动条显示的时候会有奇怪问题
	JTextArea strReceiveMSG = new JTextArea(10,69);
	
	JTextArea strRegeditMsg;
	JTextField strProvince,strCity,strManufacturer,strType,strClientID,strColor,strPlateNO;
	JTextField strAuthcode;
	JTextField GPSsendFrequency;   //0x0200发送频率
	JCheckBox jcheckGPSsend ;
	public boolean isRunning = false;				//是否开启收发线程 
	private boolean isSendGps =false;


	//2.界面控件定义	 		
	int frameheight =500 ,framewidth =800;
	Container maincontainer;						//获取Jframe容器变量  	
	JPanel mainJPanel = new JPanel();		    		//主面板用于放置tab控件	
    private JTabbedPane jTabbedpane = new JTabbedPane();//TAB 存放选项卡的组件
    private String[] tabNames = {"基础设置","查看消息","具体消息实例"};    
    JPanel jpanelSetting ,jpanelMsgView,jpanelMsgExample ;				//分页：设置界面 和消息查看 消息实例
    JButton btnstart,btnSendGps ,btnRegedit,btnAuth, btnGps0704 ;
    
    //3.消息实例 
    JTextArea JTSRegedit0x0100,JtaAuth0x0102 ,Jta0x0200 ,Jta0x0704;
    
    public void addSendMsg(String newMsg){
    	String strcurcontent = strSendingMSG.getText();
    	strSendingMSG.setText( strcurcontent+newMsg);
    }
    //手动发送gps消息 
	public boolean isSendGps() {
		return isSendGps;
	}
    //手动发送gps消息 	
	public void setSendGps(boolean isSendGps) {
		this.isSendGps = isSendGps;
	}
	
    public String getIP()  {
		return strIP.getText();
	}
	public void setIP(String strIP) {
		this.strIP.setText(strIP);
	}
	public int getPort() { 
		int port = -1 ;
		String strport = strPORT.getText();
		try {
			port = Integer.parseInt(strport) ;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return port;
	}
	public void setPort(String strPORT) {
		this.strPORT.setText(strPORT);
	}
	public String getStrSendCount() {
		return strSendCount.getText();
	}
	public void setStrSendCount(String strSendCount) {
		this.strSendCount.setText( strSendCount);
	} 
	public String getStrSendingMSG() {
		return this.strSendingMSG.getText();
	}
	public void setStrSendingMSG(String strSendingMSG) {
		this.strSendingMSG.setText( strSendingMSG);
	}
	public String getStrReceiveMSG() {
		return strReceiveMSG.getText();
	}
	public void setStrReceiveMSG(String strReceiveMSG) {
		this.strReceiveMSG.setText( strReceiveMSG);
	}
	
	public int getGPSsendFrequency(){
		String strgpsfrequency = GPSsendFrequency.getText();
		int frequency = 0;
		try {			
			  frequency = Integer.parseInt(strgpsfrequency);
		} catch (Exception e) {
			return 30;
		}
		return frequency ; 
	}
	public boolean isSendGPSMsg(){		
		return this.jcheckGPSsend.isSelected();
	}

    public TabUI(){
    	tabui =this;
    	
    	//1.基础设置 内容设计
    	jpanelSetting = new JPanel();
    	jpanelSetting.setSize(framewidth,frameheight);
    	GridBagLayout layoutsetting = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints(); //定义一个GridBagConstraints，
    	gbc.insets = new Insets(0, 0, 0, 10); //间隔
    	gbc.fill = GridBagConstraints.NONE;  		//是用来控制添加进的组件的显示位置
    	gbc.anchor= GridBagConstraints.WEST;
    	jpanelSetting.setLayout(layoutsetting);
    	JLabel jlabelProvince = new JLabel("省ID:");
    	strProvince= new JTextField(10);
    	JLabel jlabelCity = new JLabel("市ID:");
    	strCity= new JTextField(10);   
    	JLabel jlabelManufacturer = new JLabel("制造商ID:");
    	strManufacturer= new JTextField(10); 
    	JLabel jlabelType = new JLabel("终端型号:");
    	strType= new JTextField(10); 
    	JLabel jlabelClientID = new JLabel("终端ID:");
    	strClientID= new JTextField(10);     	    	
    	JLabel jlabelColor = new JLabel("车牌颜色:");
    	strColor= new JTextField(10);  
    	JLabel jlabelPlateNO = new JLabel("车牌号码:");
    	strPlateNO= new JTextField(10);      	
    	JLabel jlabelAuthcode  = new JLabel("鉴权码:");
    	strAuthcode = new JTextField(10);
    	JLabel jlabelGPSsend  = new JLabel("0x0200发送频率:");
    	GPSsendFrequency = new JTextField(10);   
    	jcheckGPSsend = new JCheckBox("是否发送0x0200");
    	jpanelSetting.add( jlabelProvince  );
    	jpanelSetting.add( strProvince  );
    	jpanelSetting.add( jlabelCity  );
    	jpanelSetting.add( strCity );
    	jpanelSetting.add( jlabelManufacturer  );
    	jpanelSetting.add( strManufacturer );
    	jpanelSetting.add( jlabelType  );
    	jpanelSetting.add( strType );
    	jpanelSetting.add( jlabelClientID  );
    	jpanelSetting.add( strClientID );
    	jpanelSetting.add( jlabelColor  );
    	jpanelSetting.add( strColor );
    	jpanelSetting.add( jlabelPlateNO  );
    	jpanelSetting.add( strPlateNO );    	
    	jpanelSetting.add( jlabelAuthcode  );
    	jpanelSetting.add( strAuthcode );
    	//添加0200发送频率：
    	jpanelSetting.add( jlabelGPSsend  );
    	jpanelSetting.add( GPSsendFrequency );
    	jpanelSetting.add( jcheckGPSsend );    	
    	
    	
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelProvince, gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(strProvince, gbc);  
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelCity, gbc);
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(strCity, gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelManufacturer, gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints( strManufacturer , gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelType , gbc);
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints( strType , gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelClientID , gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints( strClientID, gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelColor , gbc);
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(strColor , gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelPlateNO , gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(strPlateNO , gbc);        
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelAuthcode , gbc);
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(strAuthcode , gbc);       	
    	//添加0200发送频率设置:
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jlabelGPSsend , gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(GPSsendFrequency , gbc);         
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0; 
        layoutsetting.setConstraints(jcheckGPSsend , gbc);          
        
    	JLabel jlabelIP = new JLabel("IP地址:");
    	JLabel jlabelPort = new JLabel("tcp端口:");
		strIP = new JTextField(15);
		strPORT = new JTextField(5);
		JLabel jlabelsendcount = new JLabel("发送次数:");
		strSendCount = new JTextField(10);
		jpanelSetting.add(jlabelIP)  ; 
		jpanelSetting.add(strIP  )   ;
		jpanelSetting.add(jlabelPort); 
		jpanelSetting.add(strPORT  ) ;  
		jpanelSetting.add( jlabelsendcount );
		jpanelSetting.add( strSendCount );  
        
        
		JLabel jlabelRegeditMsg = new JLabel("请输入鉴权消息0x0102内容:");
		strRegeditMsg = new JTextArea( 3, 40);
		strRegeditMsg.setAutoscrolls(true); 
		strRegeditMsg.setLineWrap(true);   //允许文本框换行显示
		jpanelSetting.add(jlabelRegeditMsg);
		jpanelSetting.add(strRegeditMsg);
		
		JButton btnRegedit = new JButton("注册");
		JButton btnAuthentication = new JButton("鉴权");
		JButton btnSettingOK = new JButton("确认");
		jpanelSetting.add(btnRegedit );
		jpanelSetting.add( btnAuthentication);
		jpanelSetting.add(btnSettingOK );
		
        gbc.gridwidth=1; //该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个        
        gbc.weightx = 0; //该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.weighty=0;   //该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间        
        layoutsetting.setConstraints(jlabelsendcount, gbc);//设置组件
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0;   
        layoutsetting.setConstraints(strSendCount, gbc);
        
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0;  
        layoutsetting.setConstraints(jlabelIP, gbc);        
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0;          
        layoutsetting.setConstraints(strIP, gbc);
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0;          
        layoutsetting.setConstraints(jlabelPort, gbc);        
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0;          
        layoutsetting.setConstraints(strPORT, gbc);
        
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0;           
        layoutsetting.setConstraints(jlabelRegeditMsg, gbc);        
        gbc.gridwidth=0;
        gbc.weightx = 0;
        gbc.weighty=0;
        layoutsetting.setConstraints(strRegeditMsg, gbc);        
//        gbc.fill = GridBagConstraints.BOTH;  
        gbc.anchor= GridBagConstraints.CENTER;
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0;        
        layoutsetting.setConstraints(btnRegedit, gbc);  
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0;        
        layoutsetting.setConstraints(btnAuthentication, gbc);  
                
        gbc.gridwidth=1;
        gbc.weightx = 0;
        gbc.weighty=0;        
        layoutsetting.setConstraints(btnSettingOK, gbc);  
          
        
		jpanelSetting.add(btnSettingOK);
		//添加按钮监听事件
		btnSettingOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.err.println(strIP.getText()); 
			}
		});		
		jTabbedpane.addTab(tabNames[0],null,jpanelSetting,"SETTING");
		jTabbedpane.setMnemonicAt(0, KeyEvent.VK_S); //第一个tab页快捷键为S
		
 //2.查看消息 --------------------------------------------------------------- 
		jpanelMsgView = new JPanel();
		jpanelMsgView.setSize(framewidth,frameheight);
    	GridBagLayout layoutview = new GridBagLayout();
    	GridBagConstraints gbcview = new GridBagConstraints(); //定义一个GridBagConstraints，
    	gbcview.insets = new Insets(0, 0, 0, 10); //间隔
    	gbcview.fill = GridBagConstraints.BOTH;  		//是用来控制添加进的组件的显示位置
    	gbcview.anchor= GridBagConstraints.WEST;
    	jpanelMsgView.setLayout(layoutview);
    	
    	
    	
		jTabbedpane.addTab(tabNames[1],null,jpanelMsgView,"first");  //添加查看消息panel
		jTabbedpane.setMnemonicAt(1, KeyEvent.VK_F); //第2个tab页快捷键为F
		
		//消息查看 界面 ui元素定义 
		btnstart = new JButton("开始");
		btnstart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				strReceiveMSG.setText("");
				isRunning = true;
				tabui.validate(); 
			}
		});
		btnSendGps = new JButton("发送0x0200");
		btnSendGps.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				setSendGps(true);     //设置为发送
				btnSendGps.setEnabled(false);  //按钮置灰
				tabui.validate(); 
			}
		});		
//		btnRegedit,btnAuth
		btnRegedit = new JButton("注册0x0100");
		btnRegedit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//JTSRegedit0x0100,JtaAuth0x0102 ,Jta0x0200 ,Jta0x0704;
				Message regeditmsg = new Message(JTSRegedit0x0100.getText());
				addSendMsg(regeditmsg.getMsgContent()); //添加注册消息
			}
		});	
		btnAuth = new JButton("鉴权0x0102");
		btnAuth.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Message regeditmsg = new Message(JtaAuth0x0102.getText());
				addSendMsg(regeditmsg.getMsgContent()); //添加鉴权消息				
			}
		});			
		btnGps0704 = new JButton("盲区补报0x0704");
		btnGps0704.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				System.err.println(Jta0x0704.getText());
				Message regeditmsg = new Message(Jta0x0704.getText());
//				System.err.println(regeditmsg);
				addSendMsg(regeditmsg.getMsgContent()); //添加鉴权消息				
			}
		});				
		
		JLabel jlabelSendMsg = new JLabel("待发送消息:");		  
		strSendingMSG.setLineWrap(true);			//自动换行
		JScrollPane jscrolSend = new JScrollPane(strSendingMSG);
		JLabel jlabelRecMsg = new JLabel("已接收消息:"); 
		strReceiveMSG.setLineWrap(true);			//自动换行	
		JScrollPane jscrolrec = new JScrollPane(strReceiveMSG); 
		JLabel jlabelUIadjust = new JLabel("");    //界面排版使用
		jpanelMsgView.add(jlabelSendMsg); 
		jpanelMsgView.add(jscrolSend); //添加滚动条
		jpanelMsgView.add(jlabelRecMsg) ; 
		jpanelMsgView.add(jscrolrec);	
		jpanelMsgView.add(jlabelUIadjust);
		JPanel jpanelBottons = new JPanel();   //按钮界面排版使用
//		jpanelBottons.setBackground(Color.red);
		jpanelMsgView.add(jpanelBottons);    //按钮界面排版

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
        //按钮panel处理：
	    	GridBagLayout layout4buttons = new GridBagLayout();
	    	GridBagConstraints gbc4buttons = new GridBagConstraints(); //定义一个GridBagConstraints，
	    	gbc4buttons.insets = new Insets(0, 0, 0, 10); //间隔
	    	gbc4buttons.fill = GridBagConstraints.NONE;  		//是用来控制添加进的组件的显示位置
	    	gbc4buttons.anchor= GridBagConstraints.EAST;
	    //按钮界面单独增加一个jpanel排版 -------
	    	jpanelBottons.setLayout(layout4buttons);
	    	//JTSRegedit0x0100,JtaAuth0x0102 ,Jta0x0200 ,Jta0x0704;
	    	//JButton btnstart,btnSendGps ,btnRegedit,btnAuth;
	    	jpanelBottons.add(btnRegedit);
	    	jpanelBottons.add(btnAuth);
	        jpanelBottons.add(btnSendGps);   //发送0x0200按钮
	        jpanelBottons.add(btnGps0704);
			jpanelBottons.add(btnstart);
			
			gbc4buttons.gridwidth=1;
			gbc4buttons.weightx = 1;
			gbc4buttons.weighty=0;        
			layout4buttons.setConstraints(btnRegedit, gbc4buttons);  
			gbc4buttons.gridwidth=1;
			gbc4buttons.weightx = 0;
			gbc4buttons.weighty=0;        
			layout4buttons.setConstraints(btnAuth, gbc4buttons);  
			
			gbc4buttons.gridwidth=1;
			gbc4buttons.weightx =0;
			gbc4buttons.weighty=0;        
			layout4buttons.setConstraints(btnSendGps, gbc4buttons);  
			
			gbc4buttons.gridwidth=1;
			gbc4buttons.weightx =0;
			gbc4buttons.weighty=0;        
			layout4buttons.setConstraints(btnGps0704, gbc4buttons);			
			gbc4buttons.gridwidth=0;
			gbc4buttons.weightx = 0;
			gbc4buttons.weighty=0;        
			layout4buttons.setConstraints(btnstart, gbc4buttons);  			
        gbcview.gridwidth=0;
        gbcview.weightx = 0;
        gbcview.weighty=0;        
        layoutview.setConstraints(jpanelBottons, gbcview);         

//3.添加具体消息格式----------------------------------------------------------        
		jpanelMsgExample = new JPanel();
		jpanelMsgExample.setSize(framewidth,frameheight);
		//jpanelMsgExample.setBackground(Color.BLUE);
    	GridBagLayout layoutviewmsgexample = new GridBagLayout();
    	GridBagConstraints gbcview4exmaple = new GridBagConstraints(); //定义一个GridBagConstraints，
//    	gbcview4exmaple.insets = new Insets(0, 0, 0, 10); //间隔
    	gbcview4exmaple.fill = GridBagConstraints.BOTH;  		//是用来控制添加进的组件的显示位置
    	gbcview4exmaple.anchor= GridBagConstraints.WEST;
    	jpanelMsgExample.setLayout(layoutviewmsgexample);
//    	JTextArea JTSRegedit0x0100,JtaAuth0x0102 ,Jta0x0200 ,Jta0x0704;
    	JLabel jlabel0100 = new JLabel("0x0100:");
    	JLabel jlabel0102 = new JLabel("0x0102:");
    	JLabel jlabel0200 = new JLabel("0x0200:");
    	JLabel jlabel0704 = new JLabel("0x0704:");
    	JTSRegedit0x0100= new JTextArea(1,40);   	     	
    	JtaAuth0x0102= new JTextArea(1,40);
    	Jta0x0200= new JTextArea(3,70);
    	Jta0x0704= new JTextArea(6,70);    	
    	JTSRegedit0x0100.setLineWrap(true);
    	JtaAuth0x0102.setLineWrap(true);
    	Jta0x0200.setLineWrap(true);
    	Jta0x0704.setLineWrap(true);
    	jpanelMsgExample.add(jlabel0100);
    	jpanelMsgExample.add(JTSRegedit0x0100 );
    	jpanelMsgExample.add(jlabel0102);
    	jpanelMsgExample.add( JtaAuth0x0102);
    	jpanelMsgExample.add(jlabel0200);
    	jpanelMsgExample.add( Jta0x0200);
    	jpanelMsgExample.add(jlabel0704);
    	jpanelMsgExample.add(Jta0x0704 );
    	//0x0100
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(jlabel0100, gbcview4exmaple);     
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(JTSRegedit0x0100, gbcview4exmaple);     
    	//0102
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(jlabel0102, gbcview4exmaple);     
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(JtaAuth0x0102, gbcview4exmaple);     
    	//0200
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(jlabel0200, gbcview4exmaple);     
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(Jta0x0200, gbcview4exmaple);     

    	//0704
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(jlabel0704, gbcview4exmaple);     
    	gbcview4exmaple.gridwidth=0;
    	gbcview4exmaple.weightx = 0;
    	gbcview4exmaple.weighty=0;        
    	layoutviewmsgexample.setConstraints(Jta0x0704, gbcview4exmaple);     
    	    	    	    	    	    	
    	
    	
		jTabbedpane.addTab(tabNames[2],null,jpanelMsgExample,"third");  //添加查看消息panel
		jTabbedpane.setMnemonicAt(1, KeyEvent.VK_X); //第2个tab页快捷键为F
		        
		//3.添加到主jpanel 
		mainJPanel.setSize(framewidth, frameheight);
		mainJPanel.setBackground(Color.yellow);
		mainJPanel.setLayout(new GridLayout(1, 1)); 
		mainJPanel.add(jTabbedpane);
		
		//4.添加到主容器中
		maincontainer = this.getContentPane();
		maincontainer.setLayout(null);
		maincontainer.add(mainJPanel); 
		init();
		setVisible(true);
		setSize(this.framewidth+15, this.frameheight+39);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//5. 设置默认分页
		jTabbedpane.setSelectedComponent(jpanelMsgView);
		
    	
    }
    private void init(){
    	strIP.setText("www.baidu.com");
//    	strIP.setText("192.168.1.180");
    	strPORT.setText("8988");
    	strPORT.setText("80");
//    	strIP.setText("115.29.198.101");
    	strSendCount.setText("1"); 	
//    	strSendingMSG.setText(""); 
//    	strReceiveMSG.setText(""); 
    	strRegeditMsg.setText("7e01020001013055773110000139087e");   //注册消息   	
    	strProvince.setText("0011");  
    	strCity.setText("0000");
    	strManufacturer.setText("GY001");
    	strType.setText("Type0001");
    	strClientID.setText("0000008");
    	strColor.setText("11");
    	strPlateNO.setText("闽A-00008");
    	strAuthcode.setText("8");
    	//0x0200 发送频率
    	GPSsendFrequency.setText("30");
    	
    	strSendingMSG.setText("7e01020001013055773110000139087e"+
    			"7e02000073013055773110000200000000000c0002018e5fed071b926b00000000000014082222560001040000000002020000030200002504000000002b0400000000300100310100e0020066e13100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000827e");
//    	strReceiveMSG.setText("7e01020001013055773110000139087e"+"7e02000073013055773110000200000000000c0002018e5fed071b926b00000000000014082222560001040000000002020000030200002504000000002b0400000000300100310100e0020066e13100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000827e");
//    	JTSRegedit0x0100,JtaAuth0x0102 ,Jta0x0200 ,Jta0x0704
    	JTSRegedit0x0100.setText("7e01000001013055773110000139087e");
    	JtaAuth0x0102.setText("7e01020001013055773110000139087e");
    	Jta0x0200.setText("7e02000073013055773110000200000000000c0002018e5fed071b926b00000000000014082222560001040000000002020000030200002504000000002b0400000000300100310100e0020066e13100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000827e");
    	Jta0x0704.setText("7e0704-----");
    }
	public void startSending( ){ 
//		System.err.println("按钮置灰"+btnstart);		
		btnstart.setEnabled(false);
		this.validate(); //刷新界面
	}
	public void finishSending( ){ 
		isRunning=false;
		btnstart.setEnabled(true);
		this.validate();
	} 
	public void reflashUI( ){ 
		btnstart.setEnabled(!isRunning);
		this.validate();
		if (!isSendGps) {  //如果已发送0200则按钮恢复
			btnSendGps.setEnabled(true);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TabUI();
	}

}
