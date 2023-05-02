<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<link rel="stylesheet" href="/resources/css/board.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
	
		$("#btn_modify").click(function(){ //수정 버튼 클릭시 처리하는 이벤트 리스너
			if($("#writer").val() == '') { alert("이름을 입력하세요."); $("#writer").focus();  return false; }
			if($("#title").val() == '') { alert("제목을 입력하세요."); $("#title").focus(); return false; }	
			if($("#content").val() == '') { alert("내용을 입력하세요."); $("#content").focus(); return false; }
			
			$("#ModifyForm").attr("action","/board/modify").submit();
			
		})//End of $("#btn_modify").click		

	}) //End of $(document).ready	
	
</script>
</head>
<body>

<div>
<img id="topBanner" src="/resources/images/logo.jpg" style="height:10px;">
</div>

<div class="main">
	<h1>게시물 수정</h1>
	<br>
	<div class="ModifyForm">
		<form id="ModifyForm" method="post">
			<input type="text" class="input_field" id="writer" name="writer" value="${view.writer}" readonly>
			<input type="text" class="input_field" id="title" name="title" value="${view.title}">
			<input type="hidden" name="seqno" value="${view.seqno}">
			<textarea class="input_content" id="content" cols="100" rows="500" name="content">${view.content}</textarea>
			<button id="btn_modify" class="btn_modify">수정</button>		
		</form>
	
	</div>
</div>
<br><br>
</body>
</html>