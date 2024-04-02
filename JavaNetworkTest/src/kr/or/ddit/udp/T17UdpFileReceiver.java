package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class T17UdpFileReceiver {

	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public T17UdpFileReceiver(int port) {
		
		try {
			ds = new DatagramSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void start() throws IOException {
		
		long fileSize = 0;
		long totalReadByte = 0;
		
		int readBytes = 0;
		
		System.out.println("파일 수신 대기 중");
		
		//sender가 패킷을 보낼때까지 대기중
		String str = new String(receiveData()).trim();
		
		if (str.equals("start")) {
			//전송 파일명 받기
			str = new String(receiveData()).trim();
			
			FileOutputStream fos = new FileOutputStream("d:/D_Other/"+str);
			
			str = new String(receiveData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while (true) {
				byte[] data = receiveData();
				readBytes = dp.getLength();	//받은 바이트배열 크기
				
				//파라미터 3개짜리 : 버퍼를 이용하고, 몇바이트까지 의미있는건지 의미있는 데이터만 받으려고
				fos.write(data, 0, readBytes);	//파일 저장
				
				totalReadByte += readBytes;
				
				System.out.println("진행 상태 : "+totalReadByte+"/"
								+fileSize +" Byte(s) ("
								+(totalReadByte * 100 /fileSize)+"%");
				
				if (totalReadByte >= fileSize) {
					break;
				}
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : "+diffTime+" (ms)");
			System.out.println("평균 전송속도 : "+ transferSpeed + " bytes/ms");
			
			System.out.println("수신 완료!");
		}
	}
	
	public byte[] receiveData() throws IOException{
		buffer = new byte[1000];
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return dp.getData();
	}
	
	
	public static void main(String[] args) throws IOException {
		new T17UdpFileReceiver(8888).start();
	}

}
