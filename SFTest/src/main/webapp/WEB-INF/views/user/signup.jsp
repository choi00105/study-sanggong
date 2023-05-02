<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<link rel="stylesheet" type="text/css" href="/resources/css/board.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	function register() {
		const userid = $("#userid").val();
		const username = $("#username").val();
		const content = $("#content").val();
		if(userid == '') { alert("아이디를 입력하세요."); $("#userid").focus();  return false; }
		if(username == '') { alert("이름을 입력하세요."); $("#username").focus(); return false; }
		const Pass = $("#password").val();
		const Pass1 = $("#password1").val();
		//if(Pass == '') { alert("암호를 입력하세요."); $("#password").focus(); return false; }
		//if(Pass1 == '') { alert("암호를 입력하세요."); $("#password1").focus(); return false; }
		//if(Pass != Pass1) 
		//	{ alert("입력된 비밀번호를 확인하세요"); $("#password1").focus(); return false; }
		
		// 암호유효성 검사
		// 자바스크립트의 정규식 
		//var num = Pass.search(/[0-9]/g); // 0~9까지의 숫자가 들어 있는지 검색 -> 검색 결과 없으면 -1 리턴
	 	//var eng = Pass.search(/[a-z]/ig); // i: 알파벳 대소문자 구분 없이...
	 	//var spe = Pass.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi); // 특수문자가 포함되어 있는지 검색	
		//if(Pass.length < 8 || Pass.length > 20) { alert("암호는 8자리 ~ 20자리 이내로 입력해주세요."); return false; }
		//else if(Pass.search(/\s/) != -1){ alert("암호는 공백 없이 입력해주세요."); return false; }
		//else if(num < 0 || eng < 0 || spe < 0 ){ alert("암호는 영문,숫자,특수문자를 혼합하여 입력해주세요."); return false; }
	 	
	 	// vanila js
	 	//document.RegistryForm.action = '/user/signup';
	 	//document.RegistryForm.sumbit();
	 	
	 	// jquery
	 	$("#RegistryForm").attr('action','/user/signup').submit();
		
	 	console.log($(userid));
	 	console.log($(username));
	 	console.log($(Pass));
	 	console.log($(Pass1));
	 	console.log($("input[name=gender]").val());
	 	console.log($("input[name=hobby]").val());
	 	console.log($("select[name=job]").val());
	 	
	}
	
	function selectAll(checkElement) {
		const checkboxes = document.getElementsByName("hobby");
		checkboxes.forEach((checkbox) => {
			checkbox.checked = checkElement.checked;
		});
	}
	
	async function idCheck(){
		
		const userid = document.querySelector("#userid");
		
		await fetch('/user/idCheck',{		
			method: "POST",
			body: userid.value,		
		}).then((response) => response.text())
		  .then((data) => {		     
				const idCheckNotice = document.querySelector('#idCheckNotice');
				if(data == 0)
					idCheckNotice.innerHTML = "사용 가능한 아이디입니다.";
				else {
					idCheckNotice.innerHTML = "이미 사용중인 아이디입니다.";
					//userid.value = '';
					userid.focus();
				}
		  });
		
	}
	
</script>
</head>
<body>
 
<div>
	<img id="topBanner" src="/resources/images/logo.jpg" style="height:10px;">
</div>


<div class="main">
	<h1>회원등록</h1><br>
	<div class="WriterForm boardView">
		<form id="RegistryForm" name="RegistryForm" method="post">
			<input type="text" class="input_field" id="userid" name="userid" placeholder="여기에 아이디를 입력해 주세요!" onchange="idcheck()"><br>
			<span id="idchecknotice"></span>
			<input type="text" class="input_field" id="username" name="username" placeholder="여기에 이름을 입력해 주세요!">
			<input type="password" class="input_field" id="password" name="password" placeholder="여기에 패스워드를 입력해 주세요!">
			<input type="password" class="input_field" id="password1" name="password1" placeholder="여기에 비밀번호를 입력해 주세요!">
			<div style="width:90%; text-align:left; position:relative; left:35px; border-bottom:2px solid #adadad; margin:10px; padding: 10px">
				성별 :
				<label><input type="radio" name="gender" value="남성">남성</label>
				<label><input type="radio" name="gender" value="여성">여성</label><br>
				취미 :
				<label><input type="checkbox" name="all" onclick="selectAll(this)">전체선택</label>
				<label><input type="checkbox" name="hobby" value="음악감상">음악감상</label>
				<label><input type="checkbox" name="hobby" value="영화보기">영화보기</label>
				<label><input type="checkbox" name="hobby" value="스포츠">스포츠</label><br>
				직업 :
				<select name="job">
					<option disabled selected>-- 아래의 내용 중에서 선택 --</option>
					<option value="회사원">회사원</option>
					<option value="공무원">공무원</option>
					<option value="자영업">자영업</option>
					
				</select><br>
			</div><br>
			<textarea class="input_content" id="content" cols="100" rows="500" name="discription" placeholder="자기소개">
			</textarea><br>
			<input type="button" class="btn_write" value="여기를 클릭하세요 !!!" onclick="register()">
		</form>
		
	</div>
	
</div>

</body>
</html>