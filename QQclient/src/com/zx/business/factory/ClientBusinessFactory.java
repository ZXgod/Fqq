package com.zx.business.factory;

import com.zx.business.ebi.ClientBusinessEbi;
import com.zx.business.ebo.ClientBusinessEbo;

/**
 * �ṩnew ClientBusinessEbo�Ĺ�������
 * @author zx
 *
 */
public class ClientBusinessFactory {
    public static ClientBusinessEbi newInstance(){
    	return new ClientBusinessEbo();
    }
}
