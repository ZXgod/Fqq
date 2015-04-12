package com.zx.dao.ado;

import java.util.List;

import com.xd.bean.LoginVo;
import com.xd.bean.UserInfoVo;

/**
 * ���������ݲ�ӿ�
 * @author zx
 *
 */
public interface ServerDaoAdo {
	
	/**
	 *�����ݿ��ȡͷ��id 
	 */
	public String getPhotoId(String qq);
	
	/**
	 * �û���֤�����ݿ��и��û����򷵻ظ��û�����Ϣ��û���򷵻ؿ�
	 * @return
	 */
    public List<Object> login(UserInfoVo uv,LoginVo lv);
    
    /**
     * ��ȡ�û����飬���ظ��û������з��飬�Լ�ÿ����ĺ���
     */
    public List<Object> getSubGroup(String qq);
    
    /**
     * ��ȡ������ѵ���Ϣ
     */
    public UserInfoVo getFriendInfo(String qq);
    
    /**
     * ����
     */
    public boolean offline(String qq);
    
    /**
     * ���������ñ���
     */
    public boolean setBg(int id,String qq);
    
    /**
     * ��ȡȺ����
     */
    public List<Object> groupTable(String qq);
}
