package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class T08Sender extends Thread {
	
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	
	public T08Sender(Socket socket) {
		try {
			name = "[초힝]";
			
			scan = new Scanner(System.in);
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void run() {
		
		//DataOutputStream이 정상적으로 생성이 됐으면, dos가 null일리가 없으므로 => true
		while (dos != null) {
			try {
				dos.writeUTF(name + " >>> "+scan.nextLine());	//엔터 칠때마다, 보내는 역할
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
