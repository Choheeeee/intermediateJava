package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//쿠키는 브라우저에 보관해서 보안에 취약하다. 그래서 HttpSession을 이용하면 모든 사용자정보를 서버단에서 관리할 수 있음(대신 서버메모리를 차지함)
public class T05ServletSessionTest extends HttpServlet {

	/*
	 	세션(HttpSession)객체
	 	
	 		- 세션을 통해서 사용자(=유저)별로 구분하여 정보를 관리할 수 있다 (세션 ID이용)
	 		- 사용자당 세션이 1개씩 만들어짐 (개별 유저마다의 상태나 정보를 저장할 수 있음)
	 		- 쿠키를 사용할때보다 보안이 향상된다.(정보가 서버에 저장되기 때문)
	 		
	 	세션객체를 가져오는(생성하는)방법
	 		- HttpSession session = req.getSession(boolean);	//request객체를 이용해서 Session객체를 만드는것
	 			boolean : true인경우 => 세션객체가 존재하지 않으면 새로 생성한다.
	 						false인경우 => 세션객체가 존재하지 않으면 null을 리턴한다.
	 						(로그인 정보같은건 세션객체에 저장하는게 좋다, 세션이 유지되는동안(=브라우저가 종료될때까지가 한 세션) 이 정보는 메모리에서 삭제 안되고 유지되기 때문에)
	 						
	 	세션객체 삭제 방법
	 		1. invalidate()메서드 호출
	 		2. setMaxInactiveIntervlal(int interval)메서드 호출
	 			=> 일정시간(초)동안 요청이 없으면 세션 객체 삭제됨
	 		3. web.xml에 <session-config> 설정하기(분단위) - xml설정파일 안에 있는 모든 서블릿에 적용됨
	 		
	 	** 리퀘스트객체의 라이프스코프는 요청이 날라올때 생성되었다가 응답을 끝내면 메모리에서 소멸되어 사라짐. 반면에 Session객체는 세션이 유지되는동안 정보가 유효함
	 	
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//세션을 가져오는데 없으면 새로 생성한다. (HttpServletRequest객체의 getSession()을 이용해서 세션객체를 만듦)
		HttpSession session = req.getSession(true);
		
		//실행시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		//마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		int visitCnt = 0;	//방문횟수
		String userId = "sem";
		
		if (session.isNew()) {	//새로 만들어진 세션객체인지 확인하는 메서드
			title = "첫 방문을 환영합니다.";
			session.setAttribute("userId", userId);
			
		}else {	//재방문하면 아래에서 저장된 visitCnt를 얻고, 증가시킴
			visitCnt = (Integer) session.getAttribute("visitCnt");
			visitCnt++;
			userId = (String) session.getAttribute("userId");
		}
		
		//방문횟수를 setAttribute()로 저장함
		session.setAttribute("visitCnt", visitCnt);
		
		//session.invalidate();	//세션삭제 방법1 : session객체의 자체 메서드인 invalidate()호출 - 로그아웃할때 사용
		session.setMaxInactiveInterval(30);	//세션삭제 방법2 :30초에 한번씩 세션을 초기화시킴 - 은행앱 등 자동로그인 기능 구현할때
		
		////////////////////////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.print("<!DOCTYPE html><html><head><title>" + title
				+"</title></head><body>"
				+"<h1 align=\"center\">" + title + "</h1>"
				+"<h2 align=\"center\">세션 정보</h2>"
				+"<table border=\"1\" align=\"center\">"
				+"<tr bgcolor=\"orange\">"
				+"<th>구분</th><th>값</th></tr>"
				+"<tr><td>세션ID</td><td>"+session.getId()+"</td></tr>"
				+"<tr><td>생성시간</td><td>"+createTime+"</td></tr>"
				+"<tr><td>마지막 접근시간</td><td>"+lastAccessTime+"</td></tr>"
				+"<tr><td>USER ID</td><td>"+userId+"</td></tr>"
				+"<tr><td>방문 횟수</td><td>"+visitCnt+"</td></tr>"
				+"</table></body></html>");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}

