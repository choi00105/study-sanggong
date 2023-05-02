<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 </title>
<script>

	window.onload = () => {
		document.cookie = "userid="+ encodeURIComponent("xxx") +"; path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT";
		document.cookie = "username="+ encodeURIComponent("최대훈") +"; path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT";
	}
</script>
</head>
<body>
<%
	String userid = (String)session.getAttribute("userid");
	if(!userid.equals("xsx")) response.sendRedirect("/user/signup");
%>
<script>
	const getCookie = (name) => {
		const cookies = document.cookie.split('; ').map((el) => el.split('='));
		let getItem = []; // Array.prototype -> 배열 생성
		console.log(document.cookie);
		for(let i=0; i<cookies.length; i++){
			if(cookies[i][0] === name) {
				getItem.push(cookies[i][1]);
				break;
			}
		}
		console.log(cookies);
		console.log(getItem, getItem.length);
		if(getItem.length > 0) return decodeURIComponent(getItem[0]);
	}
	
	const getCookieName = () => {
		const userid = getCookie("userid");
		const username = getCookie("username");
		
		document.querySelector("#userid").innerHTML = userid;
		document.querySelector("#username").innerHTML = username;
	}
	
	const deleteCookie = () => {
		document.cookie = 'userid=xxx; path=/; max-age=0';
		document.cookie = 'username=' + encodeURIComponent("최대훈") + '; path=/; max-age=0';
		
		document.querySelector("#userid").innerHTML = '';
		document.querySelector("#username").innerHTML = '';
	}
</script>
<h1>세션 userid = ${userid}</h1>
<h1>세션 username= ${username}</h1>

<input type="button" value="쿠키 가져오기" onclick="getCookieName()"><br>
<div style="font-size: 50px;">쿠키 userid = <span id="userid"></span></div>
<div style="font-size: 50px;">쿠키 username = <span id="username"></span></div>

<input type="button" value="쿠키 삭제" onclick="deleteCookie()"><br>
</body>
</html>