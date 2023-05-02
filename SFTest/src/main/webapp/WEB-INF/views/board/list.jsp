<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="/resources/css/board.css">
<title>게시물 목록 보기</title>
</head>
<body>
	<div>
		<img id="topBanner" src="/resources/images/logo.jpg" title="서울 기술 교육 센터" style="height:30px;">
	</div>
	<div class="main">
		<h1>게시물 목록 보기</h1>
		<table class="InfoTable">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			
			<tbody>
				<c:forEach items="${list}" var="list">
					<tr onmouseover="this.style.background='#46D2D2'" onmouseout="this.style.background='white'">
						<td>${list.seqno}</td>
						<td style="text-align:left"><a id="hypertext" href="/board/view?seqno=${list.seqno}"
							onmouseover="this.style.textDecoration='underline'"
							onmouseout = "this.style.textDecoration='none'">${list.title}</a></td>
						<td>${list.writer}</td>
						<td>${list.regdate}</td>
						<td>${list.hitno}</td>
					
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p style="text-align:center"><a href="/board/write">글쓰기</a></p>
	</div>
</body>
</html>