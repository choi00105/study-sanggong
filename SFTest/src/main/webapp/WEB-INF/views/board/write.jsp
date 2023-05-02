<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
<link rel="stylesheet" type="text/css" href="/resources/css/board.css">
<script>
	const register = () => {
		const writer = document.querySelector("#writer");
		const title = document.querySelector("#title");
		const content = document.querySelector("#content");
		
		if(writer.value == ''){alter('이름을 입력 하세요'); writer.focus(); return false;}
		if(title.value == ''){alter('제목을 입력 하세요'); title.focus(); return false;}
		if(content.value == ''){alter('내용을 입력 하세요'); content.focus(); return false;}
		
		document.WriterForm.action = '/board/write';
		document.WriterForm.submit();
	}
</script>
</head>
<body>
	<div>
		<img id="topBanner" src="/resources/images/logo.jpg" title="서울 기술 교육 센터" style="height:30px;">
	</div>
	
	<div class="main">
		<h1>게시물 등록</h1>
		<br>
		<div class="WriterForm">
			<form id="WriterFprm" name="WriterForm" method="POST">
				<input type="text" class="input_field" id="writer" name="writer" value="${username}" readonly>
				<input type="text" class="input_field" id="title" name="title" placeholder="여기에 제목을 입력하세요.">
				<input type="hidden" name="userid" value="$(userid)">
				<textarea class="input_content" id="content" cols="100" rows="100" name="content" placeholder="여기에 내용을 입력하세요."></textarea>
				<input type="button" onclick=>
				<br>
				
				<div class="fileuploadForm">
					<input type="file" id="input_file" name="uploadFile" style="display:none;" multiple />
					<div id="fileClick">파일 첨부를 하기 위해서는 클릭하거나 여기로 파일을 드래그 하세요.<br>첨부파일은 최대 5개까지 등록이 가능합니다.</div>
			  		<div id="fileUploadList"></div>
				</div>
				<br>
				
				<input type="button" onclick="register()" class="btn_write" value="여기를 클릭 하세요">
			</form>
		</div>
	</div>
</body>
</html>