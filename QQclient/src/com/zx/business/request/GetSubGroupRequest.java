package com.zx.business.request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zx.utils.CloseUtil;

/**
 * ��ȡ�������Լ�����ĺ����б����󡣷��͸������������ؽ��
 * @author zx
 *
 */
public class GetSubGroupRequest {
    private String qq;//QQ��
    private List<Object> list;//���ܷ��������صĽ��
//    public static GetSubGroupRequest gsg = new GetSubGroupRequest();
    
    /**
     * ˽�й��캯��
     */
    public GetSubGroupRequest(String qq){
    	this.qq = qq;
    }
    
    /**
     * ��ʼ������
     */
//    public void init(String qq){
//    	this.qq = qq;
//    }
//    
    @SuppressWarnings("unchecked")
	public List<Object> getSbuGroup(){
    	try {
			Socket socket = new Socket("localhost",8888);
			List<Object> l = new ArrayList<Object>();
			l.add("CMDsubgroup");
			l.add(qq);
			
			//��������
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(l);
			oos.flush();
			
			//��������
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			list = (List<Object>) ois.readObject();
			CloseUtil.closeAll(oos,ois);
			socket.close();
		}catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
		}
    	return list;
    } 
}
