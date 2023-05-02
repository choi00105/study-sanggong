<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<head>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>

<title>게시물 목록</title>

<script>

function search(){
	
	var searchType = $("#searchType").val();
	var keyword =  $("#keyword").val();
	location.href = 'list?page=1&searchType=' + searchType + '&keyword=' + encodeURI(keyword);
	
}
</script>
</head>

<body>
<%

	String userid = (String)session.getAttribute("userid");
	if(userid == null) response.sendRedirect("/");

%>


<div class="tableDiv">

 	 <div>
    	<img id="topBanner" src ="${pageContext.request.contextPath}/resources/images/logo.jpg" title="서울기술교육센터" >
  	</div>

	<h1>게시물 목록 보기</h1>
	<table class="InfoTable">
  		<tr>
   			<th>번호</th>
   			<th>제목</th>
   			<th>작성자</th>
   			<th>조회수</th>
   			<th>작성일</th>
  		</tr>

 		<tbody>
	<c:forEach items="${list}" var="list">
 	<tr onMouseover="this.style.background='#46D2D2';" onmouseout="this.style.background='white';">
		<td>${list.seq}</td>
		<td style="text-align:left;">
			<a id="hypertext" href="view?seqno=${list.seqno}&page=${page}&searchType=${searchType}&keyword=${keyword}" onMouseover='this.style.textDecoration="underline"' 
			  onmouseout="this.style.textDecoration='none';">${list.title}</a>
		</td>  
		<td>${list.writer}</td>
		<td>${list.hitno}</td>
		<td><fmt:formatDate value="${list.regdate}" type="both" /> </td> 
	</tr>
	</c:forEach>	
	</tbody>

	</table>
	<br>

	<div>
		${pageListView}
	</div>

	<br>
	<div>
  		<select id="searchType" name="searchType">
      		<option value="title">제목</option>
      		<option value="content">내용</option>
      		<option value="title_content">제목+내용</option>
      		<option value="writer">작성자</option>
  		</select>
    	<input type="text" id="keyword" name="keyword" />
  		<button type="button" onclick="search()">검색</button>
 	</div>
<br><br>	

	<div class="bottom_menu">
		<a href="/board/list?page=1">처음으로</a>&nbsp;&nbsp;
		<a href="/board/write">글쓰기</a>&nbsp;&nbsp;
		<a href="/member/memberInfo">사용자관리</a>&nbsp;&nbsp;
		<a href="/member/logout">로그아웃</a>&nbsp;&nbsp; 
	</div>

	
</div>
</body>
</html>