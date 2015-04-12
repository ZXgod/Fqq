package com.zx.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import com.zx.business.ebi.ChatServerBusinessEbi;
import com.zx.business.ebi.ServerBusinessEbi;
import com.zx.business.factory.ChatServerBusinessFactory;
import com.zx.business.factory.ServerBusinessFactory;

/**
 * �������Ľ���
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class ServerUi extends JFrame implements ActionListener{
	public JTextArea area;//��ʾ��Ϣ���ı�����
	@SuppressWarnings("rawtypes")
	public DefaultListModel lm;//��ʾ�����û��б�
	private JMenuItem run;//����
	private JMenuItem exit;//�˳�
	public static final ServerUi smu = new ServerUi();//���������˼�룬�����½���˳���ʾ��Ϣ������
   
	/**
	 * ���췽��
	 */
	@SuppressWarnings("rawtypes")
	public ServerUi(){
		this.getContentPane().add(new JScrollPane(getArea()));

		//�����û��б�
		lm = new DefaultListModel();
		@SuppressWarnings("unchecked")
		JList list = new JList(lm);
		JScrollPane jc = new JScrollPane(list);
		jc.setPreferredSize(new Dimension(100, this.getHeight()));
		jc.setBorder(new TitledBorder("����"));
		this.getContentPane().add(jc,BorderLayout.EAST);

		//�˵�
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("����(C)");
		bar.add(menu);
		menu.setMnemonic('C'); //���Ƿ�  ������ �û���Alt+'C'����
		run = new JMenuItem("����");
		run.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
		run.setActionCommand("run");
		menu.add(run);
		menu.addSeparator();
	    exit = new JMenuItem("�˳�");
		exit.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_MASK));
		exit.setActionCommand("exit");
		menu.add(exit);
		this.setJMenuBar(bar);
		run.addActionListener(this);
		exit.addActionListener(this);
		
		this.setTitle("QQ������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final int winWidth =500;
		final int winHeight =400;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();
		this.setBounds(width/2-winWidth/2, height/2-winHeight/2, winWidth, winHeight);
	}

	/**
	 * ��ȡ��ʾ��Ϣ���ı�����
	 */
	public JTextArea getArea(){
		if(area == null){
			area = new JTextArea();
			area.setEditable(false);
		}
		return area;
	}

	/**
	 * ������Ϣ��Ӧ
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("run")){
			ServerBusinessEbi sbe = ServerBusinessFactory.newInstance();
			sbe.startServer();
			ChatServerBusinessEbi csbe = ChatServerBusinessFactory.newInstance();
			csbe.startServer();
			run.setEnabled(false);
		}
		if(e.getActionCommand().equals("exit")){
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		smu.setVisible(true);
	}
}
