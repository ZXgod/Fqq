package com.zx.dao.factory;

import com.zx.dao.ado.ServerDaoAdo;
import com.zx.dao.impl.ServerDaoImpl;

/**
 * �ṩnew ServerDaoImplʵ���Ĺ�������
 * @author zx
 *
 */
public class ServerDaoFactory {
    public static ServerDaoAdo newInstance(){
    	return new ServerDaoImpl();
    }
}
