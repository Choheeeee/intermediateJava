package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


//@WebServlet(value = "/member/insert.do")
@WebServlet("/member/insert.do")	//value만 있을때는 생략 가능
public class InsertMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/views/member/insertForm.jsp").forward(req, resp);
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		int cnt = memService.registerMember(mv);
		
		String msg = "";
		if (cnt >0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//요청은 항상 컨트롤러를 거쳐서 jsp를 가야 nullPointException이 안뜸(jsp에 forward하기 전에 데이터를 준비해야함)
		//req.getRequestDispatcher("/member/list.do").forward(req, resp);
		
		//리다이렉트 처리(리다이렉트정보는 클라이언트에서 사용하기때문에, 우리 프로젝트이름 + /member/list.do를 써줘야함)
		//리다이렉트 시키면 Network-Headers-Location에 302코드가 뜸 => 서버에 요청이 2번 들어감
		resp.sendRedirect(req.getContextPath()+"/member/list.do");
		
		
		//언제 포워드를 쓸지, 언제 리다이렉트를 쓸지 차이를 아는게 중요함
	}
}
