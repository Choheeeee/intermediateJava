package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class T12TcpFileServer {
//서버는 클라이언트가 소켓을 통해 서버에 접속하면 컴퓨터의 D_Other 폴더에 있는 이미지를 클라이언트에 보내준다.
	
	private ServerSocket server;
	private Socket socket;
	private FileInputStream fis;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void startServer() {
		try {
			server = new ServerSocket(7777);
			System.out.println("파일 서버 준비 완료!");
			
			String downFolder = "d:/D_Other/";
			
			File file = null;
			
			while (true) {
				System.out.println("파일 전송 대기중!");
				socket = server.accept();
				
				System.out.println("요청파일 존재여부 체크 중!");
				
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				file = new File(downFolder + dis.readUTF());
				
				//클라이언트가 요청한 파일이 실제로 있는지 확인
				if (!file.exists()) {
					System.out.println("요청파일("+file.getName()+")은 존재하지 않습니다.");	//서버 확인용 메세지
					
					dos.writeUTF("요청파일("+file.getName()+")은 존재하지 않습니다.");	//클라이언트에게 보여주기
					
					dis.close();
					dos.close();
					socket.close();
					
					continue;
					
				}else { 	//있으면 OK메세지 클라이언트에게 보내기
					dos.writeUTF("OK");
					System.out.println("요청파일("+file.getName()+")전송시작!");
				}
				
				fis = new FileInputStream(file);
				
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
				
				int data = 0;
				while ((data = bis.read()) != -1) {
					bos.write(data);
				}
				System.out.println("요청파일("+file.getName()+") 전송완료!");
				
				bis.close();
				bos.close();
				dis.close();
				dos.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new T12TcpFileServer().startServer();
	}
}
