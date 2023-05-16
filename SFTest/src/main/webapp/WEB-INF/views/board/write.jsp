<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시물 등록</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
window.onload = () => {
	const fileZone = document.querySelector('#fileZone');
	const inputFile = document.querySelector('#inputFile');
	
	// fileZone을 클릭하면 발생하는 이벤트
	fileZone.addEventListener('click', (e) => {
		inputFile.click(e);
		
	});
	
	// 파일 탐색창을 열어서 선택한 파일 정보를 files에 할당
	inputFile.addEventListener('change', (e) => {
		const files = e.target.files;
		fileCheck(files);
	});
	/* 마우스 이벤트 종류
	dragstart : 사용자가 객체를 드래그 할려고 시작 할 때발생하는 이벤트
	drag : 대상 객체를 드래그 하면서 마우스를 움직일 때 발생함
	* dragenter : 마우스가 대상 객체의 위로 처음 진입할 때 발생
	* dragover : 드래그 하면서 마우스가 대상 객체의 영역 위에 자리잡고 있을 떄 발생
	* drop : 드래그가 끝나서 드래그 하던 객체를 놓는 장소에 위치한 객체에서 발생함
	dragleave : 드래그가 끝나서 마우스가 대상 객체의 위에서 벗어날 때 발생함
	dragend : 대상 객체를 드래그 하다가 마우스 버튼을 놓는 순간 발생함
	*/
	
	// fileZone으로 dragenter 이벤트 발생시 처리하는 이벤트 핸들러
	fileZone.addEventListener('dragenter', (e) => {
		//e.stopPropagation() -> 웹브라우저의 고유 동작을 중단
		//e.preventDefault() -> 상위 엘레멘트드로의 이벤트 전파를 중단
		// 두개는 주로 세트로 같이 쓰인다
		
		e.stopPropagation();
		e.preventDefault();
		fileZone.style.border = '2px solid #0B85A1';
	});
	
	//fileZone으로 dragover 이벤트 발생시 처리하는 이벤트 핸들러
	fileZone.addEventListener('dragover', (e) => {		
		e.stopPropagation();
		e.preventDefault();
	});
	
	//fileZone으로 drop 이벤트 발생시 처리하는 이벤트 핸들러
	fileZone.addEventListener('drop', (e) => {		
		e.stopPropagation();
		e.preventDefault();
		
		const files = e.dataTransfer.files;
		fileCheck(files);
	});
	
}

	var uploadCountLimit = 5; // 업로드 가능한 파일 갯수
	var fileCount = 0; // 파일 현재 필드 숫자 totalCount랑 비교값
	var fileNum = 0; // 파일 고유넘버
	var content_files = new Array(); // 첨부파일 배열
	var rowCount=0;
	
	const fileCheck = (files) => {
	
		// prototype : 자바스크립트에서는 사용자가 새로운 객체 또는 함수를 만들 때 자동으로 prototype이 만들어 지고 
		// 여기에 기본적으로Object 객체를 상속(참조)하게끔 함.
		// 기존 내장 객체(Array, FormData ...)도 본인 객체 외에 prototype을 가지고 있음.
		//배열로 변환
		
		// var filesArr = Array.prototype.slice.call(files); // 유사 배열 형태를 띄고있는 객체를 배열로 전환
		
		// shallow copy : 얇은 복사, Deep Copy : 깊은 복사
		let filesArr = Object.values(files); // ECMAScript 2017년 버전부터 제공 심플함!
		
	    // 파일 개수 확인 및 제한
	    if (fileCount + filesArr.length > uploadCountLimit) {
	      	alert('파일은 최대 '+uploadCountLimit+'개까지 업로드 할 수 있습니다.');
	      	return;
	    } else {
	    	 fileCount = fileCount + filesArr.length;
	    }
	
		filesArr.forEach((file) => {
			// FileReader 객체의 역할 : 웹 애플리케이션이 비동기적으로 웹브라우저에서 파일을 읽을 때 사용하며,
			// <input type="file"> 태그를 이용하여 사용자가 선택한 파일들의 결과로 반환된 FileList 객체나
			// Drag & Drop 이벤트로 반환된 DataTransfer 객체로 부터 대이터를 얻음.
		      var reader = new FileReader();
			  
			  // 파일 읽기
		      reader.readAsDataURL(file);
			
			  //reader.readAsDataURL(file); 실행으로 파일 읽기가 성공적으로 수행 되고 난 후
			  //reader.onload 이벤트 핸들러를 통해 reader.onload 이벤트 핸들러 내의 콜백 함수가 
			  //비동기적으로 실행됨.
		      reader.onload = (e) => {
		        content_files.push(file);
				
		        if(file.size > 1073741824) { alert('파일 사이즈는 1GB를 초과할수 없습니다.'); return; }
			
		    	rowCount++;
		        var row="odd";
		        if(rowCount %2 ==0) row ="even";
		        
		        let statusbar = document.createElement('div');

		        statusbar.setAttribute('class', 'statusbar ' + row); // <div class='statusvar odd'></div>
		        statusbar.setAttribute('id', 'file'+ fileNum); // <div class='statusvar odd' id='file0'></div>
		        
		        // statusbar 내의 파일명
		        let filename = "<div class='filename'>" + file.name + "</div>"
		        
		        // statusbar 내의 파일 사이즈	       
		        let sizeStr="";
		        let sizeKB = file.size/1024;
		        if(parseInt(sizeKB) > 1024){
		        	var sizeMB = sizeKB/1024;
		            sizeStr = sizeMB.toFixed(2)+" MB";
		        }else sizeStr = sizeKB.toFixed(2)+" KB";	
		        let size = "<div class='filesize'>" + sizeStr + "</div>";
		        
		        // statusbar의 삭제버튼
		        let btn_delete = "<div class='btn_delete'><input type='button' value='삭제' onclick=fileDelete('file" + fileNum + "')>삭제</div>>";
		       
		        statusbar.innerHTML = filename + size + btn_delete;
		        
		        
		        fileUploadList.appendChild(statusbar);
				
		        fileNum ++;
	       
		        console.log(file);
		      };
		      
	    });
		
		inputFile.value = '';
			
	
	}

	const fileDelete = (fileNum) => {
	    var no = fileNum.replace(/[^0-9]/g, "");
	    content_files[no].is_delete = true; // 삭제 됐다고 표시만
	    
		document.querySelector('#'+fileNum).remove90;
		fileCount --;
	}  


	const registerForm = async() => {
	
		if(writer.value == '') { alert("이름을 입력하세요!!!"); writer.focus(); return false;  }
		if(title.value == '') { alert("제목을 입력하세요!!!");  title.focus(); return false;  }
		if(content.value == '') { alert("내용을 입력하세요!!!");  content.focus(); return false;  }
		
		let uploadURL = '';
		
		if(content_files.length != 0) // 첨부된 파일이 있을 경우
			uploadURL = '/board/fileUpload?kind=I';
		else  // 첨부된 파일이 없을 경우
			uploadURL = '/board/write';
			
		let wForm = document.getElementById('WriteForm');
	 	let formData = new FormData(wForm);
		for (let i = 0; i < content_files.length; i++) {
				if(!content_files[i].is_delete) { 
					formData.append("SendToFileList", content_files[i]);
				}
		}
	/*
		$.ajax({
	        url: uploadURL,
	        type: "POST",
	        contentType:false,
	        processData: false,
	        cache: false,
	        enctype: "multipart/form-data",
	        data: formData,
	        xhr:function(){
	        	var xhr = $.ajaxSettings.xhr();
	        	xhr.upload.onprogress = function(e){
	        		var per = e.loaded * 100/e.total;
	        		setProgress(per);
	        	};
	        	return xhr;        	
	        },
	        success: function(data){
	        	alert("게시물이 등록되었습니다.\n게시물 목록 화면으로 이동합니다.");
				document.location.href='/board/list?page=1';	
	        },
	        error: function (error) {
	       	    	alert("서버오류로 게시물 등록이 실패하였습니다. 잠시 후 다시 시도해주시기 바랍니다.");
	       	     return false;
	       	}   
	       
	    }); 
	*/
		await fetch(uploadURL, {
			method: 'POST',
			body: formData
		}).then((response) => response.json())
		.then((data) => {
			if(data.message == 'good'){
				alert("게시물이 등록 되었습니다.");
				document.location.href='/board/list?page=1';
			}
		}).catch((error) => {
			alert("시스템 장애로 게시물 등록이 실패 했습니다.");
			console.log((error) => console.log(error));
		});
	}

