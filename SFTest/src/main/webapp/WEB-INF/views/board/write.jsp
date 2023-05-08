<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
<link rel="stylesheet" href="/resources/css/board.css">
<script>
	const register = () => {
		
		const writer = document.querySelector("#writer");
		const title = document.querySelector("#title");
		const content = document.querySelector("#content");
		
		if(writer.value == '') {alert('이름을 입력하세요'); writer.focus(); return false;}
		if(title.value == '') {alert('제목을 입력하세요'); title.focus(); return false;}
		if(content.value == '') {alert('내용을 입력하세요'); content.focus(); return false;}
		
		document.WriteForm.action = '/board/write';
		document.WriteForm.submit();
		
	}
</script>
<style>
.btn_cancel  {
  position:relative;
  left:20%;
  transform: translateX(-50%);
  margin-top: 20px;
  margin-bottom: 10px;
  width:40%;
  height:40px;
  background: pink;
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}
</style>

</head>
<body>

<%

	String userid = (String)session.getAttribute("userid");
	if(userid == null) response.sendRedirect("/user/login");

%>

<div>
	<img id="topBanner" src="/resources/images/logo.jpg" title="서울기술교육센터">	
</div>

<div class="main">
	<h1>게시물 등록</h1>
	<br>
		<div class="WriteForm">
			<form id="WriteForm" name="WriteForm" method="POST" enctype="multipart/form-data">
				<input type="text" class="input_field" id="writer" name="writer" value="${username}" readonly>
				<input type="text" class="input_field" id="title" name="title" placeholder="여기에 제목을 입력하세요">
				<input type="hidden" name="userid" value="${userid}">
				<textarea class="input_content" id="content" cols="100" rows="100" name="content" placeholder="여기에 내용을 입력하세요"></textarea>
				<br><input type="file" name="fileUpload"><br>
				<input type="button" class="btn_write" onclick="register()" value="등록"> 
				<input type="button" class="btn_cancel" onclick="history.back()" value="취소">
			</form>
		
		</div>
</div>
<br><br>
				
</body>
</html>