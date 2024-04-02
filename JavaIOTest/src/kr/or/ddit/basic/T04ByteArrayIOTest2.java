package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest2 {

	public static void main(String[] args) throws IOException {
		
		//배열복사를 통해 간단한 IO작업을 해보자
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4];	//1byte씩 읽는게 비효율적이어서 4바이트씩 읽으려 함  => 4바이트씩 읽는 버퍼 생성
		
		
		//방법3 - 스트림 클래스를 이용하는 방법
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0;
		
		//read() => 1byte단위로 데이터를 읽어와 int형으로 반환한다. 더이상 읽을 데이터가 없으면 -1을 반환한다. (byte형은 -1을 담을 수 없으므로 변수 data는 int형으로 선언해서 read를 받는다.)
		while ((readBytes = bais.read(temp)) != -1) {	//ByteArrayInputStream객체를 통해 읽어온 inSrc를 data에 저장함
			baos.write(temp,0,readBytes);
//			baos.write(temp);
			System.out.println("temp => "+ Arrays.toString(temp));
		}
		//출력된 스트림 데이터를 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => "+ Arrays.toString(inSrc));
		System.out.println("outSrc => "+ Arrays.toString(outSrc));
	}

}
