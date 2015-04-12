package com.zx.ui.main.message;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.xd.bean.UserInfoVo;
import com.zx.ui.login.LoginUi;
import com.zx.utils.JImagePane;
import com.zx.utils.MyMouseAdapter;
/**
 * ��ʾ�Լ���Ϣ�Ľ��棬�����޸��Լ�����Ϣ
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class MessageUi extends JFrame implements MouseListener{
	private UserInfoVo uv;
	public static JImagePane panel;//����ͼƬ
	private JLabel skin;//������ť
	private JLabel minimize;//��С����ť
	private JLabel exit;//��󻯰�ť
	private JLabel headLabel;//ͷ�� 90 10
	private JLabel nickName;//�ǳ�
	private JLabel qq;//qq��
	private JLabel  signature;//����ǩ��
	private JTabbedPane tabbedPane;//�л����
	private JPanel showMessage;//����չʾ
	private JButton edit;//�༭����
	private JLabel nickname;//�ǳ�
	private JLabel account;//�˺�
	private JLabel personal;//����˵��
	private JLabel person;//����
	private JLabel home;//����
	private JLabel location;//���ڵ�
	private JLabel phone;//�ֻ�
	private JLabel page;//��ҳ
	private JLabel mail;//����
	
	public MessageUi(UserInfoVo uv){
		this.uv = uv;
		this.setSize(460, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//���ô��ڴ�С���ɱ�
		this.setUndecorated(true);//Ҫȥ��������
		MyMouseAdapter adapter = new MyMouseAdapter();
		//�����ޱ������Ĵ����ƶ�
		this.addMouseMotionListener(adapter);
		this.addMouseListener(adapter);
		//�����ޱ������Ĵ����ƶ�
		init();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MessageUi.class.getResource("/com/xd/imgs/login/QQ_64.png")));
		this.add(panel);
	}

	public void init(){
		panel = new JImagePane(new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/skin/qqLogin.jpg")).getImage(),JImagePane.SCALED);
		panel.setLayout(null);
		panel.add(getSkin());
		panel.add(getMinimize());
		panel.add(getExit());
		panel.add(getHeadLabel());
		panel.add(getNickName());
		panel.add(getQq());
		panel.add(getSignature());
		panel.add(getTabbedPane());
	}

	/**
	 * ��ȡ������ť
	 */
	private JLabel getSkin(){
		if(skin == null){
			ImageIcon icon = new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/login/skin.png"));
			skin = new JLabel(icon);
			skin.setBounds(365, 0, 28, 20);
			skin.addMouseListener(this);
		}
		return skin;
	}

	/**
	 * ��ȡ��С����ť
	 */
	private JLabel getMinimize(){
		if(minimize==null){
			ImageIcon icon = new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/login/minimize.png"));
			minimize = new JLabel(icon);
			minimize.setBounds(393, 0, 28, 20);
			minimize.addMouseListener(this);
		}
		return minimize;
	}

	/**
	 * ��ȡ�رհ�ť
	 */
	private JLabel getExit(){
		if(exit==null){
			ImageIcon icon = new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit = new JLabel(icon);
			exit.setBounds(421, 0, 39, 20);
			exit.addMouseListener(this);
		}
		return exit;
	}

	/**
	 * ��ȡͷ��
	 */
	private JLabel getHeadLabel(){
		if(headLabel == null){
			ImageIcon icon = new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/head/"+uv.getPhotoID()+".png"));
			ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
			headLabel = new JLabel(icon1);
			headLabel.setBounds(10, 90 ,80,80);
			headLabel.addMouseListener(this);
		}
		return headLabel;
	}

	/**
	 *��ȡ�ǳ� 
	 */
	private JLabel getNickName(){
		if(nickName == null){
			nickName = new JLabel();
			nickName.setText(uv.getNickname());
			nickName.setLocation(new Point(95,90));
			nickName.setForeground(Color.WHITE);
			nickName.setFont(new Font("Dialog", Font.BOLD, 18));
			nickName.setSize(new Dimension(80,40));
			nickName.addMouseListener(this);
		}
		return nickName;
	}

	/**
	 * ��ȡQQ��
	 */
	private JLabel getQq(){
		if(qq == null){
			qq = new JLabel();
			qq.setText(uv.getQq());
			qq.setForeground(Color.WHITE);
			qq.setBounds(180, 90, 80, 40);
		}
		return qq;
	}

	/**
	 * ��ȡ����ǩ��
	 */
	private JLabel getSignature(){
		if(signature == null){
			signature = new JLabel();
			signature.setText(uv.getSign());
			signature.setForeground(Color.WHITE);
			signature.setFont(new Font("Dialog", Font.BOLD, 18));
			signature.setBounds(95, 130, 300, 40);
		}	
		return signature;
	}

	/**
	 *��ȡ�л���� 
	 */
	private JTabbedPane getTabbedPane(){
		if(tabbedPane == null){
			tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
			tabbedPane.addTab("����", getShowMessage());
			tabbedPane.addTab("���", new JImagePane(new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/page/2.jpg")).getImage(),JImagePane.SCALED));
			tabbedPane.addTab("��̬", new JImagePane(new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/page/3.jpg")).getImage(),JImagePane.SCALED));
			tabbedPane.addTab("��ǩ", new JImagePane(new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/page/4.jpg")).getImage(),JImagePane.SCALED));
			tabbedPane.addTab("�˻�", new JImagePane(new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/page/5.jpg")).getImage(),JImagePane.SCALED));
			tabbedPane.addTab("�ֻ�", new JImagePane(new ImageIcon(MessageUi.class.getResource("/com/xd/imgs/page/6.jpg")).getImage(),JImagePane.SCALED));
			tabbedPane.setBounds(0, 180, 460, 420);
			tabbedPane.setOpaque(false);
		}
		return tabbedPane;
	}

	/**
	 * չʾ���ϵ����
	 */
	private JPanel getShowMessage(){
		if(showMessage == null){
			showMessage = new JPanel();
			showMessage.setLayout(null);
			showMessage.setBackground(Color.white);
			showMessage.add(getEdit());
			JLabel label1 = new JLabel("��  �ƣ�");
			label1.setBounds(20, 40, 60, 20);
			showMessage.add(label1);
			showMessage.add(getNickname());
			JLabel label2 = new JLabel("��  �ţ�");
			label2.setBounds(20, 70, 60, 20);
			showMessage.add(label2);
			showMessage.add(getAccount());
			JLabel label3 = new JLabel("����˵��");
			label3.setBounds(20, 100, 60, 20);
			showMessage.add(label3);
			showMessage.add(getPersonal());
			JLabel label4 = new JLabel("��  �ˣ�");
			label4.setBounds(20, 150, 60, 20);
			showMessage.add(label4);
			showMessage.add(getPerson());
			JLabel label5 = new JLabel("��  �磺");
			label5.setBounds(20, 180, 60, 20);
			showMessage.add(label5);
			showMessage.add(getHome());
			JLabel label6 = new JLabel("���ڵأ�");
			label6.setBounds(20, 210, 60, 20);
			showMessage.add(label6);
			showMessage.add(getLocaTion());
			JLabel label7= new JLabel("��  ����");
			label7.setBounds(20, 280, 60, 20);
			showMessage.add(label7);
			showMessage.add(getPhone());
			JLabel label8 = new JLabel("��  ҳ��");
			label8.setBounds(20, 310, 60, 20);
			showMessage.add(label8);
			showMessage.add(getPage());
			JLabel label9 = new JLabel("��  �䣺");
			label9.setBounds(20, 340, 60, 20);
			showMessage.add(label9);
			showMessage.add(getEmail());
			showMessage.setBounds(0, 0, 460, 420);
		}
		return showMessage;
	}

	/**
	 * ��ȡ�༭���ϰ�ť
	 */
	private JButton getEdit(){
		if(edit == null){
			edit = new JButton();
			edit.setText("�༭����");
			edit.setBackground(Color.GRAY);
			edit.setBounds(320, 10, 120, 20);
			edit.addMouseListener(this);
		}
		return edit;
	}

	/**
	 * ��ȡ�ǳ�
	 */
	private JLabel getNickname(){
		if(nickname == null){
			nickname = new JLabel();
			nickname.setText(uv.getNickname());
			nickname.setBounds(80, 40, 200, 20);
		}
		return nickname;
	}

	/**
	 * ��ȡ�˺�
	 */
	private JLabel getAccount(){
		if(account == null){
			account = new JLabel();
			account.setText(uv.getQq());
			account.setBounds(80, 70, 200, 20);
		}
		return account;
	}

	/**
	 *��ȡ����˵�� 
	 */
	private JLabel getPersonal(){
		if(personal == null){
			personal = new JLabel();
			personal.setText("��־���ʤ��~~~~");
			personal.setBounds(80, 100, 200, 20);
		}
		return personal;
	}

	/**
	 * ��ȡ����
	 */
	private JLabel getPerson(){
		if(person == null){
			person = new JLabel();
			person.setText(uv.getSex()+" "+"22��    1��1��     ����    ������");
			person.setBounds(80, 150, 300, 20);
		}
		return person;
	}

	/**
	 * ��ȡ����
	 */
	private JLabel getHome(){
		if(home == null){
			home = new JLabel();
			if(uv.getQq().equals("434461146")){
				home.setText("����");
			}else{
				home.setText("����");
			}
			home.setBounds(80, 180, 100, 20);
		}
		return home;	
	}

	/**
	 * ��ȡ���ڵ�
	 */
	private JLabel getLocaTion(){
		if(location == null){
			location = new JLabel();
			if(uv.getQq().equals("434461146")){
				location.setText("��������ʡ���������ϴ��250��");
			}else{
				location.setText("��������ʡ�����д������1��");
			}
			location.setBounds(80, 210, 300, 20);
		}
		return location;
	}

	/**
	 * ��ȡ�ֻ�
	 */
	private JLabel getPhone(){
		if(phone == null){
			phone = new JLabel();
			phone.setText("182********");
			phone.setBounds(80, 280, 200, 20);
		}
		return phone;
	}
	
	/**
	 * ��ȡ��ҳ
	 */
	private JLabel getPage(){
		if(page == null){
			page = new JLabel();
			page.setText("http://www."+uv.getQq()+".com");
			page.setBounds(80, 310, 200, 20);
		}
		return page;
	}
	
	/**
	 * ��ȡ����
	 */
	private JLabel getEmail(){
		if(mail == null){
			mail = new JLabel();
			mail.setText(uv.getQq()+"@qq.com");
			mail.setBounds(80, 340, 200, 20);
		}
		return mail;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == exit){
			dispose();
		}
		if(e.getSource() == minimize){
			this.setExtendedState(JFrame.ICONIFIED);
		}
		if(e.getSource() == skin){
			new com.zx.ui.main.message.SkinUi().setVisible(true);
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
		if(e.getSource() == headLabel){
			headLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == nickName){
			nickName.setToolTipText(nickName.getText());
		}
	}
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
		if(e.getSource() == headLabel){
			headLabel.setBorder(null);
		}
	}
}
