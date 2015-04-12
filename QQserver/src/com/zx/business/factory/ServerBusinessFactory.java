package com.zx.business.factory;

import com.zx.business.ebi.ServerBusinessEbi;
import com.zx.business.ebo.ServerBusinessEbo;

/**
 * �����࣬�ṩnew ServerBusinessEbo��ʵ��
 * @author zx
 *
 */
public class ServerBusinessFactory {
    public static ServerBusinessEbi newInstance(){
    	return new ServerBusinessEbo();
    }
}
