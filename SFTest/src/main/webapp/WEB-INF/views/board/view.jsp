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

	const deleteBoard = () => {
		if(confirm("정말로 삭제하시겠습니까?"))
			document.location.href='/board/delete?seqno=${view.seqno}';
	}
	
	<!-- 댓글 처리 -->
	function replyRegister() { //form 데이터 전송 --> 반드시 serialize()를 해야 한다.
		
		if($("#replycontent").val() == "") {alert("댓글을 입력하세요."); $("#replycontent").focus(); return false;}
		
		var queryString = $("form[name=replyForm]").serialize();
		//serialize --> 데이터를 스트림으로 보내기 위한 타입으로 바꾸는 함수.
		$.ajax({
			url : "reply?option=I",
			type : "post",
			datatype : "json",
			data : queryString,
			success : replyList,
			error : function(data) {
						alert("서버오류 문제로 댓글 등록이 실패 했습니다. 잠시 후 다시 시도해주시기 바랍니다.");
	              	    return false;
			}
		}); //End of ajax
		$("#replycontent").val("");	
	}

	function replyList(data){
		
		var session_userid = '${userid}';
		const jsonInfo = data;
		
		var result = "";
		for(const i in jsonInfo){
			
			result += "<div id='replyListView'>";
			result += "<div id = '" + jsonInfo[i].replyseqno + "' style='font-size: 0.8em'>";
			//<div id = '120' style='font-size: 0.8em'>...</div>
			result += "작성자 : " + jsonInfo[i].replywriter;
							
			if(jsonInfo[i].userid == session_userid) {
				result += "[<a href=" + "'javascript:replyModify(" + jsonInfo[i].replyseqno + ")' style='cursor:pointer;'>수정</a> | " ;
				result += "<a href=" + "'javascript:replyDelete(" + jsonInfo[i].replyseqno + ")' style='cursor:pointer;'>삭제</a>]" ;
			}
			
			result += "&nbsp;&nbsp;" + jsonInfo[i].replyregdate
			result += "<div style='width:90%; height: auto; border-top: 1px solid gray; overflow: auto;'>";
			result += "<pre class='" + jsonInfo[i].replyseqno + "'>" + jsonInfo[i].replycontent + "</pre></div>";
			//<pre class='2'>안녕</pre></div>
			result += "</div>";
			result += "</div><br>";
		}
		$("#replyListView").remove();
		$("#replyList").html(result); 
	}

	function startupPage(){
		
		var queryString = { "seqno": "${view.seqno}" };
		$.ajax({
			url : "reply?option=L",
			type : "post",
			datatype : "json",
			data : queryString,
			success : replyList,
			error : function(data) {
							alert("서버 오류로 댓글 불러 오기가 실패했습니다.");
	              	    	return false;
					}
		}); //End od ajax
	}

	function replyDelete(replyseqno) { 
		var rseqno = replyseqno
		if(confirm("정말로 삭제하시겠습니까?") == true) {
			var queryString = { "replyseqno": replyseqno, "seqno":${view.seqno} };
			$.ajax({
				url : "reply?option=D",
				type : "post",
				data : queryString,
				dataType : "json",
				success : replyList,
				error : function(data) {
							alert("서버오류 문제로 댓글 등록이 실패 했습니다. 잠시 후 다시 시도해주시기 바랍니다.");
		            		return false;
				}
			}); //End od ajax
		}
	}

	function replyModify(replyseqno) {

		var replyContent = $("." + replyseqno).html();
		
		var strReplyList = "<form id='formModify' name='formModify' method='POST'>"
						+ "작성자 : ${session_userid}&nbsp;"
						+ "<input type='button' id='btn_replyModify' value='수정'>"
						+ "<input type='button' id='btn_replyModifyCancel' value='취소'>"
						+ "<input type='hidden' name='replyseqno' value='" + replyseqno + "'>"
						+ "<input type='hidden' name='seqno' value='${view.seqno}'>"
						+ "<input type='hidden' id='writer' name='replywriter' value='${session_username}'>"
						+ "<input type='hidden' id='uerid' name='userid' value='${session_userid}'><br>"
						+ "<textarea id='replycontent' name='replycontent' cols='80' rows='5' maxlength='150' placeholder='글자수:150자 이내'>" + replyContent + "</textarea><br>"
						+ "</form>";
		$('#' + replyseqno).after(strReplyList);				
		$('#' + replyseqno).remove();

		$("#btn_replyModify").on("click", function(){
			var queryString = $("form[name=formModify]").serialize();
			$.ajax({
				url : "reply?option=U",
				type : "post",
				dataType : "json",
				data : queryString,
				success : replyList,
				error : function(data) {
								alert("서버오류 문제로 댓글 등록이 실패 했습니다. 잠시 후 다시 시도해주시기 바랍니다.");
		              	    	return false;
				}
			}); //End of $.ajax
		 }); //End of $("#btn_replyModify")
		
		 $("#btn_replyModifyCancel").on("click", function(){
			 if(confirm("정말로 취소하시겠습니까?") == true  ) { $("#replyListView").remove(); startupPage(); }
		 });	 
		
	}
		
	function replyCancel() { 
			if(confirm("정말로 취소 하시겠습니까?") == true) { $("#replyContent").val(""); $("#replyContent").focus(); }
	}

	
