package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//기본타입 입출력 보조 스트림 예제 - DataInputStream & DataOutputStream
public class T13DataIOStreamTest {

	public static void main(String[] args) throws IOException {

		//바이트기반으로 파일 출력
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
		
		//DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해준다.
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동");	//문자열 데이터 출력
		dos.writeInt(17);		//4바이트 정수형 데이터 출력
		dos.writeFloat(3.14f);	//4바이트 실수형(Float)으로 출력
		dos.writeDouble(3.14);	//8바이트 실수형(Double)으로 출력
		dos.writeBoolean(true);	//1바이트 논리형으로 출력
		
		System.out.println("출력완료!");
		
		dos.close();
		
		////////////////////////////////////////////
		
		//바이트기반 파읽 읽는 스트림
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		
		DataInputStream dis = new DataInputStream(fis);
		
		System.out.println("문자열 자료 : "+dis.readUTF());
		System.out.println("정수형 자료 : "+dis.readInt());
		System.out.println("실수형(Float)자료 : "+dis.readFloat());
		System.out.println("실수형(Double)자료 : "+dis.readDouble());
		System.out.println("논리형 자료 : "+dis.readBoolean());
		
		dis.close();
		
	}

}
