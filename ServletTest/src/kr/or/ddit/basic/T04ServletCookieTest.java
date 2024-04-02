package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
쿠키 : 서버에 메모리 부담이 가지않도록 서버가 아닌 브라우저(클라이언트)에 저장하는 작은 정보 덩어리  
		(브라우저에 사용자의 정보를 미리 저장해 놓는다 해서 북마크, 책갈피같은 개념)

	- 쿠키는 클라이언트 측에서 사용자 관련 데이터를 저장하고 유지하기 위한 목적으로 사용
	- 브라우저(pc)에 저장해놓기 때문에 보안에 취약하지만, 서버의 리소스에 접속하지 않아도 됨
	- http프로토콜은 기본적으로 연결을 맺는게 기본 작동방식이기 때문에 
		클라이언트가 요청을 할때마다 서버는 새로운 연결을 계속 맺으므로, 전에 연결맺었던 동일한 클라이언트인지 알 수가 없음.
		또한, 그 클라이언트의 해당 정보를 기억하고있지 않음.
		그래서 클라이언트에게 cookie를 심어놓고 클라이언트에서 사용자의 정보를 효율적으로 관리할 수 있음 
		
		
	목적: 쿠키는 클라이언트 측에서 사용자의 로컬 브라우저에 정보를 저장하고 유지하기 위한 목적으로 사용됨. 
			주로 세션 관리, 사용자 식별, 사용자 환경 설정 등을 위해 사용됨.

	저장 위치: 쿠키는 클라이언트(사용자의 브라우저)에 저장됨. 이 정보는 웹 사이트를 방문할 때 클라이언트로부터 서버로 자동으로 전송됨.

	수명: 쿠키는 설정된 만료 날짜 또는 세션 종료까지 유지될 수 있으며, 브라우저가 닫히더라도 지속될 수 있음.

	보안: 쿠키는 클라이언트 측에 저장되므로 민감한 정보(비밀번호, 개인 데이터)를 저장하기 위해서는 보안에 신경을 써야함.
	
 */

////////////////////////////////////////////////////////////////////////////////////////

/*
		 	쿠키 정보 : 웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 정보나 설정 상태를 유지함
		 	
		 		1. 구성요소
		 			- 이름
		 			- 값
		 			- 만료날짜(초) : 만료날짜 이후에는 쿠키가 삭제되며, 설정하지 않으면 브라우저 세션이 종료될때 삭제됨
		 			- 도메인 : 쿠키가 유효한 도메인을 지정
		 			- 경로 : 쿠키가 유효한 경로를 지정 (지정하지 않으면 실행한 URL의 경로부분이 사용됨)
		 			- 보안 : 보안쿠키로 설정 가능(https프로토콜을 통해서만 접근 가능)
		 			- httpOnly : JavaScript를 통해 액세스할 수 없으므로 보안을 강화
 */
public class T04ServletCookieTest extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		setCookieExam(req, resp);
//		readCookieExam(req,resp);
//		deleteCookieExam(req,resp);
//		
	}
	
	//만든 쿠키를 백단에서 삭제하는 메서드
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/*
		 	사용중인 쿠키정보를 삭제하는 방법
		 		1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
		 		2. 쿠키객체의 최대지속(유효)시간을 0으로 설정한다.
		 		3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
		 */
		
		//브라우저가 보내주는 쿠키값을 가져오고 싶을때
		Cookie[] cookies = req.getCookies();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 삭제 예제";
		
		out.print("<!DOCYPE html><head><title>" + title
					+ "</title></head><body>");	//삭제하기 위해 쿠키정보를 읽어서 가져옴
		
		//쿠키정보를 잘 가져와서 null이 아니라면 삭제하기
		if (cookies != null) {
			out.print("<h2>"+title+"</h2>");
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {	//userId라는 쿠키를 삭제하려 함
					
					cookie.setMaxAge(0); //0을셋팅하면 삭제하고 싶다는 의미
					
					resp.addCookie(cookie);	//0초로 셋팅한 즉 변경된 쿠키정보를 다시 브라우저에 응답을 날려줌으로써 쿠키 삭제시키기
					out.print("<p>삭제한쿠키 : "+cookie.getName()+"</p><br>");
					
				}
				out.print("<p>name : "+cookie.getName()+"</p><br>");
				out.print("<p>value : "+URLDecoder.decode(cookie.getValue(),"UTF-8")+"</p><br>");
				out.print("<hr>");
			}
			
		}else {
			out.print("<h2>쿠키정보가 없습니다. </h2>");
		}
		
		out.print("</body></html>");
	}


	//쿠키정보 저장하는 메서드
	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//쿠키 생성하기(요청이 정보와함께 , 서버로 날라오는데 그 정보를 꺼낼땐 getParameter()를 쓰고, form의 name속성을 키값으로 해서 값을 얻는다.)
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		//쿠키값에 한글을 사용시 인코딩 처리를 해준다.
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "UTF-8"));
		
		
		//쿠키 소멸시간 설정(초단위) => 설정하지 않으면 브라우저 종료시 쿠키도 함께 삭제된다.
		userId.setMaxAge(60 * 60* 24);	//하루를 초로 환산 (id정보는 하루동안 보관)
		userId.setHttpOnly(true);	//javascript를 이용한 직접 접근 방지(http만 접근 가능)
		
		name.setMaxAge(60 * 60 * 48);	//name정보는 이틀동안 보관
		
		//////////////////////////////////////////////////////////////////////////////
		
		//응답헤더에 쿠키정보 추가하기
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		//브라우저의 응답헤더에 저장할 쿠키정보 셋팅하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키설정 예제";
		out.print("<!DOCTYPE html><html><head><title>"+title
				+"</title></head>"
				+"<body><h1 align=\"center\">"+title+"</h1>"
				+"<ul><li><b>ID</b>: "
				+req.getParameter("userId")+"</li>"
				+"<li><b>이름</b>: "
				+req.getParameter("name")+"</li></ul></body></html>");
	}


	//쿠키정보 읽는 메서드
	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//브라우저가 보내주는 쿠키값을 가져오고 싶을때
		Cookie[] cookies = req.getCookies();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 읽기 예제";
		
		out.print("<!DOCYPE html><head><title>" + title
					+ "</title></head><body>");
		
		if (cookies != null) {
			out.print("<h2>"+title+"</h2>");
			
			for (Cookie cookie : cookies) {
				out.print("<p>name : "+cookie.getName()+"</p><br>");
				out.print("<p>value : "+URLDecoder.decode(cookie.getValue(),"UTF-8")+"</p><br>");
				out.print("<hr>");
			}
			
		}else {
			out.print("<h2>쿠키정보가 없습니다. </h2>");
		}
		
		out.print("</body></html>");
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
