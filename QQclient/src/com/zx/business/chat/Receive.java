package com.zx.business.chat;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xd.bean.UserInfoVo;
import com.zx.business.ebi.ClientBusinessEbi;
import com.zx.business.factory.ClientBusinessFactory;
import com.zx.ui.main.chat.PrivateChatUi;
import com.zx.utils.DateTransferUtil;
import com.zx.utils.PrivateChatMap;
import com.zx.utils.SocketMap;

/**
 * �������꣬���ͺͽ��ܶ��ǻ�������ģ����Խ��ܺͷ���Ӧ���������������߳���
 * ��������
 * @author zx
 *
 */
public class Receive extends Thread{
	//�ܵ���������
    private ObjectInputStream ois;
    //�̵߳ı�ʶ
    private boolean isRunning = true;
	private List<Object> list=null;
	private Map<String,PrivateChatUi> map=null;
	private Socket client;
	//��ʶ 
	public Receive(String qq){
		this.client = SocketMap.map.get(qq);
	}
	
	/**
	 * ��������
	 */
	@SuppressWarnings("unchecked")
	public void receive(){
		try {
			ois = new ObjectInputStream(client.getInputStream());
			list = (List<Object>) ois.readObject();
		}catch (Exception e) {
			isRunning = false;
		}
	}
	
	@Override
	public void run() {
		while(isRunning){
			receive();
			if(!isRunning){
				return;
			}
			String cmd = (String) list.get(0);//ָ��
			if(!cmd.equals("chat")){
				return;
			}
			String sqq = (String) list.get(1);
			String rqq = (String) list.get(2);
			String msg = (String) list.get(3);
			map = PrivateChatMap.pcm.get(rqq);
			String time = DateTransferUtil.long2String(new Date().getTime());
			ClientBusinessEbi cbe = ClientBusinessFactory.newInstance();
			UserInfoVo uiv = cbe.getFriendInfo(sqq);
			if(map==null){
				map = new HashMap<String, PrivateChatUi>();
				PrivateChatUi pcu = new PrivateChatUi(uiv,rqq);
				map.put(sqq, pcu);
				PrivateChatMap.pcm.put(rqq, map);
				pcu.getShowmessage().append("\r\n"+uiv.getNickname()+"  "+time);
				pcu.getShowmessage().append("\r\n"+msg);
			}else{
				if(map.containsKey(sqq)){
					map.get(sqq).getShowmessage().append("\r\n"+uiv.getNickname()+"  "+time);
					map.get(sqq).getShowmessage().append("\r\n"+msg);
				}else{
					PrivateChatUi pcu = new PrivateChatUi(uiv,rqq);
					map.put(sqq, pcu);
					PrivateChatMap.pcm.remove(rqq);
					PrivateChatMap.pcm.put(rqq, map);
					pcu.getShowmessage().append("\r\n"+uiv.getNickname()+"  "+time);
					pcu.getShowmessage().append("\r\n"+msg);
				}
			}
			System.out.println(rqq+"��Ϣ������ϡ���"+msg);
		}
	}
}
