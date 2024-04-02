package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class T10MultiChatServer {
	//대화명, 클라이언트의 소켓을 저장하기 위한 맵타입 객체변수 선언
	private Map<String, Socket> clients;
	
	public T10MultiChatServer() {
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	
	//서버 시작(메인스레 담당 : 들어온 클라이언트를 accept해서 다른 소켓에 연결해주기)
	public void startServer() {
		
		//서버소켓
		ServerSocket serverSocket = null;
		
		//클라이언트가 서버소켓에 연결요청을 보내면, 서버소켓은 자신은 빠지고 이 새 socket과 클라이언트소캣을 연결해줌
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("멀티챗 서버가 시작되었습니다!");
			
			while (true) {
				//클라이언트의 접속을 대기하다가, 클라이언트가 연결요청을 보내면 서버소켓은 새 소켓을 생성하여 클라이언트를연결해줌
				//클라이언트와의 연결이 수락되면 생성된 새로운 소켓 객체를 socket 변수에 할당하는 역할을 합니다. 
				//이후 socket을 사용하여 클라이언트와 서버 간의 데이터 통신이 이루어집니다
				socket = serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"]에서 접속했습니다.");
				
				//메세지 전송 처리를 위한 스레드 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 사용자에게 안내 메세지를 전송하는 메서드
	 * @param msg 전송할 안내 메세지
	 */
	public void sendMessage(String msg) {
		Iterator<String> it = clients.keySet().iterator();
		
		while (it.hasNext()) {
			try {
				String name = it.next();	//사용자명 구하기
				
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg);	//메세지 보내기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 사용자에게 사용자들이 서버에 보낸 메세지를  각 사용자에게 전송하는 메서드
	 * @param msg 전송할 채팅 메세지
	 * @param from 메세지 보낸사람
	 */
	public void sendMessage(String msg, String from) {
		sendMessage("["+from+"]"+msg);
	}
	
	
	
	
	
	/**
	 * 서버가 클라이언트로부터 수신한 메세지를 처리하기 위한 스레드클래스
	 * (Inner클래스 (=내부클래스)에서는 Outer(=부모)클래스의 멤버들을 직접 접근할 수 있음.
	 * 
	 * 내부클래스로 선언한 목적 : MultiChatServer01만 ServerReceiver클래스를 쓰기 위해
	 * @author PC-23
	 */
	
	//서버소켓이 클라이언트 소켓을 수락 후, 새로운 소켓을 생성해 서버소켓은 빠지고, 이 새 소켓과 클라이언트소켓을 연결하게 되는데
	//여러 클라이언트들의 연결들을 수락할때마다 새로운 소켓들을 생성함 => 그 여러개의 소켓들 병렬적으로 실행되게 하려고 스레드 클래스로 구현함
	class ServerReceiver extends Thread{
		
		private Socket socket;
		private DataInputStream dis;
		private String name; //소켓을 사용할 클라이언트 이름(사용자 명)
		
		
		
		public ServerReceiver(Socket socket) {
			
			this.socket = socket;
			
			try {
				//소켓의 데이터 읽기
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override	 //소켓접속을 한 클라이언트 수만큼 각 클라이언트를 전담마크 할 스레드가  병렬적으로 만들어짐
		public void run() {
			try {
				//서버에서는 클라이언트가 보내는 최초의 메세지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();
				
				//대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메세지를 보낸다.
				sendMessage("#"+name+"님이 입장했습니다.");
				
				//대화명과 소켓객체를 Map에 저장한다. (클라이언트의 정보를 관리할 맵에 사용자의 채팅입력내용을 저장한다.)
				clients.put(name, socket);
				
				System.out.println("현재 서버 접속자 수는 "+clients.size()+"명 입니다.");
				
				//이 후의 메세지 처리는 반복문으로 처리한다.
				//메세지를 받으면 바로 모든 클라이언트에게 보내준다.
				while (dis != null) {
					
					sendMessage(dis.readUTF(),name);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				//이 finally영역이 실행된다는 것은 클라이언트의 접속에 문제가 생긴 경우이므로 사용자 정리 작업을 해준다.
				sendMessage(name+"님이 나갔습니다.");
				
				//Map에서 해당 사용자를 삭제한다(사용자 정리).
				clients.remove(name);
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"]에서 접속 종료했습니다.");
				
				System.out.println("현재 서버 접속자 수는 "+clients.size()+"명 입니다.");
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new T10MultiChatServer().startServer();
	}
}
