package kr.or.ddit.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class T09WhisperClient {

	public void startClient() {

		Socket socket = null;
		try {
			socket = new Socket("192.168.141.3", 8888);

			System.out.println("귓속말 채팅 서버에 연결 되었습니다.");

			ClientSender sender = new ClientSender(socket);
			sender.start();

			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	class ClientSender extends Thread {
	
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
					System.out.print("사용자명 >> ");
					dos.writeUTF(sc.nextLine());
				}
				
				while (dos != null) {
					System.out.print("채팅 내용을 입력하세요 >> ");
					String message = sc.nextLine();
	                dos.writeUTF(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class ClientReceiver extends Thread {
	
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
			
			try {
				while (dis != null) {
					System.out.println(dis.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new T09WhisperClient().startClient();
	}
}
