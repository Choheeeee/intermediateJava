package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class T13FileClient {

	
	private Socket socket;
	private FileOutputStream fos;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public void startClient() {
		
		File file = new File("d:/D_Other/동글이.jpg"); //서버로부터 받고싶은 파일을 저장할 경로 설정
		
		try {
			socket = new Socket("192.168.141.19",7777);
			
			//클라이언트는 서버와 소켓접속이 성공하면 서버에 받고싶은 파일명을 보낸다.
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName());
			
			dis = new DataInputStream(socket.getInputStream());
			
			//서버가 보낸 메세지를 읽는다.
			String resultMsg = dis.readUTF();
			
			//서버가 보낸 메세지가 OK면 = 클라이언트가 요청한 파일이 서버에 있으면
			if (resultMsg.equals("OK")) {
				
				fos = new FileOutputStream(file);
				
				//보조스트림을 통해서 서버로부터 이미지 읽어오기
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				
				
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				
				int data = 0;
				while ((data = bis.read()) != -1) {
					bos.write(data);
					
				}
				
				bis.close();
				bis.close();
				
				System.out.println("파일 다운로드 완료!");
			}else {
				System.out.println(resultMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new T13FileClient().startClient();
	}

}
