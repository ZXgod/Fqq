package com.zx.business.ebo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.xd.bean.LoginVo;
import com.xd.bean.UserInfoVo;
import com.zx.business.ebi.ServerBusinessEbi;
import com.zx.business.response.GetFriendInfoResponse;
import com.zx.business.response.GetPhotoIdResponse;
import com.zx.business.response.GetSubGroupResponse;
import com.zx.business.response.GroupTableResponse;
import com.zx.business.response.LoginResponse;
import com.zx.business.response.OfflineResponse;
import com.zx.business.response.SkinResponse;
import com.zx.ui.ServerUi;
import com.zx.utils.CMDUtil;
import com.zx.utils.CloseUtil;
/**
 * ʵ��ServerBusinessEbi�ӿ�
 * @author zx
 *
 */
public class ServerBusinessEbo implements ServerBusinessEbi {
	@Override
	public void startServer() {
		try {
			ServerSocket server = new ServerSocket(8888);//��8888�˿ڼ����ӿͻ��˴�������Ϣ
			ServerUi.smu.area.append("��������:" + server);
			new ServerStartThread(server).start();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

	class ServerStartThread extends Thread{
		private ServerSocket server;
		public ServerStartThread(ServerSocket server){
			this.server = server;
		}
		@Override
		public void run() {
			while(true){
				try {
					Socket client = server.accept();
					new ServerThread(client).start();	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * ��Ϊ���пͻ��˹���һ����������ͬһ���˿ڼ��������ͻ��˴�������Ϣ������һ���ͻ�����Ҫһ����·
	 * �ö��߳̽��
	 * @author TuoTuo
	 *
	 */
	class ServerThread extends Thread{
		private Socket client ;
		private ObjectInputStream ois;
		private List<Object> list =null;
		public ServerThread(Socket client){
			this.client = client;
		}
		
		@SuppressWarnings("unchecked")
		public List<Object> receive(){
			List<Object> list =null;
			try {
				ois = new ObjectInputStream(client.getInputStream());
				list = (List<Object>) ois.readObject();
			} catch (Exception e) {
				CloseUtil.closeAll(ois);
			}
			return list;
		}
		
		@SuppressWarnings("unchecked")
		public void send(){
			list =  receive();
			if(list == null){
				return;
			}
			String cmd = (String) list.get(0);
			
			try {
				//LOGIN
				if(cmd.equals(CMDUtil.LOGIN)){
					UserInfoVo uv = (UserInfoVo) list.get(1);
					LoginVo lv = (LoginVo) list.get(2);
					LoginResponse lr = new LoginResponse(uv, lv);
					List<Object> lt = lr.deal();
					int flag = (Integer) lt.get(0);
					if(flag == 3){
						ServerUi.smu.area.append("\r\n �û�:[" + uv.getQq() + "]��½,"
								+ client);
						ServerUi.smu.lm.addElement(uv.getQq());
					}
					//������Ӧ���ͻ���
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(lt);
					oos.close();
					client.close();
				}

				//SUBGROUP
				if(cmd.equals(CMDUtil.SUBGROUP)){
					List<Object> lt =null;
					String qq = (String) list.get(1); 
					GetSubGroupResponse subgroup = new GetSubGroupResponse(qq);
					lt = subgroup.deal();
					if(lt!=null){
						ServerUi.smu.area.append("\r\n �û�:[" + qq + "]��ȡ������Ϣ�ɹ�,"
								+ client);
					}

					//��������
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(lt);
					oos.close();
					client.close();
				}

				//FRIENDINFO
				if(cmd.equals(CMDUtil.FRIENDINFO)){
					UserInfoVo uv =null;
					String qq = (String) list.get(1);
					GetFriendInfoResponse gfi = new GetFriendInfoResponse(qq);
					uv = gfi.deal();

					//��������
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(uv);
					oos.close();
					client.close();
				}

				//OFFLINE
				if(cmd.equals(CMDUtil.OFFLINE)){
					boolean flag ;
					String qq = (String) list.get(1);
					OfflineResponse off = new OfflineResponse(qq);
					flag = off.deal();
					if(flag){
						ServerUi.smu.area.append("\r\n �û�:[" + qq + "]����,"
								+ client);
						ServerUi.smu.lm.removeElement(qq);
					}

					//��������
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(flag);
					oos.close();
					client.close();
				}

				//PHOTOID
				if(cmd.equals(CMDUtil.PHOTOID)){
					String photoId;
					String qq = (String) list.get(1);
					GetPhotoIdResponse ghi =new GetPhotoIdResponse(qq);
					photoId = ghi.deal();
					if(photoId!=null){
						ServerUi.smu.area.append("\r\n �û�:[" + qq + "]��ȡͷ���ųɹ�,"
								+ client);
					}

					//��������
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(photoId);
					oos.close();
					client.close();
				}

				//SKIN
				if(cmd.equals(CMDUtil.SKIN)){
					boolean flag;
					String qq = (String) list.get(1);
					int id = (Integer) list.get(2);
					SkinResponse sr = new SkinResponse(id, qq);
					flag = sr.deal();
					if(flag){
						ServerUi.smu.area.append("\r\n �û�:[" + qq + "]�����ɹ�,"
								+ client);
					}

					//��������
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(flag);
					oos.close();
					client.close();
				}

				//GROUPTABLE
				if(cmd.equals(CMDUtil.GROUPTABLE)){
					List<Object> lt =null;
					String qq = (String) list.get(1); 
					GroupTableResponse gtr = new GroupTableResponse(qq);
					lt = gtr.deal();
					if(lt!=null){
						ServerUi.smu.area.append("\r\n �û�:[" + qq + "]��ȡȺ��Ϣ�ɹ�,"
								+ client);
					}

					//��������
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(lt);
					oos.close();
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		@Override
		public void run() {
			send();
		}

	}
}
