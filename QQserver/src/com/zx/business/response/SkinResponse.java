package com.zx.business.response;

import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.factory.ServerDaoFactory;

/**
 * ������Ӧ�������ݿ����ҵ�����Ӧ��������ؿͷ���
 * @author zx
 *
 */
public class SkinResponse {
   private boolean flag;
   private String qq;
   private int id;
  // public static SkinResponse sr = new SkinResponse();
   
   public SkinResponse(int id,String qq){
	   this.id = id;
	   this.qq = qq;
   }
   
//   public void init(int id,String qq){
//	   this.id = id;
//	   this.qq = qq;
//   }
   
   public boolean deal(){
	   ServerDaoAdo sda = ServerDaoFactory.newInstance();
	   flag = sda.setBg(id,qq);
	   return flag;
   }
}
