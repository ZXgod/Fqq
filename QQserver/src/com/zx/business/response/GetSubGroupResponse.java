package com.zx.business.response;

import java.util.List;

import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.factory.ServerDaoFactory;

/**
 * �������Կͻ��˴�����GETSUBGROUPָ����г��У���������Ӧ
 * @author zx
 *
 */
public class GetSubGroupResponse {
//    public static GetSubGroupResponse subgroup = new GetSubGroupResponse();
    private String qq;
    private List<Object> list;
    
    public GetSubGroupResponse(String qq){
    	this.qq = qq;
    }
    
//    public void init(String qq){
//    	this.qq = qq;
//    }
    
    public List<Object> deal(){
    	ServerDaoAdo sdo = ServerDaoFactory.newInstance();
    	list = sdo.getSubGroup(qq);
    	return list;
    }
}
