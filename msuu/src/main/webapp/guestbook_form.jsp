<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="guestbook_error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 : 작성화면</title>
</head>
<body>
<center>
<h2>방명록 : 작성화면</h2>
<hr>
[<a href="guestbook_control.jsp?action=list">게시물 목록으로</a>]
<form name="form1" method="post" action="guestbook_control.jsp">
<input type=hidden name="action" value="insert">
<table cellpadding=5 cellspacing=0 boarder="1">
<tr>
<td bgcolor ="99CCFF">작성자</td>
<td><input type="text" name="gb_name" size="20"></td>
</tr>

<tr>
<td bgcolor ="99CCFF">email</td>
<td><input type="text" name="gb_email" size="20"></td>
</tr>

<tr>
<td bgcolor ="99CCFF">비밀번호</td>
<td><input type="password" name="gb_passwd" size="20"></td>
</tr>

<tr>
<td colspan ="2"><textarea rows="5" name="gb_contents" cols="40"></textarea></td>
</tr>

<tr>
<td colspan ="2"><input type=submit value="저장"><input type=reset value="취소"></td>
</tr>

</table>

</form>
</center>

</body>
</html>