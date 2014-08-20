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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TabUI extends JFrame {
	TabUI tabui;
	//1.��Ϣ��������
	JTextField strIP, strPORT,strSendCount;		//����IP ,�˿� ,��Ϣ���ʹ��� 	
	JTextArea strSendingMSG, strReceiveMSG; 	    	// ��Ϣ���͡����մ��� 
	JTextArea strRegeditMsg;
	JTextField strProvince,strCity,strManufacturer,strType,strClientID,strColor,strPlateNO;
	JTextField strAuthcode;
	JTextField GPSsendFrequency;   //0x0200����Ƶ��
	JCheckBox jcheckGPSsend ;
	public boolean isRunning = false;				//�Ƿ����շ��߳� 
	//2.����ؼ�����	 		
	int frameheight =500 ,framewidth =500;
	Container maincontainer;						//��ȡJframe��������  	
	JPanel mainJPanel = new JPanel();		    		//��������ڷ���tab�ؼ�	
    private JTabbedPane jTabbedpane = new JTabbedPane();//TAB ���ѡ������
    private String[] tabNames = {"��������","�鿴��Ϣ"};    
    JPanel jpanelSetting ,jpanelMsgView ;				//��ҳ�����ý��� ����Ϣ�鿴
    JButton btnstart ;  //
    
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
    	
    	//1.�������� �������
    	jpanelSetting = new JPanel();
    	jpanelSetting.setSize(framewidth,frameheight);
    	GridBagLayout layoutsetting = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints(); //����һ��GridBagConstraints��
    	gbc.insets = new Insets(0, 0, 0, 10); //���
    	gbc.fill = GridBagConstraints.NONE;  		//������������ӽ����������ʾλ��
    	gbc.anchor= GridBagConstraints.WEST;
    	jpanelSetting.setLayout(layoutsetting);
    	JLabel jlabelProvince = new JLabel("ʡID:");
    	strProvince= new JTextField(10);
    	JLabel jlabelCity = new JLabel("��ID:");
    	strCity= new JTextField(10);   
    	JLabel jlabelManufacturer = new JLabel("������ID:");
    	strManufacturer= new JTextField(10); 
    	JLabel jlabelType = new JLabel("�ն��ͺ�:");
    	strType= new JTextField(10); 
    	JLabel jlabelClientID = new JLabel("�ն�ID:");
    	strClientID= new JTextField(10);     	    	
    	JLabel jlabelColor = new JLabel("������ɫ:");
    	strColor= new JTextField(10);  
    	JLabel jlabelPlateNO = new JLabel("���ƺ���:");
    	strPlateNO= new JTextField(10);      	
    	JLabel jlabelAuthcode  = new JLabel("��Ȩ��:");
    	strAuthcode = new JTextField(10);
    	JLabel jlabelGPSsend  = new JLabel("0x0200����Ƶ��:");
    	GPSsendFrequency = new JTextField(10);   
    	jcheckGPSsend = new JCheckBox("�Ƿ���0x0200");
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
    	//���0200����Ƶ�ʣ�
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
    	//���0200����Ƶ������:
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
        
    	JLabel jlabelIP = new JLabel("IP��ַ:");
    	JLabel jlabelPort = new JLabel("tcp�˿�:");
		strIP = new JTextField(15);
		strPORT = new JTextField(5);
		JLabel jlabelsendcount = new JLabel("���ʹ���:");
		strSendCount = new JTextField(10);
		jpanelSetting.add(jlabelIP)  ; 
		jpanelSetting.add(strIP  )   ;
		jpanelSetting.add(jlabelPort); 
		jpanelSetting.add(strPORT  ) ;  
		jpanelSetting.add( jlabelsendcount );
		jpanelSetting.add( strSendCount );  
        
        
		JLabel jlabelRegeditMsg = new JLabel("�������Ȩ��Ϣ0x0102����:");
		strRegeditMsg = new JTextArea( 3, 40);
		strRegeditMsg.setAutoscrolls(true); 
		strRegeditMsg.setLineWrap(true);   //�����ı�������ʾ
		jpanelSetting.add(jlabelRegeditMsg);
		jpanelSetting.add(strRegeditMsg);
		
		JButton btnRegedit = new JButton("ע��");
		JButton btnAuthentication = new JButton("��Ȩ");
		JButton btnSettingOK = new JButton("ȷ��");
		jpanelSetting.add(btnRegedit );
		jpanelSetting.add( btnAuthentication);
		jpanelSetting.add(btnSettingOK );
		
        gbc.gridwidth=1; //�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��        
        gbc.weightx = 0; //�÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
        gbc.weighty=0;   //�÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��        
        layoutsetting.setConstraints(jlabelsendcount, gbc);//�������
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
		//��Ӱ�ť�����¼�
		btnSettingOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.err.println(strIP.getText()); 
			}
		});		
		jTabbedpane.addTab(tabNames[0],null,jpanelSetting,"SETTING");
		jTabbedpane.setMnemonicAt(0, KeyEvent.VK_S); //��һ��tabҳ��ݼ�ΪS
		
		//2.�鿴��Ϣ  
		jpanelMsgView = new JPanel();
		jpanelMsgView.setSize(framewidth,frameheight);
    	GridBagLayout layoutview = new GridBagLayout();
    	GridBagConstraints gbcview = new GridBagConstraints(); //����һ��GridBagConstraints��
    	gbcview.insets = new Insets(0, 0, 0, 10); //���
    	gbcview.fill = GridBagConstraints.BOTH;  		//������������ӽ����������ʾλ��
    	gbcview.anchor= GridBagConstraints.WEST;
    	jpanelMsgView.setLayout(layoutview);
    	
    	
    	
		jTabbedpane.addTab(tabNames[1],null,jpanelMsgView,"first");  //��Ӳ鿴��Ϣpanel
		jTabbedpane.setMnemonicAt(1, KeyEvent.VK_F); //��2��tabҳ��ݼ�ΪF
		//��Ϣ�鿴 ���� uiԪ�ض��� 
		btnstart = new JButton("��ʼ");
		btnstart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				strReceiveMSG.setText("");
				isRunning = true;
				tabui.validate();
