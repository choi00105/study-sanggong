function register(){
	const pwCheckNotice = document.querySelector('#pwCheckNotice');
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
	var num = Pass.search(/[0-9]/g); // 0-9까지의 숫자가 들어 있는지 검색. 검색이 안되면 -1을 리턴
 	var eng = Pass.search(/[a-z]/ig); //i : 알파벳 대소문자 구분 없이... 
 	var spe = Pass.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);	//특수문자가 포함되어 있는가 검색
	if(Pass.length < 8 || Pass.length > 20) { alert("암호는 8자리 ~ 20자리 이내로 입력해주세요."); return false; }
	else if(Pass.search(/\s/) != -1){ alert("암호는 공백 없이 입력해주세요."); return false; }
	else if(num < 0 || eng < 0 || spe < 0 ) alert("암호는 영문,숫자,특수문자를 혼합하여 입력해주세요."); return false; 
	//else pwCheckNotice.classList.add("hide");

	$('#RegistryForm').attr('action','/user/signup').submit();
}