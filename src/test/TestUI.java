package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TestUI extends JFrame {

	public static void main(String[] args) {
		TestUI ui = new TestUI();
		ui.setVisible(true);
		ui.setSize(400, 500);
		//1.新建一个Jpanel 用于保存tab控件
		JPanel p1 = new JPanel();
		p1.setSize(500, 350);
		//位置调整
		p1.setBackground(Color.yellow);
		
		//2.将tab控件添加到上述p1中 
		JTabbedPane jTabbedpane = new JTabbedPane();
		JPanel jpanelFirst = new JPanel();
		jpanelFirst.setSize(100, 100);
		jpanelFirst.setBackground(Color.blue);
		JTextField textip = new JTextField(10);
		jpanelFirst.add(textip);
		jTabbedpane.addTab("选项卡1",null,jpanelFirst,"first");
		
		JPanel jpanelSecond = new JPanel();
		jpanelSecond.setSize(100, 100);
		jpanelSecond.setBackground(Color.GREEN);
		jTabbedpane.addTab("选项卡2",null,jpanelSecond,"second");
		JButton btnstart = new JButton("开始");		
		jpanelSecond.add(btnstart);
		
		p1.setLayout(new GridLayout(1, 1)); 
		p1.add(jTabbedpane);
		
		
		
		Container cp;
		cp = ui.getContentPane();
		cp.setLayout(null);
		cp.add(p1);
		//1.添加一个Jpanel:p1到Jframe
		
		//3.设置上述Jpanel布局 
	}
}
