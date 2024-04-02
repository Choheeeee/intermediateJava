package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


//사용자가 브라우저에 url을 쳤을때(=요청을 했을때), 컨트롤러는 요청에 맞게 해당하는 서블릿으로 연결해줘야함
//JSP는 화면 역할 = html을 베이스로 하고 그 안에서 java사용 가능
//url끝에 .do는 서블릿에 요청하고 있다는 의미
//contextPath
@WebServlet(value = "/member/list.do")	//web.xml 설정파일에 안해도, 어노테이션으로 설정함
public class ListMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//컨트롤러는 서비스에게 요청해서 데이터 가져와 jsp에게 넘길 준비하기
		List<MemberVO> memList = memService.displayAllMember();
		
		//위에서 가져온 데이터를 jsp가 쓸 수 있도록, 잠시 저장해놓음
		req.setAttribute("memList", memList);
		
		//list.jsp에게 화면을 그리도록 위임하는것.
		req.getRequestDispatcher("/views/member/list.jsp").forward(req, resp);
		
		
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
