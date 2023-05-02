<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 내용 보기</title>
<link rel="stylesheet" href="/resources/css/board.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
	
		$("#btn_modify").click(function(){ //수정 버튼 클릭시 처리하는 이벤트 리스너
			//수정 화면으로 이동
			document.location.href='/board/modify?seqno=${view.seqno}';			
		})//End of $("#btn_modify").click
		
		$("#btn_delete").click(function(){
			if(confirm("정말로 삭제하시겠습니까?"))
				document.location.href='/board/delete?seqno=${view.seqno}';
		})//End of $("#btn_delete").click
		
	}) //End of $(document).ready	
	
</script>
</head>
<body>

<div>
	<img id="topBanner" src="/resources/images/logo.jpg" style="height:10px;">	
</div>

<div class="main">
	<h1>게시물 내용 보기</h1>
	<br>
	<div class="boardView">
		<div class="field">이름 : ${view.writer}</div>
		<div class="field">제목 : ${view.title}</div>
		<div class="field">날짜 : ${view.regdate}</div>
		<div class="content"><pre>${view.content}</pre></div>
		
		<div style="width:80%; margin:auto">
			<button class="btn_modify" id="btn_modify">수정</button>
			<button class="btn_delete" id="btn_delete">삭제</button>
		</div>		
	</div>
</div>
<br><br>
</body>
</html>