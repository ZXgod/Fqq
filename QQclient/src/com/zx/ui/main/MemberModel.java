package com.zx.ui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.xd.bean.UserInfoVo;
import com.zx.ui.main.chat.PrivateChatUi;
import com.zx.utils.PrivateChatMap;

public class MemberModel{

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public JButton jButton = null;//��ʾ����ͷ��

	public JPanel jPanel = new JPanel();//ģ��������

	private JLabel lb_nickName = null;//��ʾ�ǳƣ�

	private String pic;

	private String nickname = null;

	private JLabel lb_mood = null;//��ʾ���飻

	private String sign = null;
	private UserInfoVo uv;
	private String qq;
	public MemberModel(UserInfoVo uv,String qq) {
		super();
		this.uv = uv;
		this.qq = qq;
		this.pic = uv.getPhotoID();//ͷ��ࣨ�ж��ַ�������ʵ�֣�������򵥣�
		this.nickname = uv.getNickname();//�ǳƣ�
		this.sign = uv.getSign();//����
		initialize();
	}


	private void initialize() {
		lb_mood = new JLabel();
		lb_mood.setBounds(new Rectangle(51, 30, 131, 20));
		lb_mood.setFont(new Font("Dialog", Font.PLAIN, 12));
	    lb_mood.setText(sign);
		lb_mood.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();
				lb_mood.setToolTipText(lb_mood.getText());
			}
			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();
			}

		});
		lb_nickName = new JLabel();
		lb_nickName.setBounds(new Rectangle(52, 10, 80, 20));
		lb_nickName.setFont(new Font("Dialog", Font.BOLD, 14));
		lb_nickName.setText(nickname);
		lb_nickName.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();
				lb_nickName.setToolTipText(lb_nickName.getText());
			}
			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();
			}

		});
		//jPanel.setBackground(Color.gray);
		jPanel.setSize(new Dimension(300, 60));
		jPanel.setLayout(null);
		jPanel.add(getJButton(), null);
		jPanel.add(lb_nickName, null);
		jPanel.add(lb_mood, null);
		jPanel.addMouseListener(new java.awt.event.MouseAdapter() {  
			public void mouseExited(java.awt.event.MouseEvent e) {
				exchangeExited();//����Ƴ�ģ�������ı䱳����ɫ��
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				exchangeEnter();//����ƽ�ģ�������ı䱳����ɫ��
			}
		});
	}

	private void exchangeEnter() {
		jPanel.setBackground(new Color(192,224,248));
	}

	private void exchangeExited() {
		jPanel.setBackground(null);
	}


	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(8, 10, 40, 40));
			jButton.setBackground(new Color(236, 255, 236));
			//jButton.setBackground(Color.gray);
			//jButton.setEnabled(false);
			ImageIcon icon = new ImageIcon(MemberModel.class.getResource("/com/xd/imgs/head/"+pic + ".png"));
			ImageIcon icon1 = new ImageIcon(icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
			jButton.setIcon(icon1);
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {  
				public void mouseExited(java.awt.event.MouseEvent e) {   
					exchangeExited();//����Ƴ�ģ�������ı䱳����ɫ��
				}  
				public void mouseEntered(java.awt.event.MouseEvent e) {   
					exchangeEnter();//����ƽ�ģ�������ı䱳����ɫ��
				}
			});
			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PrivateChatUi pcu =	new PrivateChatUi(uv,qq);
					Map<String, PrivateChatUi> map1 = PrivateChatMap.pcm.get(qq);
					if(map1 == null){
					    map1 = new HashMap<String, PrivateChatUi>();
						map1.put(uv.getQq(), pcu);
						PrivateChatMap.pcm.put(qq, map1);
					}else{
						map1.put(uv.getQq(), pcu);
						PrivateChatMap.pcm.remove(qq);
						PrivateChatMap.pcm.put(qq, map1 );
					}
				}
			});
		}
		return jButton;
	}
}


