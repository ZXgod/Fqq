package com.zx.business.response;

import java.util.List;

import com.xd.bean.LoginVo;
import com.xd.bean.UserInfoVo;
import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.factory.ServerDaoFactory;

/**
 * �������Կͻ��˴����ĵ�½ָ����г��У���������Ӧ
 * @author zx
 *
 */
public class LoginResponse {
//	public static LoginResponse login = new LoginResponse();//����˼�룬������������new������ʵ��
	private UserInfoVo uv;//�û���Ϣ��
	private LoginVo lv;//�û���½��Ϣ��
    private List<Object> list = null;//�������ݲ㷵�ص���Ϣ
	public LoginResponse(UserInfoVo uv,LoginVo lv){
		this.uv = uv;
		this.lv = lv;
	}

	/**
	 * ��ȡUserInfoVo uv��LoginVo lv����������
	 * @param uv
	 * @param lv
	 */
//	public void getInfo(UserInfoVo uv,LoginVo lv){
//		this.uv = uv;
//		this.lv = lv;
//	}

	/**
	 * ��������
	 */
	public List<Object> deal(){
 	    ServerDaoAdo sda = ServerDaoFactory.newInstance();
	    list = sda.login(uv, lv);
	    return list;
	}

}
