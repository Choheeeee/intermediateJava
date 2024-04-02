package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet {

	
	/*
	 	서블릿 동작 방식에 대해
	 		1. 사용자가 클라이언트(웹브라우저)의 URL을 클릭하면 Http Request를 서블릿 컨테이너로 전송(요청)한다.
	 		2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
	 			(로딩이 안된 경우에는 로딩처리함. 로딩시 init()메서드가 호출됨.)
	 		3. 서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
	 			- HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.
	 			- 톰캣서버는 요청이 들어올때 개발자를 위해 요청에 관한 작업을 처리할땐 HttpServletRequest인터페이스, 
					응답에 관한 작업을 처리할땐 HttpServletResponse인터페이스 객체를 미리 만들어서 개발자에게 제공하고있음
	 		4. service() 메서드는 메서드타입을 체크하여 적절한 메서드를 호출한다(doGet, doPost, doPut 등)
	 		5. 요청 처리가 완료되면 HttpServletRequest 및 HttpServletResponse 객체는 소멸된다.
	 		6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//요청 객체의 메서드
		System.out.println("getCharacterEncoding() : " +req.getCharacterEncoding());
		System.out.println("getContentLength() : "+req.getContentLength());	//Content는 요청메세지의 바디부분을 의미함
		System.out.println("getQueryStringh() : "+req.getQueryString());
		System.out.println("getProtocol() : "+req.getProtocol());
		System.out.println("getRequestURI() : "+req.getRequestURI());	//애플리케이션 이름 =ServletTest
		System.out.println("getRemoteAddr() : "+ req.getRemoteAddr());
		System.out.println("getRemotePort() : "+ req.getRemotePort());
		
		//Post방식(대표적으로 폼이 날라올때)은 Body에 데이터를 담고 서버에 넘겨주는데, 
		//post방식일때만 인코딩 셋팅해줌으로써 서버가 셋팅한 인코딩으로 꺼내 읽을 수 있도록
		//GET방식인 경우엔 톰캣이 자동으로 인코딩 처리함.
		//요청객체로부터 값을 가져오기 전에 먼저 설정해야 함
		req.setCharacterEncoding("UTF-8");
		
		//사용자가 서버에 던져준 파라미터를 꺼낼땐 getParameter()
		//name으로 날라온 parameter를 꺼냄
		String name = req.getParameter("name");
		
		System.out.println("name => "+name);
		
		//요청 객체에 정보 저장하기
		//tel, addr정보를 임시적으로 저장하는 기능을 HttpServletRequest객체의 setAttribute()메서드를 통해 제공함
		req.setAttribute("tel", "1111-1111");
		req.setAttribute("addr", "대전시 중구 오류동");
		
		//미리 setAttribute()로 값을 저장했을때만, getAttribute()를 쓸 수 있음
		//요청객체에 저장된 정보 꺼내기
		System.out.println("tel => "+req.getAttribute("tel"));
		System.out.println("addr => "+req.getAttribute("addr"));
		
		//응답을 처리하는 rest객체를 이용해서, 응답 내용 생성하기(응답 객체 rest 이용)
		//응답메세지의 response headers부분에 표현되는 부분
		resp.setCharacterEncoding("UTF-8");
		//resp.setContentType("text/plain");	//ContentType()메서드를 이용해서 요청메세지의  바디부분에 담겨있는 구체적인 타입(=마임타입)을 서버에 알려주고 있음
		resp.setHeader("content-Type", "text/plain");	//위의 setContentType()과 똑같음
		
		//실제 응답 메세지 생성하는 부분
		//쏴줘야할 응답이 txt파일이므로, 문자기반 출력스트림 쓰기 
//		PrinterWriter out = resp.getOutputStream();	바이트기반 스트림인 getOutputStream()도 가능
		PrintWriter out = resp.getWriter();
		
		//콘솔이 아닌  브라우저에 출력하는것
		out.println("name => "+name);
		out.println("서블릿 경로 => "+req.getServletPath());
		out.println("컨텍스트 경로 => "+req.getContextPath());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
