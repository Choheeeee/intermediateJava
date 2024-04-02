package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05FileStreamTest {

	public static void main(String[] args) {

		FileInputStream fis = null;	//파일 인풋 스트림클래스는 1바이트 단위(바이트기반)로 읽어와서, char타입의 한글은 2바이트단위여서 제대로 읽어오지 못함.
									//그래서 방가방가를 출력하면 이상한 문자가 나오는것.
									//이 문제를 해결해줄 T07예제 참고(InputStreamReader 보조스트림)
									//						(FileWriter, FileReader 문자기반 입출력 스트림 이용)
		
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			int data = 0;
			
			while ((data = fis.read()) != -1) {
				//읽어온 데이터 출력
				System.out.print((char) data);	//txt파일은 컴퓨터언어인 2진수로 돼 있기때문에 char로 형변환후 읽어야함
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
