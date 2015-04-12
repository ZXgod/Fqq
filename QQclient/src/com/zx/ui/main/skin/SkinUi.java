package com.zx.ui.main.skin;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zx.business.ebi.ClientBusinessEbi;
import com.zx.business.factory.ClientBusinessFactory;
import com.zx.ui.login.LoginUi;
import com.zx.ui.main.MainUi;
import com.zx.utils.MyMouseAdapter;
import com.zx.utils.OpenUrl;
/**
 * ��������
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class SkinUi extends JFrame implements MouseListener{
	private JLabel exit;//�رհ�ť 125*125
	private JLabel label;
	private JPanel skinPanel;
	private JButton more;
	private JButton define;
	private String qq;
	public SkinUi(String qq){
		this.qq = qq;
		this.setSize(750, 440);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);//Ҫȥ��������
		this.setLayout(null);
		MyMouseAdapter adapter = new MyMouseAdapter();
		//�����ޱ������Ĵ����ƶ�
		this.addMouseMotionListener(adapter);
		this.addMouseListener(adapter);
		//�����ޱ������Ĵ����ƶ�
		this.add(getLabel());
		this.add(getExit());
		this.add(getSkinPanel());
		this.add(getDefine());
		this.add(getMore());
		setIconImage(Toolkit.getDefaultToolkit().getImage(SkinUi.class.getResource("/com/xd/imgs/login/QQ_64.png")));
	}

	private JLabel getLabel(){
		if(label == null){
			label = new JLabel();
			label.setText("�������");
			label.setForeground(Color.BLUE);
			label.setBounds(10, 0, 100, 20);
		}
		return label;
	}

	/**
	 * ��ȡ�رհ�ť
	 */
	private JLabel getExit(){
		if(exit==null){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit = new JLabel(icon);
			exit.setBounds(711, 0, 39, 20);
			exit.addMouseListener(this);
		}
		return exit;
	}

	/**
	 *��ȡƤ����� 
	 */
	private JPanel getSkinPanel(){
		if(skinPanel == null){
			skinPanel = new JPanel();
			skinPanel.setLayout(new GridLayout(3, 6));
			for(int i=0;i<18;i++){
				final int j =i;
				ImageIcon icon = new ImageIcon(SkinUi.class.getResource("/com/xd/imgs/skin/"+i+".jpg"));
				ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
				final JLabel label = new JLabel(icon1);
				skinPanel.add(label);
				label.addMouseListener(new MouseListener() {
					@Override
					public void mousePressed(MouseEvent e) {
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						if(e.getSource() == label){
							label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
							label.setText(" ");
						}
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getSource() == label){
							boolean flag;
							ClientBusinessEbi cbe = ClientBusinessFactory.newInstance();
							flag = cbe.setBg(j,qq);
							if(flag){
								Image icon = new ImageIcon(SkinUi.class.getResource("/com/xd/imgs/skin/"+j+".jpg")).getImage();
								MainUi.panel.setBackgroundImage(icon);
							}
						}
					}
					@Override
					public void mouseReleased(MouseEvent e) {
					}
					@Override
					public void mouseExited(MouseEvent e) {
						if(e.getSource() == label){
							label.setBorder(null);
							label.setText(null);
						}
					}
				});
			}
			skinPanel.setBounds(0,30, 750, 360);
		}
		return skinPanel;
	}

	private JButton getDefine(){
		if(define == null){
			define = new JButton();
			define.setText("�Զ���Ƥ��");
			define.setBackground(Color.GRAY);
			define.setBounds(10, 405, 160, 20);
		}
		return define;
	}

	private JButton getMore(){
		if(more == null){
			more = new JButton();
			more.setText("����Ƥ��");
			more.setBounds(180, 405, 160, 20);
			more.setBackground(Color.GRAY);
			more.addMouseListener(this);
		}
		return more;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == exit){
			dispose();
		}
		if(e.getSource() == more){
			OpenUrl.openURL("http://style.qq.com/decorate/");
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
		if(e.getSource() == exit){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close_active.png"));
			exit.setIcon(icon);
			exit.setToolTipText("�ر�");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == exit){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit.setIcon(icon);
		}

	}
	
}
