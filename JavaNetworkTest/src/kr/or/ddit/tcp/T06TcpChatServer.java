package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class T06TcpChatServer {

	public static void main(String[] args) {

		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("채팅 서버 준비 완료...");
			
			//클라이언트가 소켓 생성을 완료하면 풀림.
			socket = server.accept();
			
			//서버와 채팅할것이므로 멀티스레드가 필요함
			T08Sender sender = new T08Sender(socket);
			T09Receiver receiver = new T09Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
