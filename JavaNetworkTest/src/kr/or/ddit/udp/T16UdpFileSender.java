package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class T16UdpFileSender {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiverAddr;
	private int port;	//패킷 전송시 사용할 포트번호
	private File file;
	
	public T16UdpFileSender(String receiverIp, int port) {
		
		try {
			
			ds = new DatagramSocket();
			this.port = port;
			receiverAddr = InetAddress.getByName(receiverIp);
			file = new File("d:/D_Other/동글이2.jpg");
			
			if (!file.exists()) {
				System.out.println("파일이 존재하지 않습니다!");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws Exception {
		
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		sendData("start".getBytes());	//전송 시작을 알려주기 위한 문자열 전송
		
		sendData(file.getName().getBytes());	//파일명을 전송
		
		//1000바이트씩 보낼껀데 언제 끝내는지 알 수 있도록, 총  파일크기정보도 전송
		sendData(String.valueOf(fileSize).getBytes());	//총 파일크기정보 전송
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1000];
		
		while (true) {
			Thread.sleep(10);	//패킷 전송간의 간격을 주기 위해
			
			//1000바이트짜리 버퍼로 1000바이트 단위씩 보내고, 마지막엔 나머지를 읽음
			int readBytes = fis.read(buffer, 0, buffer.length);
			
			//파일을 다 읽은 경우
			if (readBytes == -1) {
				break;
			}
			
			sendData(buffer, readBytes);	//읽어온 파일 내용 전송하기
			
			//읽은바이트를 계속 누적해서 총 읽은 바이트를 알 수 있음
			totalReadBytes += readBytes;
			
			//전송이 몇퍼센트 진행 됐는지 알 수 있도록, 퍼센트 계산
			System.out.println("진행 상태 : "+totalReadBytes+"/"+fileSize + " Byte(s) ("+(totalReadBytes * 100 /fileSize) + " %)");
		}
		long endTime = System.currentTimeMillis();
		long diffTime = endTime - startTime;
		double transferSpeed = fileSize / diffTime;
		
		System.out.println("걸린 시간 : "+diffTime+" (ms)");
		System.out.println("평균 전송속도 : "+ transferSpeed + " bytes/ms");
		
		System.out.println("전송 완료!");
	}
	
	
	public void sendData(byte[] data) {
		sendData(data, data.length);
	}
	
	
	public void sendData(byte[] data, int length) {
		try {
			dp = new DatagramPacket(data, length, receiverAddr, port);
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		new T16UdpFileSender("192.168.141.19", 8888).start();
	}
}
