package com.zx.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * �������ĵ�½����
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class LoginUi extends JFrame implements ActionListener{
	private final String userName = "admin";
	private final String userPwd = "123456";
	private JButton ok = null;//ȷ����ť
	private JButton cancel = null;//ȡ����ť
	private JTextField name = null;//�û��������
	private JTextField pwd = null;//���������
	
	/**
	 * ���캯��
	 */
    public LoginUi(){
    	super("������");
    	this.setSize(300, 250);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setResizable(false);
    	this.setLayout(null);
    	JLabel label1 = new JLabel("�û���:");
    	label1.setBounds(40, 40, 60, 40);
    	name = new JTextField();
    	name.setBounds(100, 40, 150, 40);
    	
    	JLabel label2 = new JLabel("����:");
    	label2.setBounds(55, 90, 60, 40);
    	pwd = new JTextField();
    	pwd.setBounds(100, 90, 150, 40);
    	
    	this.add(label1);
    	this.add(name);   
    	this.add(label2);
    	this.add(pwd);
    	this.add(getOk());
    	this.add(getCancel());
    	this.setVisible(true);
    }
    
    /**
     * ��ȡȷ����ť
     * @return
     */
    private JButton getOk(){
    	if(ok == null){
    		ok = new JButton();
    		ok.setText("ȷ��");
    		ok.setBounds(40, 140, 80, 40);
    		ok.addActionListener(this);
    	}
    	return ok;
    }
    
    /**
     * ��ȡȡ����ť
     * @return
     */
    private JButton getCancel(){
    	if(cancel == null){
    		cancel = new JButton();
    		cancel.setText("ȡ��");
    		cancel.setBounds(160, 140, 80, 40);
    	}
    	return cancel;
    }
    
    /**
     * ��Ӧ����
     */
	@Override
	public void actionPerformed(ActionEvent e) {
         if(e.getSource() == ok){
        	 String nm = name.getText();
        	 String p = pwd.getText();
        	 if(nm==null || nm.equals("") || p==null || p.equals("")){
        		 JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
        	 }
        	 if(nm.equals(userName)&&p.equals(userPwd)){
        		 ServerUi.smu.setVisible(true);
        		 dispose();
        	 }
         }		
         if(e.getSource() == cancel){
        	 System.exit(0);
         }
	}
	
	 public static void main(String[] args) {
			new LoginUi();
		}
}
