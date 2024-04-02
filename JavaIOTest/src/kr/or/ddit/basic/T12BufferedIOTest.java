package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//문자기반 버퍼 IO 이용해보기
public class T12BufferedIOTest {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("src/kr/or/ddit/basic/T11BufferedIOTest.java");
		
		BufferedReader br = new BufferedReader(fr);	//
		
		String tempStr = "";
		
		while ((tempStr = br.readLine()) != null) {	//문자열기반이므로 null을 반환하면 더이상 읽을게 없다는 의미
			
			System.out.println(tempStr);
			
		}
		
		br.close();
		
//		int data = 0;
//		
//		while ((data = fr.read()) != -1) {
//			
//			System.out.print((char)data);
//			
//		}
//		
//		fr.close();
	}

}
