package com.zx.business.response;

import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.factory.ServerDaoFactory;

/**
 * ��ȡͷ������Ӧ���Ѵ����ݿ��в�ѯ��ͷ���ŷ��ظ��ͻ���
 * @author zx
 *
 */
public class GetPhotoIdResponse {
     private String qq;
     private String photoid;
//     public static GetPhotoIdResponse ghi = new GetPhotoIdResponse();
     
     public GetPhotoIdResponse(String qq){
    	 this.qq = qq;
     }
     
//     public void init(String qq){
//    	 this.qq = qq;
//     }
     
     public String deal(){
    	 ServerDaoAdo sda = ServerDaoFactory.newInstance();
    	 photoid = sda.getPhotoId(qq);
    	 return photoid;
     }
}
