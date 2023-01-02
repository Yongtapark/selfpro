<%@page import="guestbook.GuestBook"%>
<%@page import="java.util.ArrayList"%>
<%@page import="guestbook.GuestBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(gb_id){
	pwd=prompt('수정/삭제 하려면 비밀번호를 넣으세요');
	document.location.href="guestbook_control.jsp?action=edit&gb"+gb_id+"&upasswd="+pwd;
}
</script>
</head>
<body>
<%GuestBean gb=new GuestBean();
ArrayList<GuestBook> datas=gb.getDBList();
%>
<center>
<h2>방명록: 목록화면</h2>
<hr>
<form>
<a href="guestbook_form.jsp">방명록 쓰기</a><p>
<table cellpadding="5" cellspacing="0" border="1">
	<tr bgcolor="#99CCFF">
	<td>번호</td>
	<td>작성자</td>
	<td>전화번호</td>
	<td>작성일</td>
	<td>내용</td>
</tr>
<%
// Arraylist 객체를 통해 디비에서 가져온 목록을 출력함.
for(GuestBook guestbook : datas){

%>
<tr>
<td><%=guestbook.getGB_ID()%></td>
<td><%=guestbook.getGB_NAME()%></td>
<td><%=guestbook.getGB_TEL()%></td>
<td><%=guestbook.getGB_DATE()%></td>
<td><a href=javascript:check('<%=guestbook.getGB_ID()%>')><%=guestbook.getGB_CONTENTS()%></td>
</tr>

<%} %>
</table>
</form>
</center>
</body>
</html>