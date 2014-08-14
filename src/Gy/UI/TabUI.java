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
    private JTabbedPane jTabbedpane = new JTabbedPane();//存放选项卡的组件
    private String[] tabNames = {"选项1","选项2"};
    ImageIcon icon = createImageIcon("images/middle.gif");
    JPanel resultJPanel;
	public TabUI() {
		cp = getContentPane();
		cp.setLayout(null);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JPanel jpanel = new JPanel();
		JTabbedPane jTabbedpane = new JTabbedPane();
		String[] tabNames = { "选项1", "选项2" };
		JPanel jpanelFirst = new JPanel();
		jTabbedpane.addTab(tabNames[0], null, jpanelFirst, "first");// 加入第一个页面
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
		// 第一个标签下的JPanel
		JPanel jpanelFirst = new JPanel();
		// jTabbedpane.addTab(tabNames[i++],icon,creatComponent(),"first");//加入第一个页面
		jTabbedpane.addTab(tabNames[i++], icon, jpanelFirst, "first");// 加入第一个页面
		jTabbedpane.setMnemonicAt(0, KeyEvent.VK_F);// 设置第一个位置的快捷键为f

		// 第二个标签下的JPanel
		JPanel jpanelSecond = new JPanel();
		jTabbedpane.addTab(tabNames[i++], icon, jpanelSecond, "second");// 加入第一个页面
		jTabbedpane.setMnemonicAt(1, KeyEvent.VK_S);// 设置快捷键为s
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
