package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//클라이언트용 클래스
public class T05TcpSocketClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String serverIp = "192.168.141.19";	//나 자신 localhost를 의미함(자바의 예약어로, 자바가 알고있음)
										//IP : 127.0.0.1
										//호스트이름 : localhost 
		
		System.out.println(serverIp + "서버에 접속중입니다!");
		
		//클라이언트도 소켓을 생성해서 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		//연결이 되면(소켓이 생성되면) 이후의 작업이 진행된다.
		System.out.println("서버에 연결 되었습니다.");
		
		//서버에서 보낸 메세지 받기
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//서버에서 받은 메세지 출력하기
		System.out.println("받은 메세지 : "+ dis.readUTF());
		
		System.out.println("연결종료.");
		
		dis.close();
		
		socket.close();
	}

}
