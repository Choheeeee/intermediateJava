package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class T15UdpClient {

	private DatagramPacket dp;
	private DatagramSocket ds;
	
	private byte[] msg;
	
	public T15UdpClient() {
		try {
			
			//100바이트까지 담을 수 있도록 초기화 시킴
			msg = new byte[100];
			
			//소켓객체 생성(포트번호 명시하지 않으면 이용가능한 임의의 포트번호가 할당됨)
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	public void start() throws IOException {
		InetAddress serverAddr = InetAddress.getByName("192.168.141.3");
		
		//패킷을 서버로 전송한다. => 의미없는 아무 데이터 1을 보내자마자 서버는 클라이언트가 누군지를 알게됨
		dp = new DatagramPacket(msg, 1, serverAddr, 8888);
		ds.send(dp);
		
		//서버가 대기하고 있다가 시간 문자열 패킷을 클라이언트에게 보내줌
		dp = new DatagramPacket(msg, msg.length);
		ds.receive(dp);	//서버가 보내주는 패킷을 받기 위해 수신한다.
		
		System.out.println("현재 서버 시간 => "+ new String(dp.getData()));
		
		ds.close();	//소캣 종료
	}
	
	
	public static void main(String[] args) throws Exception {
		new T15UdpClient().start();
	}

}
