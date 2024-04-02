package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.InputStreamReader;

public class T07FileWriterTest {

	public static void main(String[] args) {
		//사용자가 입력한 내용을 그대로 파일로 저장하기
		
		//콘솔(표준입출력장치)과 연결된 입력용 문자 스트림 생성
		
		//InputStreamReader => 바이트 기반 스트림을 문자기반 스트림으로 변환해 주는 보조스트림
		InputStreamReader isr = new InputStreamReader(System.in);	//보조스트림은 말그대로 기본스트림을 보조해주는 역할이므로, 
																	//반드시 기본스트림이 있어야하고, 바이트기반의 기본스트림인 System.in과 함께 쓰여
																	//System.in을 통해 바이트기반으로 들어온 문자를 보조해줘서, 문자기반으로 변환해줌
		
		FileWriter fw = null; //파일 출력용 문자기반 스트림
		
		try {
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요.");
			
			//콘솔에서 입력할 때 입력의 끝 표시는 Ctrl + Z키를 누르면 된다.
			while ((data = isr.read()) != -1) {
				fw.write(data);	//콘솔에 입력한 값을 파일에 출력하기
			}
			
			System.out.println("작업 끝...");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				isr.close();
				fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
