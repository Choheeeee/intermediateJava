package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11BufferedIOTest {

	public static void main(String[] args) {

		//바이트기반 출력스트림(숫자를 표현하는 문자(1~9)도 바이트로 표현되기 때문에)
		FileOutputStream fos = null;
		
		//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192(8KB)로 설정된다.
		BufferedOutputStream bos = null;
		
		try {
			
//			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			bos = new BufferedOutputStream(fos,5);	//5바이트짜리 버퍼를 생성함 (5바이트 단위로 출력함)	
			
			for (char ch = '1'; ch <= '9'; ch++) {

//				fos.write(ch);	//1~9까지 출력해야 하므로, 1바이트 단위로 출력하는  write()를 9번 호출해야함	
//								//파일안에 출력할 내용이 많다면 write()를 n번 호출해야 하므로 데이터 전송시 비효율적임.
//								//그래서 n단위로 돼있는 버퍼를 이용한다.
				
				bos.write(ch);	//1~9까지를 출력해야 하므로, 총 5바이트짜리 버퍼(bos)가 2번 호출됨
								//버퍼는 효율을 위해, 5바이트씩 꽉 찼을때  버퍼에 있는 데이터를 비우지만(=출력하지만) 
								//지금 1~9까지 출력하는데 1개가 부족하다. 나머지 하나가 찰때까지 기다리기엔 비효율적이므로
				bos.flush();	//버퍼가 꽉 안차도 명시적으로 버퍼를 비워주는(=출력해주는) bos.flush()를 사용해 버퍼를 비움으로써 파일에 출력하고 작업을 완료한다.
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
//				fos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
