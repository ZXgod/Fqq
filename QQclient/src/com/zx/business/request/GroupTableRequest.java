package com.zx.business.request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zx.utils.CloseUtil;



/**
 * Ⱥ����
 * @author zx
 *
 */
public class GroupTableRequest {
    private String qq;
    private List<Object> list;
    
    public GroupTableRequest(String qq){
    	this.qq = qq;
    }
    
    @SuppressWarnings("unchecked")
	public List<Object> grouptable(){
    	try {
			Socket socket = new Socket("localhost",8888);
			List<Object> l = new ArrayList<Object>();
			l.add("CMDgrouptable");
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
