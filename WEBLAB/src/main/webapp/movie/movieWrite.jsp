<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="script/movie.js"></script>
</head>
<body>
<div id="wrap" align="center">
<h1>상품 등록- 관리자 페이지</h1>
<form method="post" enctype="multipart/form-data" name="frm">
<table>
<tr>
<th>영화명</th>
<td><input type="text" name="title" size="80"></td>
</tr>

<tr>
<th>가 격</th>
<td><input type="text" name="price" size="80">원</td>
</tr>

<tr>
<th>감 독</th>
<td><input type="text" name="director" size="80"></td>
</tr>

<tr>
<th>배 우</th>
<td><input type="text" name="actor" size="80"></td>
</tr>

<tr>
<th>포스터</th>
<td><input type="file" name="poster"></td>
</tr>

<tr>
<th>설명</th>
<td><textarea cols="80" rows="10" name="poster"></textarea></td>
</tr>
</table>
<br>
<input type="submit" value="등록" onclick="return movieCheck()">
<input type="reset" value="다시작성">
<input type="button" value="목록" onclick="location.href='movieList.do'">
</form>
</div>
</body>
</html>