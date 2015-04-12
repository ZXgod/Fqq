package com.zx.ui.login;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xd.bean.UserInfoVo;
import com.zx.business.ebi.ClientBusinessEbi;
import com.zx.business.factory.ClientBusinessFactory;
import com.zx.ui.main.MainUi;
import com.zx.utils.JImagePane;
import com.zx.utils.MyMouseAdapter;
import com.zx.utils.OpenUrl;

/**
 * ��½����
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class LoginUi extends JFrame implements MouseListener{
	private TrayIcon trayIcon = null;//ϵͳ����
	private JImagePane panel;
	private JTextField userName;//�û��������
	private JPasswordField userPwd;//���������
	private JCheckBox box;//��ס����ѡ���
	private JButton login;//��½��ť
	private JLabel skin;//������ť
	private JLabel minimize;//��С����ť
	private JLabel exit;//��󻯰�ť
	private JLabel register;//ע��
	private JLabel back;//�һ�����
	private JLabel headLabel; //ͷ��
	//  public static LoginUi l = new LoginUi();
	/**
	 * ���캯��
	 */
	public LoginUi(){
		this.setSize(450, 330);
		this.setLocationRelativeTo(null);//ʹ��½��������Ļ���м���ʾ
		this.setResizable(false);//���ô��ڴ�С���ɱ�
		this.setUndecorated(true);//Ҫȥ��������
		init();
		systemTrayinit();
		MyMouseAdapter adapter = new MyMouseAdapter();
		//�����ޱ������Ĵ����ƶ�
		this.addMouseMotionListener(adapter);
		this.addMouseListener(adapter);
		//�����ޱ������Ĵ����ƶ�
		//����Ӧ�ó����ͼ�겻������������ʾ
		this.setType(java.awt.Window.Type.UTILITY);
		this.add(panel);
		this.setVisible(true);
	}

	/**
	 *��ʼ��ϵͳ���� 
	 */
	public void systemTrayinit(){
		if(SystemTray.isSupported()){//��鵱ǰϵͳ�Ƿ�֧��ϵͳ����
			SystemTray tray = SystemTray.getSystemTray();///��ȡ��ʾ������������ SystemTray ʵ����
			//TODO:�������ͼ�겻�ÿ�
			Image image = Toolkit.getDefaultToolkit().getImage(LoginUi.class.getResource("/com/xd/imgs/login/CT.gif"));
			image = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH); //����ͼƬ
			PopupMenu popupMenu = new PopupMenu(); 
			MenuItem  menuItema  = new MenuItem("�������"); 
			MenuItem  exitItem  = new MenuItem("�˳�");
			exitItem.addActionListener(new  ActionListener(){ 
				public void actionPerformed(ActionEvent e)     {  
					try{     
						System.exit(0);     
					}catch(Exception   ex)   {  
						ex.printStackTrace();   
					}   
				} 
			}); 
			menuItema.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try{     
						setVisible(true);     
					}catch(Exception   ex)   {  
						ex.printStackTrace();   
					} 	
				}
			});
			popupMenu.add(menuItema); 
			popupMenu.add(exitItem); 
			trayIcon = new TrayIcon(image, "����",  popupMenu);
			trayIcon.addMouseListener(this);
			try{   
				tray.add(trayIcon);  // �� TrayIcon ��ӵ� SystemTray�� 
			} catch   (AWTException   e)     {   
				System.err.println(e);   
			} 
		}else{
			System.out.println("���ϵͳ��֧��ϵͳ����"); 
		}
	}

	/**
	 * ��ʼ������
	 */
	public void init(){
		panel = new JImagePane(new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/skin/qqLogin.jpg")).getImage(),JImagePane.SCALED);
		panel.setLayout(null);
		panel.add(getSkin());
		panel.add(getMinimize());
		panel.add(getExit());
		panel.add(getHeadLabel());
		panel.add(getUserName());
		panel.add(getBox());
		panel.add(getUserPwd());
		panel.add(getRegister());
		panel.add(getBack());
		panel.add(getLogin());
	}

	/**
	 * ��ȡ������ť
	 */
	private JLabel getSkin(){
		if(skin == null){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/skin.png"));
			skin = new JLabel(icon);
			skin.setBounds(355, 0, 28, 20);
			skin.addMouseListener(this);
		}
		return skin;
	}

	/**
	 * ��ȡ��С����ť
	 */
	private JLabel getMinimize(){
		if(minimize==null){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/minimize.png"));
			minimize = new JLabel(icon);
			minimize.setBounds(383, 0, 28, 20);
			minimize.addMouseListener(this);
		}
		return minimize;
	}

	/**
	 * ��ȡ�رհ�ť
	 */
	private JLabel getExit(){
		if(exit==null){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit = new JLabel(icon);
			exit.setBounds(411, 0, 39, 20);
			exit.addMouseListener(this);
		}
		return exit;
	}

	/**
	 * ��ȡͷ��
	 * 
	 */
	private JLabel getHeadLabel(){
		if(headLabel==null){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/head/001.png"));
			headLabel = new JLabel(icon);
			headLabel.setBounds(30, 180 ,87,83);
		}
		return headLabel;
	}

	/**
	 * ��ȡ�û��������
	 * @param args
	 */
	private JTextField getUserName(){
		if(userName==null){
			userName = new JTextField();
			userName.setBounds(130, 180, 180, 30);
			userName.addFocusListener(new FocusListener() {
				//���㶪ʧ
				@Override
				public void focusLost(FocusEvent e) {
					String qq = userName.getText();
					ClientBusinessEbi cbe = ClientBusinessFactory.newInstance();
					String photoId = cbe.getPhotoId(qq);
					if(photoId!=null){
						ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/head/"+photoId+".png"));
						ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(87, 83, Image.SCALE_SMOOTH));
						headLabel.setIcon(icon1);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});
		}
		return userName;
	}

	/**
	 * ��ȡ���������
	 * @param args
	 */
	private JPasswordField getUserPwd(){
		if(userPwd==null){
			userPwd = new JPasswordField();
			userPwd.setBounds(130, 220, 180, 30);
		}
		return userPwd;
	}

	/**
	 * ��ȡ��ס�������
	 * @param args
	 */
	private JCheckBox getBox(){
		if(box==null){
			box = new JCheckBox("��ס����");
			box.setForeground(Color.white);
			box.setBounds(130, 250, 80, 20);
			box.setOpaque(false);
		}
		return box;
	}

	/**
	 * ��ȡע���˺����
	 * @param args
	 */
	private JLabel getRegister(){
		if(register==null){
			register = new JLabel("ע���˺�");
			register.setBounds(320, 180, 60, 30);
			register.setForeground(Color.BLUE);
			register.addMouseListener(this);
		}
		return register;
	}

	/**
	 * ��ȡ�һ��������
	 * @param args
	 */
	private JLabel getBack(){
		if(back==null){
			back = new JLabel("�һ�����");
			back.setBounds(320, 220, 60, 30);
			back.setForeground(Color.BLUE);
			back.addMouseListener(this);
		}
		return back;
	}

	/**
	 * ��ȡ��½��ť
	 * @param args
	 */
	private JButton getLogin(){
		if(login == null){
			Font font = new Font("����",1,20);
			login = new JButton("��½");
			login.setFont(font);
			login.setBounds(130, 280, 180, 30);
			login.setContentAreaFilled(false);//���ð�ť͸����
			login.setForeground(Color.RED);
			login.addMouseListener(this);
		}
		return login;
	}

	public static void main(String[] args) {
		new LoginUi();
	}

	/**
	 * ������
	 */
	//��갴��������ϵ��������²��ͷţ�ʱ���á�
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == exit){
			System.exit(0);
		}
		if(e.getSource() == minimize){
			this.setVisible(false);
		}
		if(e.getSource() == skin){
			//TODO:
		}
		if(e.getSource() == register){
			OpenUrl.openURL("http://zc.qq.com/chs/index.html");
			//new RegisterUi();
			//dispose();
		}
		if(e.getSource() == back){
			OpenUrl.openURL("http://aq.qq.com/cn2/findpsw/pc/pc_find_pwd_input_account");
		}
		if(e.getSource() == login){
			String nm = userName.getText();
			@SuppressWarnings("deprecation")
			String pwd = userPwd.getText();
			ClientBusinessEbi cbe = ClientBusinessFactory.newInstance();
			List<Object> list = cbe.login(nm, pwd);
			int flag = (Integer) list.get(0);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "���˺Ų�����");
			}
			if(flag == 1){
				JOptionPane.showMessageDialog(null, "�������");
			}
			if(flag == 2){
				JOptionPane.showMessageDialog(null, "���˺��ѵ�¼");
			}
			if(flag == 3){
				UserInfoVo uv = (UserInfoVo) list.get(1);
				new MainUi(uv);
				dispose();
				SystemTray.getSystemTray().remove(trayIcon);//�Ƴ�ϵͳ����
			}
		}
		if(e.getSource() == trayIcon){
			this.setVisible(true);
		}
	}
	//��갴��������ϰ���ʱ���á�
	@Override
	public void mousePressed(MouseEvent e) {		
	}
	//��갴ť��������ͷ�ʱ���á�
	@Override
	public void mouseReleased(MouseEvent e) {		
	}
	//�����뵽�����ʱ���á�
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == skin){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/skin_active.png"));
			skin.setIcon(icon);
			skin.setToolTipText("�������");
		}
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
		if(e.getSource() == register){
			register.setBorder(BorderFactory.createLineBorder(Color.red));
		}
		if(e.getSource() == back){
			back.setBorder(BorderFactory.createLineBorder(Color.red));
		}
	}
	//����뿪���ʱ���á�
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == skin){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/skin.png"));
			skin.setIcon(icon);
		}
		if(e.getSource() == minimize){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/minimize.png"));
			minimize.setIcon(icon);
		}
		if(e.getSource() == exit){
			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit.setIcon(icon);
		}
		if(e.getSource() == register){
			register.setBorder(null);
		}
		if(e.getSource() == back){
			back.setBorder(null);
		}
	}

}
