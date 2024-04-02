package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//서버역할 클래스
public class T04TcpSocketServer {
	
	public static void main(String[] args) throws IOException {
		
		
	 	//소켓이란? 두 호스트간 통신을 하기 위한 양 끝단(EndPoint)을 말한다.(데이터를 주고받기(=통신을위한) 위한 도구)
		//통신을 하려면 소켓이 반드시 필요함
	 	//TCP소켓 통신을 하기 위한 ServerSocket객체 생성(서버 소켓에 포트번호 7777지정) 
		//(클라이언트가 자신의 소켓을 통해 서버에 데이터를 요청함 -> 서버도 요청에 응답하기 위해(=통신하기위해) 자신의 소켓을 이용) 
		//즉 통신하려면 서버와 클라이언트의 소켓 한쌍이 필요함(서버와 클라이언트 양쪽 끝단에 1개씩)!
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다..");
		
		//accept()메서드는 클라이언트에서 연결이 될때까지 클라이언트가 접속하기를 기다리고 있음.
		//연결 요청이 오면 Socket객체를 생성해서 클라이언트 소켓과 연결한다.
		Socket socket = server.accept();
		
		////////////////////////////////////////////////////////////
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : "+socket.getInetAddress());
		
		//클라이언트에게 메세지 보내기
		OutputStream out = socket.getOutputStream();
		
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("히수안녕....");	//메세지 보내기
		System.out.println("메세지를 보냈습니다.");
		
		dos.close();
		server.close();
	}
}
