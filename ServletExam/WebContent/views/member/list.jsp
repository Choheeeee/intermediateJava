<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 퍼센트로 감싸면, 자바코드 구현 가능 / req객체에 넣어놓은 memList 정보들 갖고오기-->
<%

List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
		
<%
	int memSize = memList.size();
	if(memSize > 0) {
		for(MemberVO mv : memList){
		
%>
		<tr>
			<td><%out.print(mv.getMemId()); %></td>
			<td><%=mv.getMemName() %></td>
			<td><%=mv.getMemTel() %></td>
			<td><%=mv.getMemAddr() %></td>
		</tr>
<%
		}
	}else{

%>
	<tr align="center">
		<td colspan"4">회원정보가 존재하지 않습니다.</td>
	</tr>
	
<%
	}
%>
	<tr align="center">
		<td colspan="4"><a href="<%=request.getContextPath()%>/member/insert.do">[회원 등록]</a></td>
	</tr>

	</table>

</body>
</html>