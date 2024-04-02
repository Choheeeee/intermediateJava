package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	서블릿에 대해
 		컨테이너(서블릿 엔진)에 의해 관리되는 자바기반 웹 구성요소로서, 동적인 웹 컨텐츠 생성을 가능하게 해준다.
 */

//HttpServlet을 상속했으므로 이 클래스도 서블랫이 된다.
//어느시점에 각 메서드가 호출되는지 확인하기 위한 예제
//톰캣(왓스 = 서비스엔진)에 의해 service메서드가 진입포인트가 됨
//톰캣은 요청이 들어올때 개발자를 위해 요청에 관한 작업을 처리할땐 HttpServletRequest인터페이스, 
//응답에 관한 작업을 처리할땐 HttpServletResponse인터페이스 객체를 미리 만들어서 개발자에게 제공하고있음
public class T01ServletLifeCycleTest extends HttpServlet {
	
	
	public T01ServletLifeCycleTest() {
		System.out.println("T01ServletLifeCycleTest 생성자 호출됨");
	}

	
	//서비스를 제공하는 메서드 즉, 프로그램의 진입포인트 이기때문에 service()가 우리가 주로 정의할 메서드임.
	//(doGet과 doPost메서드도 우리가 주로 정의할 메서드)
	//ServletRequest인터페이스가 최상위 인터페이스임(HttpServletRequest보다 상위)
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		//실제적인 작업이 시작되는 지점(자바의 main메서드 역할)
		System.out.println("service() 호출됨");
		super.service(req, res);
	}
	
	
	//객체가 생성될때 딱 한번만 호출되는 메서드(그래서 생성자메서드에 넣어주면 됨)
	@Override
	public void init() throws ServletException {
		//초기화 코드 작성
		System.out.println("init() 호출됨");
	}
	
	//URL을 통해서 링크로 이동(=검색), 검색, 조회 등을 할때 GET방식
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//메서드 방식이 GET인 경우 호출됨
		System.out.println("doGet() 호출됨");
	}
	
	
	//Post = (보내다, 부치다의 어원) 폼, 데이터등을 서버에 보내야 하므로 POST방식은 Body부분에 내용을 담아 보낸다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		//메서드 방식이 POST인 경우에 호출됨
		System.out.println("doPost() 호출됨");
	}
	
	
	//콘솔 정지누르는것 말고, 톰캣서버를 stop하면 destroy()메서드가 호출되어 서버를 다운시킴
	//서블릿 객체를 remove(지우기)전에 실행해야할 코드를 작성하면 됨(ex : JDCB코딩시, 자원반납 등에 관한 코드를 작성하면 됨)
	@Override
	public void destroy() {
		//객체 소멸시(=컨테이너로부터 서블릿객체 제거시) 필요한 코드 작성
		System.out.println("destroy() 호출됨");
	}
}
