package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class T10FileEncodingTest {

	public static void main(String[] args) throws IOException {

		/*
		 	키보드로 입력한 내용을 파일로 저장하는데 
		 	out_utf8.txt파일은 'UTF-8'인코딩 방식으로,
 			out_ansi.txt파일은 'CP949'인코딩 방식으로 저장한다.
		 */
		
		//바이트기반 입력스트림으로, 사용자한테 받은 값을 읽어오기
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//바이트기반 출력스트림
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		
		//OutputSteamWriter는 바이트기반 출력스트림을 문자기반 출력스트림인 Writer로 변환시켜주는 보조스트림
		OutputStreamWriter osw1 =  new OutputStreamWriter(fos1,"UTF-8");
		OutputStreamWriter osw2 =  new OutputStreamWriter(fos2,"CP949");
		
		int data = 0;
		
		System.out.println("아무거나 입력하세요.");
		
		while ((data = isr.read()) != -1) {
			osw1.write(data);
			osw2.write(data);	//엔터 후, Ctrl + z 까지 입력해야 파일에 문자가 출력됨
		}
		
		System.out.println("작업원료!");
		
		isr.close();
		osw1.close();
		osw2.close();
	}

}
