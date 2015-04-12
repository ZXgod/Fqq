package com.zx.business.ebi;

import java.util.List;

import com.xd.bean.UserInfoVo;

/**
 * �ͻ����߼���Ľӿ���
 * @author zx
 *
 */
public interface ClientBusinessEbi {
	
    /**
     *��ȡͷ���� 
     */
	public String getPhotoId(String qq);
	
	/**
      * ��½�����˺���Ϣ�ռ������͸�������
      */
	public List<Object> login(String name,String pwd);
	
	/*
	 * ��½�ɹ��󣬻�ȡ���ѷ��鼰�����еĺ�������
	 */
	public List<Object> getSubGroup(String qq);
	
	/**
	 * ��ȡ���ں��ѵ���Ϣ
	 */
	public UserInfoVo getFriendInfo(String qq);
	
	/**
	 * ����
	 */
	public boolean offline(String qq);
	
	/**
	 * ����
	 */
	public boolean setBg(int id,String qq);
	
	/**
	 * Ⱥ
	 */
	public List<Object> getGroupTable(String qq);
	
	/**
	 * �����ͷ������������ͨ��ͨ��
	 */
	public void startSocket(String qq);
}
