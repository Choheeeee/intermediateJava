package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class T07TcpChatClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("192.168.141.19",7777);
		
		System.out.println("채팅 서버에 연결되었습니다!");
		
		T08Sender sender = new T08Sender(socket);
		T09Receiver receiver = new T09Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}
