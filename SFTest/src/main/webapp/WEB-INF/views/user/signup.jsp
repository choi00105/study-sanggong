<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<link rel="stylesheet" href="/resources/css/board.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
const register = async () => {
	
	//유효성 검사
	if($("#userid").val() == '') { alert("아이디를 입력하세요."); $("#userid").focus();  return false; }
	if($("#username").val() == '') { alert("이름을 입력하세요."); $("#username").focus(); return false; }

	const Pass = $("#password").val();
	const Pass1 = $("#password1").val();
	if(Pass == '') { alert("암호를 입력하세요."); $("#password").focus(); return false; }
	if(Pass1 == '') { alert("암호를 입력하세요."); $("#password1").focus(); return false; }
	if(Pass != Pass1) 
		{ alert("입력된 비밀번호를 확인하세요"); $("#password1").focus(); return false; }
	
	// 암호유효성 검사
	//자바스크립트의 정규식(Regular Expression)
	//var num = Pass.search(/[0-9]/g); // 0-9까지의 숫자가 들어 있는지 검색. 검색이 안되면 -1을 리턴
 	//var eng = Pass.search(/[a-z]/ig); //i : 알파벳 대소문자 구분 없이... 
 	//var spe = Pass.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);	//특수문자가 포함되어 있는가 검색
	//if(Pass.length < 8 || Pass.length > 20) { alert("암호는 8자리 ~ 20자리 이내로 입력해주세요."); return false; }
	//else if(Pass.search(/\s/) != -1){ alert("암호는 공백 없이 입력해주세요."); return false; }
	//else if(num < 0 || eng < 0 || spe < 0 ){ alert("암호는 영문,숫자,특수문자를 혼합하여 입력해주세요."); return false; }

	// $('#RegistryForm').attr('action','/user/signup').submit();
	
	// 새로 데이터 받는 방법 ( 프론트 단에서 어떻게 주고 받는지 ) 
	const gender = document.querySelector('input[name = gender]:checked');
	const hobby = document.querySelectorAll('input[name = hobbies]:checked');
	let hobbyArray = [];
	for(let i = 0; i< hobby.length; i++)
		hobbyArray.push(hobby[i].value);
	
	const job = document.querySelector('select[name = job] option:checked');
	const description = document.querySelector('textarea[name = description]');
	
	// 자바스크립트 객체 만들기
	const data = {
		userid: userid.value,
		username: username.value,
		password: password.value,
		gender: gender.value,
		hobby: hobbyArray.toString(),
		job: job.value,
		description: description.value
			
	}
	await fetch('', { // ('경로', 보낼 데이터)
		headers: {
			"content-type": "application/json"
		},
		method: 'POST',
		body: JSON.stringify(data)
	}).then((response) => response.json())
	.then((data) => {
		if(data.status === 'good'){
			alert(decodeURIComponent(data.username) + "님, 회원 등록 완료");
			document.location.href="/board/list?page=1";
		} else {
			alert("서버 장애로 회원 가입에 실패 했습니다.");
		}
	});
	
}

function selectAll(checkElement){
	//<input type="checkbox" id="all" name="all" onclick="selectAll(this)">가 
	//ckeck 여부를 확인할 수 있는 값(ckecked==true 또는 ckecked==false)을 보내고
	//이 값이 checkElement에 들어감.
	//checkElement 값을 checkbox.checked에 넣어준다.
	/*
	const checkboxes = document.getElementsByName("hobbies");
	checkboxes.forEach((checkbox) => {
		checkbox.checked = checkElement.checked; 
	});
	*/

	if(document.getElementById('all').checked == true)
		for(var i=0; i<document.getElementsByName('hobbies').length; i++)
			document.getElementsByName('hobbies')[i].checked = true;
	if(document.getElementById('all').checked == false)
		for(var i=0; i<document.getElementsByName('hobbies').length; i++)
			document.getElementsByName('hobbies')[i].checked = false;
	
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
	<img id="topBanner" src="/resources/images/logo.jpg">	
</div>

<div class="main">
	<h1>회원 등록</h1><br>
	
	<div class="WriteForm">
		<form id="RegistryForm" name="RegistryForm" method="POST">
			<input type="text" class="input_field" id="userid" name="userid" placeholder="여기에 아이디를 입력해 주세요." onchange="idCheck()"><br>
			<span id="idCheckNotice"></span>			
			<input type="text" class="input_field" id="username" name="username" placeholder="여기에 이름을 입력해 주세요.">
			<input type="password" class="input_field" id="password" name="password" placeholder="여기에 패스워드를 입력해 주세요.">
			<input type="password" class="input_field" id="password1" name="password1" placeholder="여기에 패스워드를 입력해 주세요.">
			<div style="width:90%; text-align:left; position:relative; left: 35px; border-bottom:2px solid #adadad; margin:10px; padding: 10px;">
				성별 : 
				<label><input type="radio" name="gender" value="남성">남성</label>
				<label><input type="radio" name="gender" value="여성">여성</label><br>
				취미 : 
				<label><input type="checkbox" id="all" name="all" onclick="selectAll(this)">전체선택</label>
				<label><input type="checkbox" name="hobbies" value="음악감상">음악감상</label>
				<label><input type="checkbox" name="hobbies" value="영화보기">영화보기</label>
				<label><input type="checkbox" name="hobbies" value="스포츠">스포츠</label><br>
				직업 : 
				<select name="job">
					<option disabled selected>-- 아래의 내용 중에서 선택 --</option>
					<option value="회사원">회사원</option>
					<option value="공무원">공무원</option>
					<option value="자영업">자영업</option>
				</select>
				<br>
			</div>
			<br>
			<textarea class="input_content" id="content" cols="100" rows="500" name="description" placeholder="자기소개를 입력해 주세요.">
			</textarea><br>
			<input type="button" class="btn_write" value="여기를 클릭하세요!!!" onclick="register()">
			
		</form>
	
	</div>

</div>
<br><br>
</body>
</html>