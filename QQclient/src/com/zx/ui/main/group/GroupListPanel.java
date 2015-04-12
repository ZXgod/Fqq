package com.zx.ui.main.group;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zx.business.ebi.ClientBusinessEbi;
import com.zx.business.factory.ClientBusinessFactory;


/**
 * Ⱥ�б����
 * @author zx
 *
 */
@SuppressWarnings("serial")
public class GroupListPanel extends JPanel {
	private int clickB=0;
	/**
	 * ���캯��
	 */
	public GroupListPanel(String qq) {
		ClientBusinessEbi cbe = ClientBusinessFactory.newInstance();
		initialize(cbe.getGroupTable(qq));
	}
	
	/**
	 * ��ʼ�����
	 */
	@SuppressWarnings("unchecked")
	private void initialize(List<Object> list) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(300, 285);
		this.setOpaque(false);
		String groupName=null;
		List<Object> lt=null;
		for(int i=0;i<list.size();i++){
			if(i%2==0){
				groupName = (String) list.get(i);
			}
			if(i%2==1){
				lt= (List<Object>) list.get(i);
				addJLabel(groupName,lt);
			}
		}
	}
	
	/**
	 * ����UI���棻
	 */
	private void update(){
		this.updateUI();
	}
	
	/**
	 * �����ǩ��������ı�ǩȫ����Ϊ�����ӣ�
	 * @param jb
	 */
	private void clickBlack2(JLabel []jb){
		for(int i=1;i<jb.length;i++){
			try{
				jb[i].setVisible(false);
			}catch(Exception e){
				e.printStackTrace();
			}
		} 
		update();
	}
	
	/**
	 * �����ǩ��������ı�ǩȫ����Ϊ���ӣ�
	 * @param jb
	 */
	private void clickBlack(JLabel []jb){
		for(int i=1;i<jb.length;i++){
			try{
				jb[i].setVisible(true);
			}catch(Exception e){
				e.printStackTrace();
			}

		} 
		update();
	}
	
	/**
	 * ���Ⱥ�б�����ݣ�
	 */
	private void addJLabel(String groupName,List<Object> l){
		final JLabel []jb=new JLabel[l.size()+1];
		jb[0] = new JLabel();
		jb[0].setText(groupName);
		jb[0].setIcon(new ImageIcon(GroupListPanel.class.getResource("/com/xd/imgs/main/arrow_left.png")));
		jb[0].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		jb[0].addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				clickB+=1;
				if(clickB%2==1){
					clickBlack(jb);
					jb[0].setIcon(new ImageIcon(GroupListPanel.class.getResource("/com/xd/imgs/main/arrow_down.png")));
				}else{
					clickBlack2(jb);
					jb[0].setIcon(new ImageIcon(GroupListPanel.class.getResource("/com/xd/imgs/main/arrow_left.png")));
				}	    
			}
		});
		this.add(jb[0],null);
		for(int i=1;i<jb.length;i++){
			jb[i]=new JLabel();
			jb[i].setIcon(new ImageIcon(GroupListPanel.class.getResource("/com/xd/imgs/main/bg.jpg")));
			jb[i].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			jb[i].add(new MemberModel((String)l.get(i-1)).jPanel);
			jb[i].setVisible(false);
			this.add(jb[i],null);
		}

	}
}
