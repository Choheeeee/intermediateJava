package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class T11MultiChatClient {

	//클라이언트 시작
	public void startClient() {
		
		//socket 변수는 서버와 연결될 클라이언트 자신측의 소켓을 나타내며, 클라이언트 소켓을 생성하고 서버에 연결하기 위해 사용되는 변수
		Socket socket = null;
		
		try {
			//클라이언트소켓이 연결될 서버IP와 port번호를 입력함으로써, 클라이언트소켓 객체가 생성됨
			socket = new Socket("192.168.141.8",7777);
			
			System.out.println("멀티챗 서버에 연결되었습니다.");
			
			//송신용 스레드 생성 및 실행
			ClientSender sender = new ClientSender(socket);
			sender.start();
			
			//수신용 스레드 생성 및 실행
			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();
			
			//여기까지만 하고, 메인스레드는 할일이 없으면 죽는다.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//메세지 전송을 처리하는 스레드
	class ClientSender extends Thread{
		private DataOutputStream dos;
		private Scanner sc;
		
		public ClientSender(Socket socket) {
			sc = new Scanner(System.in);
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		@Override
		public void run() {
			
			try {
				if (dos != null) {
					//시작하자마자 자신의 사용자명을 서버로 전송한다.
					System.out.println("사용자명 >>> ");
					dos.writeUTF(sc.nextLine());
				}
				
				while (dos != null) {
					
					//키보드로 입력받은 메세지를 서버로 전송한다.
					dos.writeUTF(sc.nextLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//메세지 수신을 처리하는 스레드
	class ClientReceiver extends Thread{
		private DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
			//반복문을 돌면서 주구장창  메세지만 읽는 스레드
			while (dis != null) {
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		new T11MultiChatClient().startClient();
	}
}
