package com.zx.business.request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zx.utils.CloseUtil;

/**
 * ��������
 * @author zx
 *
 */
public class SkinRequest {
    private int id;//����ͼƬ���
    private String qq;
    private boolean flag;
//    public static SkinRequest sr = new SkinRequest();
    
    public SkinRequest(int id,String qq){
    	this.id = id;
    	this.qq = qq;
    }
    
//    public void init(int id,String qq){
//    	this.id = id;
//    	this.qq = qq;
//    }
    
    public boolean setBg(){
    	try {
			Socket socket = new Socket("localhost",8888);
			List<Object> l = new ArrayList<Object>();
			l.add("CMDskin");
			l.add(qq);
			l.add(id);
			
			//��������
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(l);
			oos.flush();
			
			//��������
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			flag = (Boolean) ois.readObject();
			CloseUtil.closeAll(oos,ois);
			socket.close();
		}catch(Exception e) {
		  throw new RuntimeException(e.getMessage(),e);	
		}
    	return flag;
    }
}
