<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="guestbook_error.jsp" import="guestbook.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록:수정화면</title>
<script>
//삭제 확인을 위한 자바스크립트
function delcheck(){
	//메세지 창을 통해 yes/no 확인
	result=confirm("정말로 삭제하시겠습니까?");
	if(result==true){
		document.forml.action.value="delete";
		document.forml.submit();
	}
	else
		return;
}
</script>
</head>
<%
GuestBook guestbook =(GuestBook)request.getAttribute("gbook");
%>
<body>
<center>
<h2>방명록 : 수정화면</h2>
<hr>
[<a href=guestbook_control.jsp?action=list>게시글목록으로</a>]
<form name=forml method=post action=guestbook_control.jsp>
<input type="hidden" name="gb_id" value="<%=guestbook.getGB_ID()%>">
<input type="hidden" name="action" value="update">

<table cellpadding="5" cellspacing="0" border="1">
<tr>
<td bgcolor="#99CCFF">작 성 자</td>
<td><input type="text" name="gb_name" size="20" value="<%=guestbook.getGB_NAME()%>"></td>
</tr>

<tr>
<td bgcolor="#99CCFF">email</td>
<td><input type="text" name="gb_email" size="20" value="<%=guestbook.getGB_EMAIL()%>"></td>
</tr>

<tr>
<td bgcolor="#99CCFF">전화번호</td>
<td><input type="text" name="gb_tel" size="20" value="<%=guestbook.getGB_TEL()%>"></td>
</tr>

<tr>
<td bgcolor="#99CCFF">비밀번호</td>
<td><input type="password" name="gb_passwd" size="20" value="<%=guestbook.getGB_PASSWD()%>"></td>
</tr>

<tr>
<td colspan="2"><textarea rows="5" name="gb_contents" cols="40"><%=guestbook.getGB_CONTENTS() %></textarea>
</tr>

<tr>
<td colspan=2 align=center><input type=sumit value="저장"><input type=reset value="취소"><input type="button" value="삭제" onclick="delcheck()"></td>
</tr>


</table>

</form>
</center>
</body>
</html>