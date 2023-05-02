<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	const preViewFiles = () => {
		const preview = document.querySelector('#preview');
		const files = document.querySelector('input[type=file]').files;
		
		const readAndPreview = (file) => {
			if(/\.(jpe?g|png|gif)$/i.test(file.name)) {
				const reader = new FileReader();
				reader.addEventListener('load', function(){
					let image = new Image();
					image.height = 100;
					image.title = file.name;
					image.src = this.result;
					preview.appendChild(image);
				});
				reader.readAsDataURL(file); // <- 바이너리 파일 읽는 법
				
			}
		}
		if(files)
			// 유사 배열 : 원래 정식 배열은 아닌데 배열처럼 보이는 것
			// 정식배열은 배열 객체에서 제공하는 메소드를 사용 할 수 있는 반면
			// 유사배열은 X. 유사 배열도 배열 객체에서 지원하는 메소드를 사용 할 수 있게끔
			// 해주는 방법이 있음 [] --> Array.prototype
			[].forEach.call(files, readAndPreview); 
	
	}
</script>

	<input type="file" id="browse" onchange="preViewFiles()" multiple><br>
	<div id="preview">
		<img src="" style="width: 400px; height: auto;">
	</div>
	
</body>
</html>