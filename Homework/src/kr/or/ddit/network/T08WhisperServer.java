package kr.or.ddit.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class T08WhisperServer {

	private Map<String, Socket> clients;
	
	public T08WhisperServer() {
		
		//동기화처리된 Map 생성
		clients = Collections.synchronizedMap(new HashMap<>());
	}
	
	public void startServer() {
		
		ServerSocket serversocket = null;
		Socket socket = null;
		try {
			
			//서버 소켓은 특정 포트에서 클라이언트의 연결을 수신하고 처리하는 데 사용됨
			serversocket = new ServerSocket(10000);
			socket = new Socket();
			System.out.println("귓속말 채팅 서버가 시작되었습니다!");
			
			while (true) {
				
				socket = serversocket.accept();
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"]에서 접속했습니다.");
				
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				serversocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class ServerReceiver extends Thread {
		
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerReceiver(Socket socket) {
			
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				
				name = dis.readUTF();
				
				sendMessage("#"+name+"님이 입장했습니다.");
				
				clients.put(name, socket);
				
				System.out.println("현재 서버 접속자 수는 "+clients.size()+"명 입니다!");
				
				while (dis != null) {
					sendMessage(dis.readUTF(),name);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				
				sendMessage(name+"님이 나갔습니다.");
				
				clients.remove(name);
				
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"에서 접속을 종료했습니다.");
				System.out.println("현재 서버 접속자 수는 "+clients.size()+"명 입니다!");
			}
		}

		private void sendMessage(String msg, String from) {
			
			
			if (msg.startsWith("@")) {
				
				String[] parts = msg.split(" ", 3);
				
				if (parts.length == 3) {
					
					String targetClient = parts[1];
					String whisper = parts[2];
					whisperMessage(targetClient,"["+from+" -> "+targetClient+" ] "+ whisper);
				}
			
			}else {
				sendMessage("["+from+"] "+msg);
			}
			
		}

		private void sendMessage(String msg) {
			
			Iterator<String> it = clients.keySet().iterator();
			
			while (it.hasNext()) {
				
				try {
					String name = it.next();
					
					DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
					
					dos.writeUTF(msg);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
		}
		private void whisperMessage(String targetClient, String msg) {
			
			if (clients.containsKey(targetClient)) {
				try {
					DataOutputStream dos = new DataOutputStream(clients.get(targetClient).getOutputStream());
					dos.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				sendMessage("귓속말 대상인 "+targetClient+"님을 찾을 수 없습니다:(");
			}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		new T08WhisperServer().startServer();
	}

}
