<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 리스트 - 관리자 페이지</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border: while; text-align: right"><a
					href="movieWrite.do">영화 정보 등록</a></td>
			</tr>
			<tr>
				<th>타이틀</th>
				<th>가격</th>
				<th>배우</th>
				<th>수정</th>
				<th>삭제</th>
				
			</tr>
			
			<c:forEach var="movie" items="${movieList}">
				<tr class="record">
					<td>${movie.title}</td>
					<td>${movie.price}원</td>
					<td>${movie.actor}</td>
					<td><a href="movieUpdate.do?code=${movie.code}">상품수정</a></td>
					<td><a href="moviedelete.do?code=${movie.code}">상품삭제</a></td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
</body>
</html>