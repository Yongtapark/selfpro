<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="guestbook_error.jsp" import="guestbook.*" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="gb" class="guestbook.GuestBean"/>
<jsp:useBean id="guestbook" class="guestbook.GuestBook"/>
<jsp:setProperty property="*" name="guestbook"/>
<%
//사용자 요청을 구분하기 위한 파라미터를 저장할 변수
String action =request.getParameter("action");

//1.게시물 목록 요청인 경우
if(action.equals("list")){
	response.sendRedirect("guestbook_list.jsp");	
}
//2.게시물 등록 요청인 경우
else if(action.equals("insert")){
	if(gb.insertDB(guestbook)){
		response.sendRedirect("guestbook_list.jsp");}
	else{
		throw new Exception("DB입력 오류");
	}
}
//3. 게시물 수정화면 요청인 경우
else if(action.equals("edit")){
	GuestBook gbook =gb.getDB(guestbook.getGB_ID());
		if(!gbook.getGB_PASSWD().equals(request.getParameter("upasswd"))){
			out.println("<script>alert('비밀번호가 틀렸습니다.');history.go(-1);</script>");
			
	}else{
		request.setAttribute("gbook", gbook);
		pageContext.forward("guestbook_edit_form.jsp");
	}
}
//4. 게시물 수정 처리인 경우
else if(action.equals("update")){
	if(gb.updateDB(guestbook)){
		response.sendRedirect("guestbook_list.jsp");
	}else{
		throw new Exception("DB 갱신오류");
	}}
	//5. 게시물 삭제 요청인 경우
	else if(action.equals("delete")){
		if(gb.deleteDB(guestbook.getGB_ID())){
			response.sendRedirect("guestbook_list.jsp");
		}else{
			throw new Exception("DB삭제 오류");
		}
	}else{
		out.println("<script>alert('action 파라미터를 확인해주세요')</sript>");
	}

%>
</body>
</html>