package com.zx.business.response;

import com.xd.bean.UserInfoVo;
import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.factory.ServerDaoFactory;

/**
 * �����ݿ��л�ȡ������Ϣ�����ؽ�����ͻ���
 * @author zx
 *
 */
public class GetFriendInfoResponse {
     private String qq;
     private UserInfoVo uv;
//     public static GetFriendInfoResponse gfi = new GetFriendInfoResponse(); 
     
     public GetFriendInfoResponse(String qq){
    	 this.qq = qq;
     }
     
//     public void init(String qq){
//    	 this.qq = qq;
//     }
     
     public UserInfoVo deal(){
    	 ServerDaoAdo sda = ServerDaoFactory.newInstance();
    	 uv = sda.getFriendInfo(qq);
    	 return uv;
     }
}
