package com.zx.utils;

import java.util.HashMap;
import java.util.Map;

import com.zx.ui.main.chat.PrivateChatUi;

/**
 * ��Map<String,Map<String,PrivateChatUi>>�������û���������Щ��������
 * key:���û���QQ��
 * value:���û�������ѵ�������棬��Ϊһ���û�����ͬʱ�Ͷ���������죬������List���洢��
 * @author zx
 *
 */
public class PrivateChatMap {
   public static Map<String,Map<String,PrivateChatUi>> pcm = new HashMap<String, Map<String,PrivateChatUi>>();//����˼��
}
