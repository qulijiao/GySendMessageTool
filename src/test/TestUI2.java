package test;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TestUI2 extends JFrame {
	public TestUI2() {
		JPanel p1 = new JPanel();
		p1.setSize(500, 350);
		JButton jbtn = new JButton("ok");
		JButton jbtn2 = new JButton("cancle");
		JButton jbtn3 = new JButton("save");
		JTextArea textAreaOutput = new JTextArea(10,10);
		JScrollPane js=new JScrollPane(textAreaOutput);
		JPanel panelOutput  = new JPanel();
	    panelOutput.add(new JScrollPane(textAreaOutput));
		p1.add(jbtn);
		p1.add(jbtn2);
		p1.add(textAreaOutput);
		p1.add(js);
		p1.add(jbtn3);
		GridBagLayout layout = new GridBagLayout();
		p1.setLayout(layout);
		GridBagConstraints gbc =  new GridBagConstraints();
//		gbc.
		Container cp;
		cp = getContentPane();
		cp.setLayout(null);
		cp.add(p1);
		setVisible(true);
		setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new TestUI2();
	}
}
