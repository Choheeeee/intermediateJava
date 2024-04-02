package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Udp통신방식은 데이터그램소켓 + 데이터그램패킷이 필요함
public class T14UdpServer {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg;
	
	public T14UdpServer(int port) {
		try {
			ds = new DatagramSocket(8888);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		while (true) {
			
			//데이터를 수신하기 위한 패킷을 생성한다. (데이터를 받기위함은 아니고, 그냥 1바이트짜리로 초기화해줌)
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수신 대기중");
			
			//패킷을 통해 데이터를 수신(receive)한다.
			//클라이언트가 데이터를 보내기전까지 기다리고있다가, 상대방이 데이터를 전송하면 풀리면서 작업실행
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료!");
			
			//수신한 패킷으로부터 client의 IP주소와 Port번호를 알아낸다.
			InetAddress addr = dp.getAddress();
			int port = dp.getPort();
			
			
			//서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes();	//시간 문자열을 byte배열로 반환한다.(패킷은 데이터를 바이트단위로 처리하므로, 변환과정 필요)
			
			//패킷을 생성해서 client에게 전송(send)한다.
			dp = new DatagramPacket(msg, msg.length, addr, port);
			ds.send(dp);
			
			//무한루프라서, 다시 위로 올라가서 수신을 대기하고 보내는 과정을 반복
		}
	}
 	public static void main(String[] args) throws Exception {
 		new T14UdpServer(8888).start();
		
	}

}
