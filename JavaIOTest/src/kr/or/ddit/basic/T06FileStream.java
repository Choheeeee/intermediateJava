package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//파일 출력 예제
public class T06FileStream {

	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/out.txt", true); //2번째 인자 = append로 true로 써주면, 추가됨
			
			for (char ch = 'a'; ch <= 'z'; ch++) {
				fos.write(ch);
			}
			System.out.println("파일 쓰기작업 완료!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch ( IOException e) {
				
			}
		}
		System.out.println("===========================================");
		System.out.println("파일 읽어오기 완료!");
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while ((data = fis.read()) != -1) {
				System.out.println((char)data);	//txt파일은 사실상 이진수(컴퓨터언어)로 되어있고, 우리가 자바로 txt파일을 읽어오면
												//인간이 볼 수 있는 언어로 표현해줘야 하므로, char로 형변환해줘야함.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
