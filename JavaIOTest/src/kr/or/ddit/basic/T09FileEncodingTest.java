package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09FileEncodingTest {
/*
 	한글 인코딩 방식에 대해
 	
 		- 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식으로 나뉜다.
 		- 원래 한글 윈도우에서는 CP949방식을 사용했는데, 윈도우를 개발한 MS사에서 EUC-KR을 확장해 만들었기 때문에 MS949라고도 부름
 		- 한글 윈도우의 메모장에서 말하는 ANSI 계열 인코딩이란 CP949(Code Page 949)를 의미한다.
 			->MS949 : 한글 윈도우의 기본 한글 인코딩 방식(ANSI 계열)
 			->UTF-8 : 유니코드 UTF-8인코딩 방식 (영문자 및 숫자 : 1byte, 한글 : 3byte) => 가변적
 			->US-ASCII : 영문전용 인코딩 방식
 		
 		ASCII는 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없었다가 나중에 추가하면서 EUC-KR, CP949 등으로 확장됨
 		
 		참고)
 			ASCII : extended ASCII(ISO8859-1)조합형, (KSC 5601)완성형
 			
 			=>윈도우 계열 : CP949(확장완성형) - 일부문자(8822)를 추가함 => ANSI계열 
 			=>유닉스 계열 : EUC-KR(확장 유닉스 코드)
 			
 			
 			=>EUC-KR : 유니코드(UTF-8)
 	
 */
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			//파일 인코딩형식을 미리 지정하여 읽어오기
			//ex) new InputStreamReader(바이트기반 스트림객체, 인코딩방식)
			isr = new InputStreamReader(fis,"UTF-8");
			
			int data = 0;
			while ((data = isr.read()) != -1) {
				System.out.print((char) data);
				
			}
			System.out.println();
			System.out.println("출력 끝!");
		
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				isr.close();	//보조스트림만 닫아도 됨
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
	}

}