</script>
<style>
.bottom_menu {
	margin-top: 20px;
}

.bottom_menu > a:link, .bottom_menu > a:visited {
	background-color: #FFA500;
	color: maroon;
	padding: 15px 25px;
	text-align: center;
	display: inline-block;
	text-decoration: none;
}

.bottom_menu > a:hover, .bottom_menu > a:active {
	background-color: #1E90FF;
	text-decoration: none;
}

</style>
</head>
<body onload="startupPage()">

<%

	String userid = (String)session.getAttribute("userid");
	if(userid == null) response.sendRedirect("/user/login");

%>

<div>
	<img id="topBanner" src="/resources/images/logo.jpg" title="서울기술교육센터">	
</div>

<div class="main">
	<h1>게시물 내용 보기, ${userid}, ${view.userid}</h1>
	<br>
	<div class="boardView">
		<div class="field">이름 : ${view.writer}</div>
		<div class="field">제목 : ${view.title}</div>
		<div class="field">날짜 : ${view.regdate}</div>
		<div class="content"><pre>${view.content}</pre></div>
		<c:if test="${view.org_filename != NULL}">
			<div style="text-align: center">파일명 : 
			  <a href="/board/filedownload?seqno=${view.seqno}">${view.org_filename}</a>(${view.filesize} byte)</div>
		</c:if>
		<c:if test="${view.org_filename == NULL}">
			<div style="text-align: center">업로드 된 파일이 없습니다.</div>
		</c:if>	
		<br><br>
	</div>
	<br>
	<div class="bottom_menu">
		<c:if test="${pre_seqno != 0}">
			&nbsp;&nbsp;<a href="/board/view?seqno=${pre_seqno}&page=${page}&keyword=${keyword}">이전글▼</a>&nbsp;&nbsp;
		</c:if>
		<a href="/board/list?page=${page}&keyword=${keyword}">목록보기</a>&nbsp;&nbsp;
		<c:if test="${next_seqno != 0}">
			<a href="/board/view?seqno=${next_seqno}&page=${page}&keyword=${keyword}">다음글▲</a>&nbsp;&nbsp;
		</c:if>
		<a href="/board/write">글 작성</a>&nbsp;&nbsp;
		<c:if test="${userid == view.userid}">
			<a href="/board/modify?seqno=${view.seqno}&page=${page}&keyword=${keyword}">글 수정</a>&nbsp;&nbsp;
			<a href="javascript:deleteBoard()">글 삭제</a>
		</c:if>
	</div>	
	<br>
	<div class="replyDiv" style="width:60%; height:300px; margin:auto; text-align:left;">
		<p id="replyNotice">댓글을 작성해 주세요</p>
		<form id="replyForm" name="replyForm" method="POST"> 
			작성자 : <input type="text" id="replywriter" name="replywriter" value=${username} readonly><br>
	    	<textarea id='replycontent' name='replycontent' cols='80' rows='5' maxlength='150' placeholder='글자수:150자 이내'></textarea><br>
	    	<input type="hidden" id="seqno" name="seqno" value="${view.seqno}">
	    	<input type="hidden" id="userid" name="userid" value="${userid}">
		</form>
		<input type="button" id="btn_reply" value="댓글등록" onclick="replyRegister()">
		<hr>
		
		<div id="replyList" style="width:100%; height:600px; overflow:auto;">
			<div id="replyListView"></div> 
		</div><!-- replyList End  -->		
	</div>
	
</div>
<br><br>
</body>
</html>