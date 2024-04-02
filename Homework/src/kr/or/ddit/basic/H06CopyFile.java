package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class H06CopyFile {

	public static void main(String[] args) throws IOException {
		
		byte[] buffer = new byte [1024];	//1024byte씩 읽을 버퍼 생성

		FileInputStream fis = new FileInputStream("C:/D_Other/Tulips.jpg");	//fis는 Tulips.jpg 파일을 참조하고 있음. 
																				//이 파일 스트림을 통해 Tulips.jpg 파일의 내용을 읽어오거나 처리할 수 있음.
		FileOutputStream fos = new FileOutputStream("C:/D_Other/복사본_Tulips.jpg");	//FileOutputStream을 사용하여 파일을 생성하고 데이터를 파일에 쓰는 코드. 
																						//Tulips.jpg 파일의 내용을 읽어와서 복사본_Tulips.jpg 파일로 복사하는 작업을 수행합니다
		int readByteNum;
		
		while ((readByteNum = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, readByteNum);
		}
		fis.close();
		fos.close();
		System.out.println("파일 복사 완료!");
	}

}
