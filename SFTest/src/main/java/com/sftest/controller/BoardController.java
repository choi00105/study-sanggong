package com.sftest.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sftest.dto.BoardVO;
import com.sftest.dto.UserVO;
import com.sftest.mapper.SFTestMapper;

@Controller
public class BoardController {
	
	@Autowired //비밀번호 암호화 의존성 주입
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired // mapper 인터페이스 의존성 주입
	SFTestMapper mapper;
	
	
	@PostMapping("/user/userinfo")
	public void postUserInfo(UserVO user, Model model) {
		System.out.println("==== post userinfo 됐을때 model = " + model +"\n user.getUserid = "+user.getUserid());
		model.addAttribute("user", user);
		
	}
	
	@GetMapping("/board/imgView")
	public void getImgView() throws Exception {
		
	}
	@GetMapping("/board/imgViews")
	public void getImgViews() throws Exception {
		
	}
	@GetMapping("/board/fileUpload")
	public void getFileUpload() throws Exception {
		
	}
	@PostMapping("/board/fileUpload")
	public void postFileUpload(@RequestParam("painter") String painter,
			@RequestParam("fileUpload")List<MultipartFile> multipartFile) throws Exception {
		
		String path = "c:\\Repository\\test\\"; 
		
		if(!multipartFile.isEmpty()) {
			File targetFile = null; 	
			String org_filename =null;
			long filesize=0L;
			for(MultipartFile mpr:multipartFile) {
				
				org_filename = mpr.getOriginalFilename();	
				// String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));	
				// String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;	
				filesize = mpr.getSize() ;
				
				// File file = new File("path 값\\+된거"); -> 파일 생성에 필요한 경로 및 파일 정보를 입력
				targetFile = new File(path + org_filename);
				mpr.transferTo(targetFile); // raw 데이터를 targetFile에서 가진 정보대로 변환
					
			}
			System.out.println("파일명 = " + org_filename);
			System.out.println("파일사이즈 = " + filesize);
		}
	}
	@GetMapping("/board/fileUpload2")
	public void getFileUpload2() throws Exception {
		
	}
	@ResponseBody
	@PostMapping("/board/fileUpload2")
	public String postFileUpload2(@RequestParam("painter") String painter,
			@RequestParam("fileUpload")List<MultipartFile> multipartFile) throws Exception {
		
		String path = "c:\\Repository\\test\\";
		String good = "\\good";
		System.out.println(path+"\n"+good);
		
		if(!multipartFile.isEmpty()) {
			File targetFile = null; 	
			String org_filename =null;
			long filesize=0L;
			for(MultipartFile mpr:multipartFile) {
				
				org_filename = mpr.getOriginalFilename();	
				// String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));	
				// String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;	
				filesize = mpr.getSize() ;
				
				// File file = new File("path 값\\+된거"); -> 파일 생성에 필요한 경로 및 파일 정보를 입력
				targetFile = new File(path + org_filename);
				mpr.transferTo(targetFile); // raw 데이터를 targetFile에서 가진 정보대로 변환
					
			}
		}
		return "good";
	}
	@GetMapping("/board/fileList")
	public void getFileList() throws Exception {
		
	}
	//파일 다운로드
	@GetMapping("/board/fileDownload")
	public void fileDownload(@RequestParam(name="file") String file, HttpServletResponse rs) throws Exception {
		
		String path = "c:\\Repository\\test\\";
		
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File(path+file));
		
		//헤드값을 Content-Disposition로 주게 되면 Response Body로 오는 값을 filename으로 다운받으라는 것임
		//예) Content-Disposition: attachment; filename="hello.jpg"
		rs.setContentType("application/octet-stream");
		rs.setContentLength(fileByte.length);
		rs.setHeader("Content-Disposition",  "attachment; filename=\""+URLEncoder.encode(file, "UTF-8")+"\";");
		rs.getOutputStream().write(fileByte);
		rs.getOutputStream().flush();
		rs.getOutputStream().close();
			
	}
	
	@GetMapping("/user/login")
	public void getlogin(HttpSession session) throws Exception {
		String password_before = "12345";
		String password_after = pwdEncoder.encode(password_before);
		
		System.out.println("password_before = " + password_before + ", password_after = " + password_after);
		
		if(pwdEncoder.matches(password_before, password_after))
			System.out.println("패스워드 맞음");
		else
			System.out.println("패스워드 틀림");
		
		UserVO user = new UserVO();
		
		session.setAttribute("userid", "user.getUserid()");
		session.setAttribute("username", "최대훈");
		// 초단위 유지 시간 설정
		
		session.setMaxInactiveInterval(3600*24*7); 
			
	}
	// 게시물 목록보기
	@GetMapping("/board/list")
	public void getLsit(Model model) throws Exception { 
		model.addAttribute("list", mapper.list());
		System.out.println("========/list에 get요청 했을 때 mapper.list() \n"+mapper.list()); 
		//com.sftest.dto.BoardVOr가 리스트에 db데이터 수만큼 들어있음
	}
	
	// 게시물 등록(화면 보기)
	@GetMapping("board/write")
	public void getWrite() {}
	
	// 게시물 등록
	@PostMapping("board/write")
	public String postWrite(BoardVO board) throws Exception {
		
		mapper.write(board);
		
		return "redirect:/board/list";
	}
	
	
	
	//게시물 내용 상세 보기 
	@GetMapping("/board/view")
	public void getView(@RequestParam("seqno") int seqno, Model model) throws Exception {
		
		BoardVO board = new BoardVO();
		board.setSeqno(seqno);		
		mapper.hitno(board);
		model.addAttribute("view", mapper.view(seqno));
		
	}
	// 게시물 수정 ( 화면 보기 )
	@GetMapping("/board/modify")
	public void getModify(@RequestParam("seqno") int seqno, Model model) throws Exception {
		model.addAttribute("view", mapper.view(seqno));
	}
	// 실제로 게시물 수정 POST
	@PostMapping("/board/modify")
	public String postModify(BoardVO board) throws Exception {
		mapper.modify(board);
		return "redirect:/board/view?seqno=" + board.getSeqno();
	}
	
	// 게시물 삭제
	@GetMapping("/board/delete")
	public String getDelete(@RequestParam("seqno") int seqno) throws Exception {
		
		mapper.delete(seqno);
		return "redirect:/board/list";
	}
	
}
		
		

