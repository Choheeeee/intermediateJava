package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class T02URLTest {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {

		/*
		 	URL클래스 : 인터넷에 존재하는 서버들의 자원에 접근하기 위한 주소 정보를 관리하기 위한 클래스
		 	(url을 안다는건 그 서버에 접속해서 리소스에 접근한다는것)
		 	
		 */
		
		URL url = new URL("http", "ddit.or.kr",80,"/main/index.html?ttt=123#kkk");
		
		System.out.println("전체 URL 주소 : "+ url.toString());
		
		System.out.println("protocol : "+ url.getProtocol());
		System.out.println("host : "+ url.getHost());
		System.out.println("query : "+ url.getQuery());	//url에 데이터를 담아 서버에 보내는것
		System.out.println("file : "+ url.getFile());	//쿼리정보 포함
		System.out.println("path : "+ url.getPath());	//쿼리정보 미포함
		System.out.println("port : "+ url.getPort());	//서비스 프로그램들을 구분하기 위해(ex - 오라클 : 1521)
		System.out.println("ref : "+ url.getRef());
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
	}

}