//				System.err.println(ui.getPort());
			}
		});
		JLabel jlabelSendMsg = new JLabel("������Ϣ:");		 
		strSendingMSG = new JTextArea(10,43);
		JLabel jlabelRecMsg = new JLabel("������Ϣ:");
		strReceiveMSG = new JTextArea(10,43);
		JLabel jlabelUIadjust = new JLabel("");    //�����Ű�ʹ��
		jpanelMsgView.add(jlabelSendMsg);
		jpanelMsgView.add(strSendingMSG);
		jpanelMsgView.add(jlabelRecMsg) ; 
		jpanelMsgView.add(strReceiveMSG);	
		jpanelMsgView.add(jlabelUIadjust);
		jpanelMsgView.add(btnstart);
		gbcview.gridwidth=0;
		gbcview.weightx = 0;
		gbcview.weighty=0;    
        layoutview.setConstraints(jlabelSendMsg, gbcview);  
        gbcview.gridwidth=0;
        gbcview.weightx = 0;
        gbcview.weighty=0;        
        layoutview.setConstraints(strSendingMSG, gbcview);   
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
        
		//3.��ӵ���jpanel 
		mainJPanel.setSize(framewidth, frameheight);
		mainJPanel.setBackground(Color.yellow);
		mainJPanel.setLayout(new GridLayout(1, 1)); 
		mainJPanel.add(jTabbedpane);
		
		//4.��ӵ���������
		maincontainer = this.getContentPane();
		maincontainer.setLayout(null);
		maincontainer.add(mainJPanel); 
		init();
		setVisible(true);
		setSize(this.framewidth+15, this.frameheight+39);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    }
    private void init(){
//    	strIP.setText("www.baidu.com");
//    	strIP.setText("192.168.1.180");
//    	strPORT.setText("80");
    	strIP.setText("115.29.198.101");
    	strPORT.setText("8988");
    	strSendCount.setText("1"); 	
//    	strSendingMSG.setText(""); 
//    	strReceiveMSG.setText(""); 
    	strRegeditMsg.setText("7e01020001013055773110000139087e");   //ע����Ϣ   	
    	strProvince.setText("0011");  
    	strCity.setText("0000");
    	strManufacturer.setText("GY001");
    	strType.setText("Type0001");
    	strClientID.setText("0000008");
    	strColor.setText("11");
    	strPlateNO.setText("��A-00008");
    	strAuthcode.setText("8");
    	//0x0200 ����Ƶ��
    	GPSsendFrequency.setText("30");
    }
	public void startSending( ){ 
//		System.err.println("��ť�û�"+btnstart);		
		btnstart.setEnabled(false);
		this.validate(); //ˢ�½���
	}
	public void finishSending( ){ 
		isRunning=false;
		btnstart.setEnabled(true);
		this.validate();
	} 
	public void reflashUI( ){ 
		btnstart.setEnabled(!isRunning);
		this.validate();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TabUI();
	}

}
