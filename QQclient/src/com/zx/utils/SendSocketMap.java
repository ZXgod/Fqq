package com.zx.utils;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * ��Map<String,Socket>�������QQ���������ͨ��ͨ����(ר���ڷ�����Ϣ��ͨ��)
 * key:���û���QQ��
 * value:���û��������������Socketͨ��
 * @author zx
 *
 */
public class SendSocketMap {
    public static Map<String, Socket> map = new HashMap<String, Socket>();
}