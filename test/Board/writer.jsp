<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
<script>
	console.log(document.WriteForm);
	console.log(document.forms[0]);
		function reqisterForm() {
			// 유효성 처리
			if(document.WriteForm.write.value===''){
				alert("이름을 입력하세요!!!");
				document.WriteForm.writer.focus();
				return false;
			}
			if(document.WriteForm.title.value===''){
				alert("제목을 입력하세요!!!");
				document.WriteForm.title.focus();
				return false;
			}
			if(document.WriteForm.content.value===''){
				alert("내용을 입력하세요!!!");
				document.WriteForm.content.focus();
				return false;
			}
			
			document.WriteForm.action = '/board/write';
			document.WriteFomr.submit();
		}
	
	
	const registerForm1 = () =>{	
		let writer = document.querySelector('#writer');	
		let title = document.querySelector('#title');
		let content = document.querySelector('#content');
		
		if(writer.value ===''){
			alert("이름을 입력 하세요.");
			writer.focus();
			return false;
		}
		if(title.value ===''){
			alert("이름을 입력 하세요.");
			title.focus();
			return false;
		}
		if(content.value ===''){
			alert("이름을 입력 하세요.");
			content.focus();
			return false;
		}
		document.WriteForm.action = '/board/write';
		document.WriteForm.submit();
	}
	</script>
</head>
<body>
	<h1>게시물 등록</h1>
	
	<br>
	<form name="WriteForm" method="post">
		<input type="text" id="writer" name="writer" placeholder="작성자 이름을 입력하세요."><br>
		<input type="text" id="title" name="title" placeholder="제목을 입력 하세요."><br><br>
		<textarea cols="20" rows="20" id="content" name = "content" placeholder="내용을 입력하세요.">왜안돼</textarea>
		<br/><br/>
		<input type="button" value="등록" onclick="registerForm1()">
		<input type="button" value="취소" onclick="history.back()">
	</form>
</body>
</html>