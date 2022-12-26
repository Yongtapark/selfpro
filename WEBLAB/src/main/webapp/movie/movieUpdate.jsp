<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1> 상품 수정 - 관리자 페이지</h1>
<form method="post" enctype="multipart/form-data" name="frm">
<input type="hidden" name="code" value="${movie.code}">
<input type="hidden" name="nomakeImg" value="${product.poster}">
<table>
<tr>
<td>
<c:choose>
<c:when test="${empty movie.poster }">
<img src="upload/noimage.gif">
</c:when>
<c:otherwise>
<img src="upload/${product.poster}">
</c:otherwise>
</c:choose>
</td>
<td>
<table>
<tr>
<th style="width: 80px"> 제목</th>
<td><input type="text" name="title" value="${movie.title}" size="80"></td>
</tr>

<tr>
<th>가격</th>
<td><input type="text" name="price" value="${movie.price}">원</td>
</table>
</tr>
</table>
<br>
<input type="submit" value="수정" onclick="return movieCheck()"> <input type="reset" value="다시 작성">
<input type="button" value="목록" onclick="location.href='movieList.do'">
</form>
</div>
</body>
</html>