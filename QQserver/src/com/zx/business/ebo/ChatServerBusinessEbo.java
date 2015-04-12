package com.zx.business.ebo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.business.ebi.ChatServerBusinessEbi;
import com.zx.ui.ServerUi;
import com.zx.utils.CloseUtil;
/**
 * ʵ��ChatServerBusinessEbi�ӿ�
 * @author zx
 *
 */
public class ChatServerBusinessEbo implements ChatServerBusinessEbi {
	private Map<String,Socket> map = new HashMap<String, Socket>();//�洢ÿ���ͻ��˺ͷ�������ͨ��
	@Override
	public void startServer() {
		try {
			ServerSocket server = new ServerSocket(6666);//��6666�˿ڼ����ӿͻ��˴�������Ϣ
			ServerUi.smu.area.append("\r\n �����������:" + server);
			new startChatThrea(server).start();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

	/**
	 * ���������ͻ��˵��߳�
	 * @author TuoTuo
	 *
	 */
	class startChatThrea extends Thread{
		private ServerSocket server;
		public startChatThrea(ServerSocket server){
			this.server = server;
		}
		@Override
		public void run() {
			while(true){
				try {
					Socket client = server.accept();
					System.out.println("�������յ���Ϣ"+client);
					new chatThread(client).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * �����̣߳�һ���ͻ���һ��ͨ��
	 * @author TuoTuo
	 *
	 */
	class chatThread extends Thread{
		private Socket client;
		private ObjectInputStream ois;
		private List<Object> list =null;
		public chatThread(Socket client){
			this.client = client;
		}

		/**
		 * ������Ϣ
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public List<Object> receive(){
			List<Object> list =null;
			try {
				ois = new ObjectInputStream(client.getInputStream());
				list = (List<Object>) ois.readObject();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return list;	
		}

		/**
		 * ת����Ϣ
		 */
		public void send(){
			list = receive();
			String cmd = (String) list.get(0);
			System.out.println(cmd);
			if(cmd.equals("start")){
				String sqq= (String) list.get(1); 
				map.put(sqq, client);
				ServerUi.smu.area.append("����ͨ���ѽ���:" + sqq);
				System.out.println(sqq+"����ͨ�������ɹ�����");
				list =null;
				return;
			}else if(cmd.equals("end")){
				try {
					CloseUtil.closeAll(ois);
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			String rqq = (String) list.get(2);
			Socket s = map.get(rqq);
			System.out.println(s);
			if(list!=null){
				try {
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(list);
					oos.flush();
					System.out.println(list.get(1)+"������Ϣ��"+list.get(2)+"�ɹ�:"+list.get(3));
					list =null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("��Ϣ����ʧ�ܡ���");
			}
 
		}

		@Override
		public void run() {
			send();
		}
	}
}
