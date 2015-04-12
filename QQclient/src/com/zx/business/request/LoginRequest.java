package com.zx.business.request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.xd.bean.LoginVo;
import com.xd.bean.UserInfoVo;
import com.zx.utils.CloseUtil;

/**
 * ��½���󣬷��͸������������ؽ��
 * @author zx
 *
 */
public class LoginRequest {
    private UserInfoVo uiv= null;//�û��˺ţ�����
    private LoginVo lv = null;//�û�IP���˿ڵ���Ϣ
    private List<Object> ls =null;//���շ�����������Ϣ
//    public static LoginRequest lr = new LoginRequest();
    
    /**
     * ˽�й��캯��������������new�����Ķ���
     */
    public LoginRequest(UserInfoVo uiv,LoginVo lv){
    	this.uiv = uiv;
    	this.lv = lv;
    }
    
//    public void init(UserInfoVo uiv,LoginVo lv){
//    	this.uiv = uiv;
//    	this.lv = lv;
//    }
    
    @SuppressWarnings("unchecked")
	public List<Object> login(){
    	try {
			Socket socket = new Socket("localhost",8888);
			lv.setLip(socket.getLocalAddress().getHostAddress());
			lv.setLport(socket.getLocalPort()+"");
			lv.setLstatus("yes");
			List<Object> list = new ArrayList<Object>();
			list.add("CMDlogin");
			list.add(uiv);
			list.add(lv);
			
			//��������
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(list);
			oos.flush();
			
			//��������
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ls = (List<Object>) ois.readObject();
			CloseUtil.closeAll(oos,ois);
			socket.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
    	return ls;
    }
}
