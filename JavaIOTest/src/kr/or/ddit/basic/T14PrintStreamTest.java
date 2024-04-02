package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

//프린터 기능을 제공하는 보조 스트림 예제

public class T14PrintStreamTest {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
		//PrintStream은 모든 타입의 데이터를 출력할 수 있는 기능을 제공하는 OutputStream의 하위 클래스이다.
		PrintStream out = new PrintStream(fos);	//fos파일에 출력(=print : 콘솔에든 파일에든 어떤 위치에든 뭐든 출력해주는 스트림)
		out.print("안녕하세요. PrintStream 입니다.\n");
		out.println("안녕하세요. PrintStream 입니다2.");
		out.println("안녕하세요. PrintStream 입니다3.");
		out.println(fos);
		out.println(3.14);
		
		System.out.println("출력완료");
		
		out.close();
		
		//////////////////////////////////////////
		
		//PrintWriter(인코딩도 추가 가능)가 PrintStream보다 다양한 언어의 문자를 처리함
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		
//		PrintWriter pw = new PrintWriter(fos2);	36라인에 인코딩정보를 추가함
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "CP949"));	//안시계열의 CP949 인코딩정보 추가
		pw.print("안녕하세요. PrintWriter 입니다. \n");
		pw.println("안녕하세요. PrintWriter 입니다2.");
		pw.println("안녕하세요. PrintWriter 입니다3.");
		pw.println(fos2);
		
		System.out.println("출력완료2");
		
		pw.close();
	}

}
