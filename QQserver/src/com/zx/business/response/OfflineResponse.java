package com.zx.business.response;

import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.factory.ServerDaoFactory;

/**
 * ������Ӧ��true���߳ɹ���false����ʧ�ܣ��ѽ�����ظ��ͻ���
 * @author zx
 *
 */
public class OfflineResponse {
    private String qq;
    private boolean flag;
//    public static OfflineResponse off = new OfflineResponse();
    
    public OfflineResponse(String qq){
    	this.qq = qq;
    }
    
//    public void init(String qq){
//    	this.qq = qq;
//    }
    
    public boolean deal(){
    	ServerDaoAdo sda = ServerDaoFactory.newInstance();
    	flag = sda.offline(qq);
    	return flag;
    }
}
