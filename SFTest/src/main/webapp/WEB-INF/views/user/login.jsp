<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<script>

	const cookieMaker = () => {
	
		document.cookie = 'userid=' + ${userid} + '; path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT';
		document.cookie = 'username=' + encodeURIComponent('${username}') + '; path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT';		
	
	}	
	
	const getCookie = (name) => {
	
		const cookies = document.cookie.split(`; `).map((el) => el.split('='));
		  let getItem = [];
	
		  for (let i = 0; i < cookies.length; i++) {
		    
		    if (cookies[i][0] === name) {
		      getItem.push(cookies[i][1]);
		      break;
		    }
		  }
	
		  if (getItem.length > 0) {
			  console.log(getItem[0]);
		    return decodeURIComponent(getItem[0]);
		  }
		
		
	}
	
	const getCookieName = () => {
		
		const userid = getCookie("userid");
		const username = getCookie("username");
		
		document.querySelector("#userid").innerHTML = userid;
		document.querySelector("#username").innerHTML = username;
		
	}
	
	const deleteCookie = () => {
		
		document.cookie = 'userid=xavier; path=/; max-age=0';
		document.cookie = 'username=' + encodeURIComponent("김현우") + '; path=/; max-age=0';
		
		document.querySelector("#userid").innerHTML = '';
		document.querySelector("#username").innerHTML = '';
		
	}
	
	const loginCheck = () => {
		
		if(document.loginForm.userid.value == ''){
			alert("아이디를 입력하세요");
			return false;
		}
		if(document.loginForm.password.value == ''){
			alert("패스워드를 입력하세요");
			return false;
		}
		document.loginForm.action = '/user/login';
		document.loginForm.submit();
	}
	
	const press = () =>{
		
		if(event.keyCode == 13){loginCheck();}
		
	}

</script>

<style>
body { font-family: "나눔고딕", "맑은고딕" }
h1 { font-family: "HY견고딕" }
a:link { color: black; }
a:visited { color: black; }
a:hover { color: blue; }
a:active { color: red; }
#hypertext { text-decoration-line: none; cursor: hand; }
#topBanner {
       margin-top:10px;
       margin-bottom:10px;
       max-width: 500px;
       height: auto;
       display: block; margin: 0 auto;
}  
.login {
  width:900px;
  height:auto;
  padding: 20px, 20px;
  background-color:#FFFFFF;
  text-align:center;
  border:5px solid gray;
  border-radius: 30px;
}   
.userid, .userpasswd {
  width: 80%;
  border: none;
  border-bottom: 2px solid #adadad;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
  margin-top: 20px;
}
.bottomText {
  text-align: center;
  font-size: 20px;
}
.login_btn  {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 40px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
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

<div class="main" align="center">

  <div>
    <img id="topBanner" src ="/resources/images/logo.jpg" title="서울기술교육센터" >
  </div>
  
	<div class="login">
		<h1>로그인</h1>
		<form name="loginForm" id="loginForm" class="loginForm" method="post"> 

			<input type="text" name="userid" id="userid" class="userid" placeholder="아이디를 입력하세요.">
         	<input type="password" id="password" name="password" class="userpasswd" onkeydown="press(this.form)" placeholder="비밀번호를 입력하세요.">
         	<br>
         	<c:if test="${message == 'ID_NOT_FOUND' }">
         		<p style="color:red;text-align:center;">존재하지 않는 아이디입니다.</p> 
         	</c:if>
         	<c:if test="${message == 'PASSWORD_NOT_FOUND' }">
         		<p style="color:red;text-align:center;">잘못된 패스워드입니다.</p> 
         	</c:if>
         	<br><br>
     		<input type="button" id="btn_login" class="login_btn" value="로그인" onclick="loginCheck()"> 
		</form>
        <div class="bottomText">사용자가 아니면 ▶<a href="/user/signup">여기</a>를 눌러 등록을 해주세요.<br><br></div>
	</div>
 
</div>




</body>
</html>