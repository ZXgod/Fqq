package com.zx.business.request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zx.utils.CloseUtil;

/**
 * ��ȡͷ���ŵ����󣬷���������ͷ����
 * @author zx
 *
 */
public class GetPhotoIdRequest {
     private String qq;
     private String photoId;
//     public static GetPhotoIdRequest ghi = new GetPhotoIdRequest();
     
     public GetPhotoIdRequest(String qq){
    	 this.qq = qq;
     }
     
//     public void init(String qq){
//    	 this.qq = qq;
//     }
     
     public String getPhotoId(){
    	 try {
			Socket socket = new Socket("localhost",8888);
			List<Object> list = new ArrayList<Object>();
			list.add("CMDphotoid");
			list.add(qq);
			
			//��������
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(list);
			oos.flush();
			
			//��������
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			photoId =  (String) ois.readObject();
			CloseUtil.closeAll(oos,ois);
			socket.close();
		} catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
		}
    	 return photoId;
     }
}
