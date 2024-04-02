package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class T09Receiver extends Thread {
	
	private DataInputStream dis;
	
	public T09Receiver(Socket socket) {
		
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (dis != null) {
			try {									//받은걸 읽기만 하는 쓰레드
				System.out.println(dis.readUTF());	//상대방이 writeUTF()로 보낸것을 읽고 멈춰있음 => 무한 반복
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
