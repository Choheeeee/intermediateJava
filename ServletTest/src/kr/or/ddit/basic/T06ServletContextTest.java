package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//HttpResponse객체
//HttpRequest 객체 : 요청 당 1개씩 만들어짐 - 서버가 응답까지 다 주고 나면 소멸되는 객체
//Cookie 객체
//HttpSession 객체 : 사용자 당 1개씩 만들어짐
//ServletContext 객체 : 웹애플리케이션 당 1개씩 만들어짐(웹사이트 전체가 공유해야할 내용이면, 웹어플리케이션을 대표하는 ServletContext객체에  저장하는게 좋다)
public class T06ServletContextTest extends HttpServlet {
	/*
	 	서블릿 컨텍스트 객체
	 	
	 		1. 서블릿이 컨테이너와 정보를 주고받기 위한 유용한 메서드를 제공한
	 			ex) 파일의 MIME TYPE정보 가져오기, 요청정보 forwarding, 로깅처리 등
	 			
	 		2. 웹애플리케이션(웹사이트) 당 1개씩 생성된다.
	 			(애플리케이션이 종료될때(=서버 톰캣이 종료될때_까지 계속 살아있는 라이프스코프를 가짐) => 전역변수같은 느김
	 		
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//ServletContext = 애플리케이션을 대표하는 객체 
		ServletContext ctx = req.getServletContext();
		
		System.out.println("서블릿 컨텍스트의 기본 경로 : "+ ctx.getContextPath());
		System.out.println("서버 정보 : "+ctx.getServerInfo());
		System.out.println("서블릿 API의 메이저 버전 정보 : "+ ctx.getMajorVersion());
		System.out.println("서블릿 API의 마이너 버전 정보 : "+ctx.getMinorVersion());
		System.out.println("파일에 대한 MIME타입 정보 : "+ctx.getMimeType("abc.mp3"));
		System.out.println("파일시스템상의 실제 경로 : "+ctx.getRealPath("/"));
		
		//속성갑 설정
		ctx.setAttribute("attr1", "속성1");
		
		//속성값 변경
		ctx.setAttribute("attr1", "속성2");
		
		//속성값 가져와서 출력하기
		System.out.println("attr1의 속성값 : "+ctx.getAttribute("attr1"));
		
		//속성값 제거하기
		ctx.removeAttribute("attr1");
		
		//로깅 작업하기(로그파일)
		ctx.log("서블릿 컨텍스트를 이용한 로깅 작업중입니다!!!");
		
		//포워딩(forwarding)처리
//		ctx.getRequestDispatcher(("포워딩할 경로정보").forwar
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
