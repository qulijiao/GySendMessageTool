package Gy.UI;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabUI extends JFrame {
	String strIP, strPORT, strSendCount;
	String strSendingMSG, strReceiveMSG;
	Container cp;
	public boolean isRunning = false;
    private JTabbedPane jTabbedpane = new JTabbedPane();//���ѡ������
    private String[] tabNames = {"ѡ��1","ѡ��2"};
    ImageIcon icon = createImageIcon("images/middle.gif");
    JPanel resultJPanel;
	public TabUI() {
		cp = getContentPane();
		cp.setLayout(null);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JPanel jpanel = new JPanel();
		JTabbedPane jTabbedpane = new JTabbedPane();
		String[] tabNames = { "ѡ��1", "ѡ��2" };
		JPanel jpanelFirst = new JPanel();
		jTabbedpane.addTab(tabNames[0], null, jpanelFirst, "first");// �����һ��ҳ��
		jpanel.add(jTabbedpane);
		cp.add(jpanel);
		setLayout(null);
		 setContentPane(getJPanel());
//		setContentPane(new TabComponentsDemo());
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel getJPanel() {
		resultJPanel = new JPanel();
		layoutComponents();
		incident();
		return resultJPanel;
	}

	private void incident() {

	}

	private void layoutComponents() {
		int i = 0;
		// ��һ����ǩ�µ�JPanel
		JPanel jpanelFirst = new JPanel();
		// jTabbedpane.addTab(tabNames[i++],icon,creatComponent(),"first");//�����һ��ҳ��
		jTabbedpane.addTab(tabNames[i++], icon, jpanelFirst, "first");// �����һ��ҳ��
		jTabbedpane.setMnemonicAt(0, KeyEvent.VK_F);// ���õ�һ��λ�õĿ�ݼ�Ϊf

		// �ڶ�����ǩ�µ�JPanel
		JPanel jpanelSecond = new JPanel();
		jTabbedpane.addTab(tabNames[i++], icon, jpanelSecond, "second");// �����һ��ҳ��
		jTabbedpane.setMnemonicAt(1, KeyEvent.VK_S);// ���ÿ�ݼ�Ϊs
		setLayout(new GridLayout(1, 1));
		// add(jTabbedpane);
		resultJPanel.add(jTabbedpane);

	}

	private ImageIcon createImageIcon(String path) {

		URL url = TabComponentsDemo.class.getResource(path);
		if (url == null) {
			System.out.println("the image " + path + " is not exist!");
			return null;
		}
		return new ImageIcon(url);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TabUI();
	}

}
