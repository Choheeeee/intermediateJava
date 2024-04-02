package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {

	public static void main(String[] args) {
		
		//배열복사를 통해 간단한 IO작업을 해보자
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		//방법1 - for문을 통해 전형적인 방법으로 배열 복사
//		outSrc = new byte[inSrc.length];	//메모리 공간 inSrc의 크기만큼 확보
//		for (int i = 0; i < inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
//		
//		System.out.println("복사 후 outSrc => " + Arrays.toString(outSrc));
		
		//방법2 - arraycopy()를 이용한 배열 복사
//		outSrc = new byte[inSrc.length];
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
//		System.out.println("arraycopy() 후 outSrc => "+ Arrays.toString(outSrc));
		
		//방법3 - 스트림 클래스를 이용하는 방법
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0;
		
		//read() => 1byte단위로 데이터를 읽어와 int형으로 반환한다. 더이상 읽을 데이터가 없으면 -1을 반환한다. (byte형은 -1을 담을 수 없으므로 변수 data는 int형으로 선언해서 read를 받는다.)
	
		while ((data = bais.read()) != -1) {	//ByteArrayInputStream객체를 통해 inSrc를 읽어오고, data에 저장함
			baos.write(data); 
		}
		//출력된 스트림 데이터를 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => "+ Arrays.toString(inSrc));
		System.out.println("outSrc => "+ Arrays.toString(outSrc));
	}

}