</script>

<style>
body { font-family: "나눔고딕", "맑은고딕" }
h1 { font-family: "HY견고딕" }
a:link { color: black; }
a:visited { color: black; }
a:hover { color: red; }
a:active { color: red; }
#hypertext { text-decoration-line: none; cursor: hand; }
#topBanner {
       margin-top:10px;
       margin-bottom:10px;
       max-width: 500px;
       height: auto;
       display: block; margin: 0 auto;
}

.main {
	text-align: center;
}

.WriteDiv {
  width:50%;
  height:auto;
  padding: 20px, 20px;
  background-color:#FFFFFF;
  margin: auto;
  text-align: center;
  border:4px solid gray;
  border-radius: 30px;
}

.writer, .title {
  width: 90%;
  border:none;
  border-bottom: 2px solid #adadad;
  margin: 10px;
  padding: 5px 5px;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.content{
  width: 70%;
  height: 300px;
  padding: 10px;
  box-sizing: border-box;
  border: solid #adadad;
  font-size: 16px;
  resize: both;
}

.btn_write  {
  position:relative;
  left:20%;
  transform: translateX(-50%);
  margin-top: 20px;
  margin-bottom: 10px;
  width:40%;
  height:40px;
  background: red;
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}

.btn_cancel{
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

.filename{
  display:inline-block;
  vertical-align:top;
  width: 50%;
}

.filesize{
  display:inline-block;
  vertical-align:top;
  color:#30693D;
  width:30%;
  margin-left:10px;
  margin-right:5px;
}

.fileuploadForm {
 margin: 5px;
 padding: 5px 5px 2px 30px;
 text-align: left;
 width:90%;
 
}

.fileZone {
  border: solid #adadad;
  background-color: #a0a0a0;
  width: 97%;
  height:80px;
  color: white;
  text-align: center;
  vertical-align: middle;
  padding: 5px;
  font-size:120%;
  display: block;
}

.fileUpLoadList {
	border: solid #adadad;
	width: 97%;
	height: auto;
	padding: 5px;
	font-size: 120%;
	
}

.btn_delete{
	display: inline-block;
	width: 15%;
	cursor:pointer;
	vertical-align:top
}

.statusbar{
  border-bottom:1px solid #92AAB0;;
  min-height:25px;
  width:96%;
  padding:10px 10px 0px 10px;
  vertical-align:top;
}
.statusbar:nth-child(odd){
  background:#EBEFF0;
}
</style>

</head>   
<body>

<%

	String userid = (String)session.getAttribute("userid");
	String username = (String)session.getAttribute("username");
	String role = (String)session.getAttribute("role");
	if(userid == null) response.sendRedirect("/");

%>

  	<div>
    	<img id="topBanner" src ="/resources/images/logo.jpg" title="서울기술교육센터" >
  	</div>
  	<div class="main">
		<div class="WriteDiv">
			<h1>게시물 등록</h1>
			<br>
			
			<form id="WriteForm" method="POST" >
				<input type="text" class="writer" value="작성자 : <%=username %> 님" disabled>
				<input type="text" class="title" id="title" name="title"  placeholder="여기에 제목을 입력하세요">
				<input type="hidden" id="writer" name="writer" value="<%=username %>">
				<input type="hidden" class="userid" id="userid" name="userid" value="<%=userid %>">
				<textarea class="content" id="content" cols="100" rows="500" name="content" placeholder="여기에 내용을 입력하세요"></textarea>
			
				<div class="fileuploadForm">
					<input type="file" id="inputFile" name="uploadFile" style="display:none;" multiple />
					<div id="fileZone" class="fileZone">파일 첨부를 하기 위해서는 클릭하거나 여기로 파일을 드래그 하세요.<br>첨부파일은 최대 5개까지 등록이 가능합니다.</div>
			  		<div id="fileUploadList" class="fileUploadList"></div>
				</div>
				<input type="button" class="btn_write" value="등록" onclick="registerForm()" />
				<input type="button" class="btn_cancel" value="취소" onclick="history.back()" />
			</form>		
		</div>
	</div>
<br><br>
</body>
</html>