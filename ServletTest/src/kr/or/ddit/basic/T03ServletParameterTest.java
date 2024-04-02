package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03ServletParameterTest extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 	요청 객체로부터 파라미터값을 가져오는 방법
		 	(요청에 정보를 포함해서, 서버로 날라오는데 그 정보를 꺼낼땐 getParameter()를 쓰고, form의 name속성을 키값으로 해서 값을 얻는다.)
		 	
		 	- getParameter() : 파라미터값이 한 개인 경우에 가져올때 사용
		 	- getParameterValues() : 파라미터값이 여러개인 경우에 사용함 ex)checkbox 등 (배열 이용)
		 	- getParameterNames() : 요청객체에 존재하는 모든 파라미터 이름을 가져올때 사용함 (배열 이용)
		 */
		req.setCharacterEncoding("UTF-8");
		
		
		//서버에 요청이 날라올때 정보까지 같이 날라오는데, 그 정보를 꺼낼땐 getParameter()를 쓰고 form의 name속성을 키값으로 해서 값을 얻는다.
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
		String[] foods = req.getParameterValues("food");
		
		///////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		//html문서형식을 만들고 있는데, html문서는 문자열이니까 문자기반 출력스트림 이용
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("<p>username: "+username+"</p>");
		out.println("<p>password: "+password+"</p>");
		out.println("<p>gender: "+gender+"</p>");
		out.println("<p>hobby: "+hobby+"</p>");
		out.println("<p>rlgn: "+rlgn+"</p>");
		
		if (foods != null) {
			for (String food : foods) {
				out.print("<p>food: "+food+"</p>");
			}
			
		}
		
		Enumeration<String> params = req.getParameterNames();
		
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			out.print("<hr>");
			out.print("<p>파라미터 이름: "+paramName+"</p>");
			out.print("<p>파라미터 이름값: "+req.getParameter(paramName)+"</p>");
		}
		
		out.print("</body></html>");
		
		
	}
	
	
	
	//브라우저로부터 날라온 폼 처리하기
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get방식이든 Post방식이든 똑같은 코드를 실행할때 doPost()안에서 doGet()을 호출해서 요청이 Get방식으로 들어왔을때 doGet()에 넘겨버림
		doGet(req, resp);	
	}
	
}
