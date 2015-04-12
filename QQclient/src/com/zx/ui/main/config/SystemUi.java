package com.zx.ui.main.config;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.zx.ui.login.LoginUi;
import com.zx.ui.main.MainUi;
import com.zx.utils.MyMouseAdapter;
/**
 * ϵͳ���ý���
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class SystemUi extends JFrame implements MouseListener{
	private JLabel minimize;//��С����ť
	private JLabel exit;//�رհ�ť
	private JLabel font;
	private JLabel C_black;
	private JLabel C_red;
	private JLabel C_white;
	private JLabel C_blue;
	private JLabel C_green;
	private JLabel C_gray;
	public SystemUi(){
		this.setSize(200, 220);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//���ô��ڴ�С���ɱ�
		this.setUndecorated(true);//Ҫȥ��������
		MyMouseAdapter adapter = new MyMouseAdapter();
		//�����ޱ������Ĵ����ƶ�
		this.addMouseMotionListener(adapter);
		this.addMouseListener(adapter);
		init();
		this.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SystemUi.class.getResource("/com/xd/imgs/login/QQ_64.png")));
		this.setVisible(true);
	}
	
	public void init(){
		this.add(getMinimize());
		this.add(getExit());
		font = new JLabel("�� �� �� ɫ ��");
		font.setBounds(45, 20, 100, 20);
		this.add(font);
		C_black = new JLabel("��");
		C_black.setBounds(95, 50, 20, 20);
		C_black.addMouseListener(this);
		this.add(C_black);
		C_red = new JLabel("��");
		C_red.setBounds(95, 80, 20, 20);
		C_red.addMouseListener(this);
		this.add(C_red);
		C_white = new JLabel("��");
		C_white.setBounds(95, 110, 20, 20);
		C_white.addMouseListener(this);
		this.add(C_white);
		C_blue = new JLabel("��");
		C_blue.setBounds(95, 140, 20, 20);
		C_blue.addMouseListener(this);
		this.add(C_blue);
		C_green = new JLabel("��");
		C_green.setBounds(95, 170, 20, 20);
		C_green.addMouseListener(this);
		this.add(C_green);
		C_gray = new JLabel("��");
		C_gray.setBounds(95, 200, 20, 20);
		C_gray.addMouseListener(this);
		this.add(C_gray);
	}
	
	/**
	 * ��ȡ��С����ť
	 */
	private JLabel getMinimize(){
		if(minimize==null){
			ImageIcon icon = new ImageIcon(SystemUi.class.getResource("/com/xd/imgs/login/minimize.png"));
			minimize = new JLabel(icon);
			minimize.setBounds(133, 0, 28, 20);
			minimize.addMouseListener(this);
		}
		return minimize;
	}

	/**
	 * ��ȡ�رհ�ť
	 */
	private JLabel getExit(){
		if(exit==null){
			ImageIcon icon = new ImageIcon(SystemUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit = new JLabel(icon);
			exit.setBounds(161, 0, 39, 20);
			exit.addMouseListener(this);
		}
		return exit;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == exit){
			dispose();
		}
		if(e.getSource() == minimize){
			this.setExtendedState(JFrame.ICONIFIED);
		}
		if(e.getSource() == C_black){
			MainUi.nickName.setForeground(Color.BLACK);
			MainUi.signature.setForeground(Color.BLACK);
		}
		if(e.getSource() == C_blue){
			MainUi.nickName.setForeground(Color.BLUE);
			MainUi.signature.setForeground(Color.BLUE);
		}
		if(e.getSource() == C_gray){
			MainUi.nickName.setForeground(Color.GRAY);
			MainUi.signature.setForeground(Color.GRAY);
		}
		if(e.getSource() == C_green){
			MainUi.nickName.setForeground(Color.GREEN);
			MainUi.signature.setForeground(Color.GREEN);
		}
		if(e.getSource() == C_red){
			MainUi.nickName.setForeground(Color.RED);
			MainUi.signature.setForeground(Color.RED);
		}
		if(e.getSource() == C_white){
			MainUi.nickName.setForeground(Color.WHITE);
			MainUi.signature.setForeground(Color.WHITE);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == minimize){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/minimize_active.png"));
			minimize.setIcon(icon);
			minimize.setToolTipText("��С��");
		}
		if(e.getSource() == exit){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close_active.png"));
			exit.setIcon(icon);
			exit.setToolTipText("�ر�");
		}
		if(e.getSource() == C_black){
			C_black.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == C_blue){
			C_blue.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == C_gray){
			C_gray.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == C_green){
			C_green.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == C_red){
			C_red.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == C_white){
			C_white.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == minimize){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/minimize.png"));
			minimize.setIcon(icon);
		}
		if(e.getSource() == exit){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit.setIcon(icon);
		}
		if(e.getSource() == C_black){
			C_black.setBorder(null);
		}
		if(e.getSource() == C_blue){
			C_blue.setBorder(null);
		}
		if(e.getSource() == C_gray){
			C_gray.setBorder(null);
		}
		if(e.getSource() == C_green){
			C_green.setBorder(null);
		}
		if(e.getSource() == C_red){
			C_red.setBorder(null);
		}
		if(e.getSource() == C_white){
			C_white.setBorder(null);
		}
	}
}
