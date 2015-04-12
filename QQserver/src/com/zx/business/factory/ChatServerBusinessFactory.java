package com.zx.business.factory;

import com.zx.business.ebi.ChatServerBusinessEbi;
import com.zx.business.ebo.ChatServerBusinessEbo;

/**
 * �����࣬�ṩnew ChatServerBusinessEbo��ʵ��
 * @author zx
 *
 */
public class ChatServerBusinessFactory {
    public static ChatServerBusinessEbi newInstance(){
    	return new ChatServerBusinessEbo();
    }
}
