package com.zx.business.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.zx.utils.CloseUtil;
import com.zx.utils.SendSocketMap;
import com.zx.utils.SocketMap;

/**
 * һ���ͻ���һ��ͨ����д���ڲ���ĺô������Թ����û��������򣬷���������ݣ����ᷢ�����ݴ�����
 * ��������
 */
public class Send extends Thread{
	//�����̵߳ı�ʶ
	//�ܵ��������
	private ObjectOutputStream oos =null;
	private List<Object> list =null;
	private Socket client;
	public Send(Socket client,List<Object> list){
		this.client = client;
		this.list = list;
	}

	public void sendMsg(){
		if(list!=null){
			try {
				oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(list);
				oos.flush();
				if(list.get(0).equals("end")){
					String sqq = (String) list.get(1);
					if(sqq.equals("0")){
						String rqq = (String) list.get(2);
						SocketMap.map.get(rqq).close();
						CloseUtil.closeAll(oos);
						SocketMap.map.remove(rqq);
					}else{
						String rqq = (String) list.get(2);
						SendSocketMap.map.get(rqq).close();
						CloseUtil.closeAll(oos);
						SendSocketMap.map.remove(rqq);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			list = null;
		}
	}


	@Override
	public void run() {
		sendMsg();
	}
}