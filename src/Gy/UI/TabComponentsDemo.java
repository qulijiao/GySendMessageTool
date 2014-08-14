package Gy.UI;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


import javax.swing.JPanel;

import javax.swing.JTabbedPane;

import javax.swing.SwingUtilities;
 

 
public class TabComponentsDemo extends JPanel{
 
 
    private JTabbedPane jTabbedpane = new JTabbedPane();//���ѡ������
    private String[] tabNames = {"ѡ��1","ѡ��2"};
    ImageIcon icon = createImageIcon("images/middle.gif");
    
    public TabComponentsDemo()
    {
     layoutComponents();
     incident();
    }
    
   


 private void layoutComponents() {
  int i=0;
  //��һ����ǩ�µ�JPanel
  JPanel jpanelFirst = new JPanel();
  //jTabbedpane.addTab(tabNames[i++],icon,creatComponent(),"first");//�����һ��ҳ��
  jTabbedpane.addTab(tabNames[i++],icon,jpanelFirst,"first");//�����һ��ҳ��
  jTabbedpane.setMnemonicAt(0, KeyEvent.VK_F);//���õ�һ��λ�õĿ�ݼ�Ϊf
  
  //�ڶ�����ǩ�µ�JPanel
  JPanel jpanelSecond = new JPanel();
  jTabbedpane.addTab(tabNames[i++],icon,jpanelSecond,"second");//�����һ��ҳ��
  jTabbedpane.setMnemonicAt(1, KeyEvent.VK_S);//���ÿ�ݼ�Ϊs  
  setLayout(new GridLayout(1, 1));
//  add(jTabbedpane);
  this.add(jTabbedpane);
  
 }

 
 private ImageIcon createImageIcon(String path) {

        URL url = TabComponentsDemo.class.getResource(path);
        if(url == null)
        {
            System.out.println("the image "+path+" is not exist!");

            return null;

        }
        return new ImageIcon(url);
 }
 
    private void incident() {
  // TODO Auto-generated method stub
  
 }


 public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
         
          public void run() {
          JFrame.setDefaultLookAndFeelDecorated(true);//���齨�������ΪJava���
          JFrame frame = new JFrame( );
          frame.setLayout(null);
          frame.setContentPane(new TabComponentsDemo());
          frame.setSize(400, 600);
          frame.setVisible(true);
          //new TabComponentsDemo().runTest();
          
           }

       });

    }

 
 
}