package com.zx.ui.main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.xd.bean.UserInfoVo;
import com.zx.business.chat.Send;
import com.zx.business.ebi.ClientBusinessEbi;
import com.zx.business.factory.ClientBusinessFactory;
import com.zx.ui.login.LoginUi;
import com.zx.ui.main.config.SystemUi;
import com.zx.ui.main.group.GroupListPanel;
import com.zx.ui.main.message.MessageUi;
import com.zx.ui.main.skin.SkinUi;
import com.zx.utils.JImagePane;
import com.zx.utils.MyMouseAdapter;
import com.zx.utils.OpenUrl;
import com.zx.utils.PrivateChatMap;
import com.zx.utils.SocketMap;
/**
 * TuoTuoTal�������棬��ʾ�����б�Ⱥ�����
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class MainUi extends JFrame implements MouseListener,ActionListener{
	private UserInfoVo uv = null;//�����û���Ϣ
	//��
	public static JImagePane panel = null;//����ͼƬ��Panel(��)
	private JLabel skin;//������ť
	private JLabel minimize;//��С����ť
	private JLabel exit;//�رհ�ť
	private JLabel headLabel;//�û�ͷ��  ����15,45
	private TrayIcon trayIcon = null;//ϵͳ����
	public static JLabel nickName;//�ǳ�
	private JMenuBar stateMenu;//״̬��
	private JMenu jMenu;//״̬
	public static JTextField  signature;//����ǩ��
	private JLabel space;//�ռ�
	private JLabel mail;//�ʼ�
	private JLabel blog;//΢��
	private JLabel add;//���
	private JLabel set;//����
	private JTextField searchBar;//������
	private JLabel search;//����ͼ��
	private JPanel tp;//���ѣ�Ⱥ����Ϣ��¼�л�
	private JLabel contact;//��ϵ��
	private JLabel group;//Ⱥ��������
	private JLabel message;//��Ϣ��¼
	//��
	private JPanel middlePanel;//���ѣ�Ⱥ����Ϣ���(��)
	private JScrollPane scrollPane = null;//�����б���ӹ�����
	private JScrollPane groupPanel;//Ⱥ���
	//��
	private JImagePane belowPanel;//�������

	/**
	 * ���캯��
	 */
	public MainUi(final UserInfoVo uv){
		this.uv = uv;
		this.setResizable(false);
		this.setUndecorated(true);//Ҫȥ��������
		this.setLayout(null);
		MyMouseAdapter adapter = new MyMouseAdapter();
		//�����ޱ������Ĵ����ƶ�
		this.addMouseMotionListener(adapter);
		this.addMouseListener(adapter);
		//�����ޱ������Ĵ����ƶ�
		this.add(getPanel());
		this.add(getMiddlePanel());
		this.add(getBelowPanel());
		init();
		systemTrayinit();
		//����Ӧ�ó����ͼ�겻������������ʾ
		this.setType(java.awt.Window.Type.UTILITY);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();
		this.setBounds(width*2/3, height/7,300 , 530);
		this.setVisible(true);
		new Thread(){
			public void run() {
				ClientBusinessEbi cbe = ClientBusinessFactory.newInstance();
				cbe.startSocket(uv.getQq());
			};
		}.start();
	}

	/**
	 * ��ʼ������
	 */
	public void init(){
		//��
		panel.add(getSkin());
		panel.add(getMinimize());
		panel.add(getExit());
		panel.add(getHeadLabel());
		panel.add(getNickName());
		panel.add(getStateMenu());
		panel.add(getSignature());
		panel.add(getSpace());
		panel.add(getMail());
		panel.add(getBlog());
		panel.add(getAdd());
		panel.add(getSet());
		panel.add(getSearch());
		panel.add(getSearchBar());
		panel.add(getTP());
		//��
		middlePanel.add(getScrollPane());
	}

	/**
	 *��ʼ��ϵͳ���� 
	 */
	public void systemTrayinit(){
		if(SystemTray.isSupported()){//��鵱ǰϵͳ�Ƿ�֧��ϵͳ����
			SystemTray tray = SystemTray.getSystemTray();///��ȡ��ʾ������������ SystemTray ʵ����
			//TODO:�������ͼ�겻�ÿ�
			Image image = Toolkit.getDefaultToolkit().getImage(MainUi.class.getResource("/com/xd/imgs/login/CT.gif"));
			image = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH); //����ͼƬ
			PopupMenu popupMenu = new PopupMenu(); 
			MenuItem  menuItema  = new MenuItem("�������"); 
			MenuItem  exitItem  = new MenuItem("�˳�");
			exitItem.addActionListener(new  ActionListener(){ 
				public void actionPerformed(ActionEvent e)     {  
					try{     
						ClientBusinessEbi cbi = ClientBusinessFactory.newInstance();
						if(cbi.offline(uv.getQq())){
							System.exit(0);
						}else{
							JOptionPane.showMessageDialog(null, "���߲��ɹ�");
						} 
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
	 * ��ȡ����ͼƬ���
	 */
	private JImagePane getPanel(){
		if(panel == null){
			panel = new JImagePane(new ImageIcon(MainUi.class.getResource("/com/xd/imgs/skin/"+uv.getBg()+".jpg")).getImage(),JImagePane.SCALED);
			panel.setLayout(null);
			panel.setBounds(0, 0, 300, 185);
		}
		return panel;
	}

	/**
	 * ��ȡ������ť
	 */
	private JLabel getSkin(){
		if(skin == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/login/skin.png"));
			skin = new JLabel(icon);
			skin.setBounds(205, 0, 28, 20);
			skin.addMouseListener(this);
		}
		return skin;
	}

	/**
	 * ��ȡ��С����ť
	 */
	private JLabel getMinimize(){
		if(minimize==null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/login/minimize.png"));
			minimize = new JLabel(icon);
			minimize.setBounds(233, 0, 28, 20);
			minimize.addMouseListener(this);
		}
		return minimize;
	}

	/**
	 * ��ȡ�رհ�ť
	 */
	private JLabel getExit(){
		if(exit==null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/login/close.png"));
			exit = new JLabel(icon);
			exit.setBounds(261, 0, 39, 20);
			exit.addMouseListener(this);
		}
		return exit;
	}

	/**
	 * ��ȡͷ��
	 */
	private JLabel getHeadLabel(){
		if(headLabel == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/head/"+uv.getPhotoID()+".png"));
			//			ImageIcon icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/qq.gif"));
			ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
			headLabel = new JLabel(icon1);
			headLabel.setBounds(15, 45 ,60,60);
			//			headLabel = new JLabel(icon);
			//			headLabel.setBounds(15, 45 ,79,72);
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
			nickName.setLocation(new Point(80,45));
			nickName.setForeground(Color.WHITE);
			nickName.setFont(new Font("Dialog", Font.BOLD, 15));
			nickName.setSize(new Dimension(60,20));
			nickName.addMouseListener(this);
		}
		return nickName;
	}

	/**
	 * ��ȡ״̬
	 */
	private JMenuBar getStateMenu(){
		if(stateMenu == null){
			stateMenu = new JMenuBar();
			stateMenu.setBorder(null);
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/status_online.png"));
			jMenu = new JMenu();
			jMenu.setIcon(icon);
			jMenu.setOpaque(false);
			JMenuItem[] jMenuItems=new JMenuItem[7];
			String imageStr[]={"status_online.png","status_callme.png","status_away.png","status_busy.png","status_silent.png","status_hidden.png","status_offline.png"};
			String NameStr[]={"��������","Q�Ұ�","�뿪","æµ","�������","����","����"};
			for(int i=0;i<6;i++)
			{
				ImageIcon  ic = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/"+imageStr[i]));
				jMenuItems[i]=new JMenuItem(NameStr[i],ic);
				jMenuItems[i].addActionListener(this);
				jMenuItems[i].setOpaque(false);
				jMenu.add(jMenuItems[i]);
			}	
			stateMenu.add(jMenu);
			stateMenu.setBounds(145, 45, 30, 20);
			stateMenu.setOpaque(false);
			//stateMenu.setBackground(new Color(2,2,2));
			stateMenu.setVisible(true);
			stateMenu.addMouseListener(this);
		}
		return stateMenu;
	}

	/**
	 * ��ȡ����ǩ��
	 */
	private JTextField getSignature(){
		if(signature == null){
			signature = new JTextField();
			//signature.setHorizontalAlignment(JTextField.RIGHT);//�������
			if(uv.getSign()!=null&&!uv.getSign().equals("null")){
				signature.setText(uv.getSign());
			}else{
				signature.setText("�༭����ǩ��");
			}
			signature.setSize(new Dimension(150,20));
			signature.setLocation(new Point(80,65));
			signature.setBorder(new EmptyBorder(0,0,0,0));
			signature.setForeground(Color.WHITE);
			signature.setEditable(false);
			signature.setOpaque(false);
			signature.addMouseListener(this);
		}
		return signature;
	}

	/**
	 * ��ȡ�ռ�Label
	 */
	private JLabel getSpace(){
		if(space == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/space1.png"));
			ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH));
			space = new JLabel(icon1);
			space.setBounds(80, 85, 30, 20);
			space.setOpaque(false);
			space.setBorder(null);
			space.addMouseListener(this);
		}
		return space;
	}

	/**
	 * ��ȡ�ʼ�Label
	 */
	private JLabel getMail(){
		if(mail == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/mail1.png"));
			ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH));
			mail = new JLabel(icon1);
			mail.setBorder(null);
			mail.setOpaque(false);
			mail.setBounds(110, 85, 30, 20);
			mail.addMouseListener(this);
		}
		return mail;
	}

	/**
	 * ��ȡ���Label
	 */
	private JLabel getBlog(){
		if(blog == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/weibo.png"));
			//ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(25, 17, Image.SCALE_SMOOTH));
			blog = new JLabel(icon);
			blog.setOpaque(false);
			blog.setBounds(140, 85, 30, 20);
			blog.addMouseListener(this);
		}
		return blog;
	}

	/**
	 * ��ȡ���Label
	 */
	private JLabel getAdd(){
		if(add == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/add.png"));
			add = new JLabel(icon);
			add.setOpaque(false);
			add.setBounds(170, 85, 30, 20);
			add.addMouseListener(this);
		}
		return add;
	}

	/**
	 * ��ȡ����Label
	 */
	private JLabel getSet(){
		if(set == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/setting.png"));
			set = new JLabel(icon);
			set.setOpaque(false);
			set.setBounds(270, 85, 30, 20);
			set.addMouseListener(this);
		}
		return set;
	}

	/**
	 * ��ȡ����ͼ��
	 */
	private JLabel getSearch(){
		if(search == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/search_normal.png"));
			ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
			search = new JLabel(icon1);
			search.setOpaque(false);
			search.setBounds(280, 115, 20, 30);
			search.addMouseListener(this);
		}
		return search;
	}

	/**
	 * ��ȡ������
	 */
	private JTextField getSearchBar(){
		if(searchBar == null){
			searchBar = new JTextField();
			searchBar.setText("��������ϵ�ˣ������飬Ⱥ����ҵ");
			searchBar.setOpaque(false);
			searchBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			searchBar.setForeground(Color.WHITE);
			searchBar.setBounds(0, 115, 300, 30);
			//searchBar.addMouseListener(this);
		}
		return searchBar;
	}

	/**
	 *��ȡ���ѣ�Ⱥ����Ϣ��� 
	 */
	private JPanel getMiddlePanel(){
		if(middlePanel == null){
			middlePanel = new JPanel();
			middlePanel.setBounds(0, 185, 300,285);
			middlePanel.setLayout(null);
		}
		return middlePanel;
	}

	/**
	 * ��ȡ��ϵ��
	 */
	private JLabel getContact(){
		if(contact == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/group1_down.png"));
			contact = new JLabel(icon);
			contact.addMouseListener(this);
			contact.setOpaque(false);
		}
		return contact;
	}

	/**
	 * ��ȡ������
	 */
	private JLabel getGroup(){
		if(group == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/group2.png"));
			group = new JLabel(icon);
			group.addMouseListener(this);
			group.setOpaque(false);
		}
		return group;
	}

	/**
	 *��ȡ��Ϣ��¼ 
	 */
	private JLabel getMessage(){
		if(message == null){
			ImageIcon icon = new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/group3.png"));
			message = new JLabel(icon);
			message.addMouseListener(this);
			message.setOpaque(false);
		}
		return message;
	}

	/**
	 * ��ȡTP
	 */
	private JPanel getTP(){
		if(tp == null){
			tp = new JPanel();
			tp.setLayout(new GridLayout(1, 4));
			tp.add(getContact());
			tp.add(getGroup());
			tp.add(getMessage());
			tp.setOpaque(false);
			tp.setBounds(0, 145, 300, 40);
		}
		return tp;
	}

	/**
	 * ��ʼ�������б�Ĺ�����
	 */
	private JScrollPane getScrollPane(){
		if(scrollPane == null){
			scrollPane = new JScrollPane(new FriendListPanel(uv.getQq()));
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );//����ʾˮƽ������
			scrollPane.setBounds(0, 0, 300, 285);
		}
		return scrollPane;
	}

	/**
	 *��ȡȺ��� 
	 */
	private JScrollPane getGroupPanel(){
		if(groupPanel == null){
			groupPanel = new JScrollPane(new GroupListPanel(uv.getQq()));
			groupPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );//����ʾˮƽ������
			groupPanel.setBounds(0, 0, 300, 285);
		}
		return groupPanel;
	}

	/**
	 *��ȡ���������� 
	 */
	private JImagePane getBelowPanel(){
		if(belowPanel == null){
			belowPanel = new JImagePane(new ImageIcon(MainUi.class.getResource("/com/xd/imgs/main/lowPanel.jpg")).getImage(),JImagePane.SCALED);
			belowPanel.setLayout(null);
			belowPanel.setBounds(0, 470, 300, 60);
		}
		return belowPanel;
	}

	/**
	 * ������
	 */
	//��갴��������ϵ��������²��ͷţ�ʱ���á�
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == exit){
			ClientBusinessEbi cbi = ClientBusinessFactory.newInstance();
			if(cbi.offline(uv.getQq())){
				try {
					List<Object> list = new ArrayList<Object>();
					String cmd = "end";
					String sqq = "0";
					String rqq = uv.getQq();
					list.add(cmd);
					list.add(sqq);
					list.add(rqq);
					new Send(SocketMap.map.get(uv.getQq()), list).start();
					PrivateChatMap.pcm.remove(uv.getQq());
					System.exit(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "���߲��ɹ�");
			}
		}
		if(e.getSource() == minimize){
			this.setVisible(false);
		}
		if(e.getSource() == skin){
			new SkinUi(uv.getQq()).setVisible(true);
		}
		if(e.getSource() == headLabel){
			new MessageUi(uv).setVisible(true);
		}
		if(e.getSource() == trayIcon){
			this.setVisible(true);
		}
		if(e.getSource() == signature){
			signature.setEditable(true);
			signature.setBackground(Color.white);
		}
		if(e.getSource() == space){
			OpenUrl.openURL("Http://user.qzone.qq.com");
		}
		if(e.getSource() == mail){
			OpenUrl.openURL("Http://mail.qq.com");
		}
		if(e.getSource() == blog){
			OpenUrl.openURL("Http://t.qq.com");
		}
		if(e.getSource() == set){
			new SystemUi();
		}
		if(e.getSource() == search){
			//TODO
		}
		if(e.getSource() == contact){
			ImageIcon icon;
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group2.png"));
			group.setIcon(icon);
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group3.png"));
			message.setIcon(icon);
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group1_down.png"));
			contact.setIcon(icon);
			middlePanel.removeAll();
			middlePanel.add(getScrollPane());
			middlePanel.updateUI();
		}
		if(e.getSource() == group){
			ImageIcon icon ;
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group1.png"));
			contact.setIcon(icon);
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group3.png"));
			message.setIcon(icon);
			icon= new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group2_down.png"));
			group.setIcon(icon);
			middlePanel.removeAll();
			middlePanel.add(getGroupPanel());
			middlePanel.updateUI();
		}
		if(e.getSource() == message){
			ImageIcon icon ;
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group1.png"));
			contact.setIcon(icon);
			icon= new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group2.png"));
			group.setIcon(icon);
			icon = new ImageIcon(LoginUi.class.getResource("/com/xd/imgs/main/group3_down.png"));
			message.setIcon(icon);
			//TODO
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
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
		if(e.getSource() == headLabel){
			headLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		if(e.getSource() == nickName){
			nickName.setToolTipText(nickName.getText());
		}
		if(e.getSource() == stateMenu){
			stateMenu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		if(e.getSource() == signature){
			signature.setToolTipText(signature.getText());
			signature.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		if(e.getSource() == space){
			space.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			space.setToolTipText("�ռ�");
		}
		if(e.getSource() == mail){
			mail.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			mail.setToolTipText("�ʼ�");
		}
		if(e.getSource() == blog){
			blog.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			blog.setToolTipText("����");
		}
		if(e.getSource() == add){
			add.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			add.setToolTipText("���������");
		}
		if(e.getSource() == set){
			set.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			set.setToolTipText("ϵͳ����");
		}
		if(e.getSource() == search){
			search.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			search.setToolTipText("��ѯ����");
		}
		if(e.getSource() == contact){
			contact.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		if(e.getSource() == group){
			group.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
		if(e.getSource() == message){
			message.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
		if(e.getSource() == headLabel){
			headLabel.setBorder(null);
		}
		if(e.getSource() == stateMenu){
			stateMenu.setBorder(null);
		}
		if(e.getSource() == signature){
			signature.setEditable(false);
			signature.setBorder(new EmptyBorder(0,0,0,0));
		}
		if(e.getSource() == space){
			space.setBorder(null);
		}
		if(e.getSource() == mail){
			mail.setBorder(null);
		}
		if(e.getSource() == blog){
			blog.setBorder(null);
		}
		if(e.getSource() == add){
			add.setBorder(null);
		}
		if(e.getSource() == set){
			set.setBorder(null);
		}
		if(e.getSource() == search){
			search.setBorder(null);
		}
		if(e.getSource() == contact){
			contact.setBorder(null);
		}
		if(e.getSource() == group){
			group.setBorder(null);
		}
		if(e.getSource() == message){
			message.setBorder(null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem target = (JMenuItem)e.getSource();
		ImageIcon  ic = (ImageIcon)target.getIcon();
		jMenu.setIcon(ic);	
	}
}
