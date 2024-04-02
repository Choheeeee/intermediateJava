package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


//URLConnection 클래스를 사용하여 웹 페이지의 내용을 가져오는 예제
//URL(네이버 메인 페이지)에 대한 연결을 설정하고 해당 URL의 정보 및 내용을 읽어오는 작업을 수행


public class T03URLConnectionTest {
//네트워크를 통해서 1바이트씩 왔다갔다 하므로 일종의 IO작업(그래서 HttpInputStream이 내부적으로 실행돼 바디내용 읽어옴)
	public static void main(String[] args) throws IOException {

		//URLConnection 클래스 => 애플리케이션과 URL간의 통신연결을 위한 추상 클래스
		//특정 서버(ex:네이버)의 정보가 파일의 내용을 가져오기
		//URLConnection 객체 생성하기
		URL url = new URL("https://www.naver.com/index.html"); //https에서 s는 암호화를 위해 사용된 프로토콜(http와는 다르게 암호화를 해줌)
		
		//URL의 프로토콜이 http이므로 openConnection()은 HttpURLConnectiion을 반환함
		URLConnection urlConn = url.openConnection();	
		
		//URL 정보 및 헤더 가져오기
		System.out.println("Content-Type : "+ urlConn.getContentType());
		System.out.println("Encoding : "+ urlConn.getContentEncoding());
		System.out.println("Content : "+urlConn.getContent());
		
		
		//getHeaderFields()는 헤더의 모든 필드와 값이 저장된 Map을 반환함
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		//Map의 key를 순회하기 위해, 반복자를 생성함
		Iterator<String> it = headerMap.keySet().iterator();
		
		//반복자로 반복문을 돌며 키를 순회
		while (it.hasNext()) {
			
			//반복자에서 다음 HTTP 헤더 필드 이름을 가져옴
			String key = it.next();
			
			//키를 통해 접근한 값을 출력함
			System.out.println(key+ " : "+headerMap.get(key));
		}
		System.out.println("-------------------------------------------------------------------------------------");
		
		
		//getContent()는 URLConnection 객체를 사용하여 원격 서버로의 HTTP 요청을 수행하고, 서버 응답의 내용을 나타내는 데이터 스트림(InputStream)을 반환
		//urlConn.getContent()는 반환값이 Object타입인데, http 프로토콜의 url이므로 정확히는 HttpInputStream타입이라  Object에서 InputStream으로 형변환 해줘야함
		//urlConn 객체를 사용하여 웹 서버로부터 데이터를 읽어오는 InputStream을 가져옴
		//URL 콘텐츠 읽기
		InputStream is = (InputStream) urlConn.getContent();	
		
		//InputStreamReader는 바이트 기반의 InputStream을 문자 기반으로 읽을 수 있도록 해줌.
		InputStreamReader isr = new InputStreamReader(is);
		
		int data = 0;
		
		//isr.read() 메서드를 사용하여 한 번에 하나의 문자(1바이트)를 읽고, 읽은 문자가 -1이 아닌 경우 (파일의 끝이 아닌 경우) 계속 실행
		while ((data = isr.read()) != -1) {
			
			//char 형식으로 형변환 하여, 읽은 바이트기반 데이터를 문자로 변환함
			System.out.print((char) data);
		}
		isr.close();
	}

}
