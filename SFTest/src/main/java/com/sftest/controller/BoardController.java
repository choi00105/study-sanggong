package com.SFTest.controller;

import java.io.File;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.SFTest.dto.BoardVO;
import com.SFTest.dto.FileVO;
import com.SFTest.dto.ReplyVO;
import com.SFTest.dto.UserVO;
import com.SFTest.mapper.SFTestMapper;
import com.SFTest.service.BoardService;
import com.SFTest.util.Page;
import com.SFTest.dto.LikeVO;

@Controller
public class BoardController {
	
	@Autowired //비밀번호 암호화 의존성 주입
	private BCryptPasswordEncoder pwdEncoder; 
	
	@Autowired //mapper 인터페이스 의존성 주입
	SFTestMapper mapper;
	
	@Autowired
	BoardService service; //의존성 주입
	
	// 홈페이지 가게
	@GetMapping("/")
	public String getHome() {
		return "home";
	}
	//게시물 목록 보기
	@GetMapping("/board/list")
	public void getList(@RequestParam("page") int pageNum, @RequestParam(name="keyword",defaultValue="",required=false) String keyword, Model model) throws Exception{
		//model.addAttribute("list",mapper.list());
		System.out.println("===GET /board/list");
		int postNum = 10; //한 화면에 보여지는 게시물 행의 갯수
		int pageListCount = 10; //화면 하단에 보여지는 페이지리스트 내의 페이지 갯수
		int startPoint = (pageNum-1)*postNum;
		
		Page page = new Page();
		
		model.addAttribute("list",service.list(startPoint,postNum,keyword));
		model.addAttribute("page", pageNum);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageList", page.getPageList(pageNum, postNum, pageListCount, service.getTotalCount(keyword), keyword));
	}
	
	//게시물 등록(화면 보기)
	@GetMapping("/board/write")
	public void getWrite() {}
	
	
	
	//첨부 파일 없는 게시물 등록
	@ResponseBody
	@PostMapping("/board/write")
	public String PostWrite(BoardVO board) throws Exception{
		System.out.println("===GET /board/write");
		//int seqno = service.getSeqnoWithNextval();
		//board.setSeqno(seqno);
		service.write(board);		
		return "{\"message\":\"good\"}";
	}
		
	//파일 업로드
	@ResponseBody
	@PostMapping("/board/fileUpload")
	public String postFileUpload(BoardVO board,@RequestParam("SendToFileList") List<MultipartFile> multipartFile, 
			@RequestParam("kind") String kind,@RequestParam(name="deleteFileList", required=false) int[] deleteFileList) throws Exception{

		String path = "c:\\Repository\\file\\"; 
		int seqno =0;
		
		if(kind.equals("U")) { // 게시물 수정 시 게시물 수정
			seqno = board.getSeqno();
			service.modify(board);
			
			if(deleteFileList != null) {
				
				for(int i=0; i<deleteFileList.length; i++) {

					//파일 삭제
					FileVO fileInfo = new FileVO();
					fileInfo = service.fileInfo(deleteFileList[i]);
					//File file = new File(path + fileInfo.getStored_filename());
					//file.delete();
					
					//파일 테이블에서 파일 정보 삭제
					// 게시물 수정에서 삭제할 파일 목록이 전송되면 이 값으 받아서 tbl_file내에 있는 정보를 하나씩 삭제하는 deleteFileList
					Map<String, Object> data = new HashMap<>();
					data.put("kind", "F");
					data.put("fileseqno", deleteFileList[i]);
					service.deleteFileList(deleteFileList[i]); // deleteFileList는 Map타입의 인자값을 받도록 설정
					
				}
			}	
		}
		
		if(!multipartFile.isEmpty()) { // 파일 등록 및 수정 시 파일 업로드
			File targetFile = null; 
			Map<String,Object> fileInfo = null;		
			
			for(MultipartFile mpr:multipartFile) {
				
				String org_filename = mpr.getOriginalFilename();	
				String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));	
				String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;	
				long filesize = mpr.getSize() ;
				
				targetFile = new File(path + stored_filename);
				mpr.transferTo(targetFile);
				
				fileInfo = new HashMap<>();
				fileInfo.put("org_filename",org_filename);
				fileInfo.put("stored_filename", stored_filename);
				fileInfo.put("filesize", filesize);
				fileInfo.put("seqno", seqno);
				fileInfo.put("userid", board.getUserid());
				fileInfo.put("kind", kind);
				service.fileInfoRegistry(fileInfo);
	
			}
		}
		if(kind.equals("I")) { // 게시물 등록시 파일 업로드
			service.write(board);
		}
		return "{\"message\":\"good\"}";
	}	
	//게시물 내용 상세 보기 
	@GetMapping("/board/view")
	public void getView(@RequestParam("seqno") int seqno, @RequestParam("page") int pageNum,
			@RequestParam(name="keyword",defaultValue="",required=false) String keyword,
			Model model,HttpSession session) throws Exception {
		System.out.println("===GET /board/view");
		String SessionUserid = (String)session.getAttribute("userid");
		BoardVO view = service.view(seqno);
		
		//mapper.hitno(board);
		//조회수 증가
		String Sessioonuserid = (String)session.getAttribute("userid");
		if(!Sessioonuserid.equals(view.getUserid())) 
			service.hitno(view);
		
		LikeVO likeCheckView = service.likeCheckView(seqno, Sessioonuserid);
		
		//초기에 좋아요/싫어요 등록이 안되어져 있을 경우 "N"으로 초기화 
		if(likeCheckView == null) {
			model.addAttribute("myLikeCheck", "N");
			model.addAttribute("myDislikeCheck", "N");
		} else if(likeCheckView != null) {
			model.addAttribute("myLikeCheck", likeCheckView.getMylikecheck());
			model.addAttribute("myDislikeCheck", likeCheckView.getMydislikecheck());
		}
		
		if(!SessionUserid.equals(view.getUserid()))
			service.hitno(view);
		//model.addAttribute("view", mapper.view(seqno));
		model.addAttribute("view", view);
		model.addAttribute("page", pageNum);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pre_seqno", service.pre_seqno(seqno, keyword));
		model.addAttribute("next_seqno", service.next_seqno(seqno, keyword));
		model.addAttribute("likeCheckView", likeCheckView);
		model.addAttribute("fileListView", service.fileListView(seqno));
		
	}
	
	//좋아요/싫어요 관리
	@ResponseBody
	@PostMapping(value = "/board/likeCheck")
	public Map<String, Object> postLikeCheck(@RequestBody Map<String, Object> likeCheckData) throws Exception {
		System.out.println("===POST /board/likeCheck");
		int seqno = (int)likeCheckData.get("seqno");
		String userid = (String)likeCheckData.get("userid");
		int checkCnt = (int)likeCheckData.get("checkCnt");

		//현재 날짜, 시간 구해서 좋아요/싫어요 한 날짜/시간 입력 및 수정
		String likeDate = "";
		String dislikeDate = "";
		if(likeCheckData.get("mylikecheck").equals("Y")) 
			likeDate = LocalDateTime.now().toString();
		else if(likeCheckData.get("mydislikecheck").equals("Y")) 
			dislikeDate = LocalDateTime.now().toString();

		likeCheckData.put("likedate", likeDate);
		likeCheckData.put("dislikedate", dislikeDate);

		//TBL_LIKE 테이블 입력/수정
		LikeVO likeCheckView = service.likeCheckView(seqno,userid);
		if(likeCheckView == null) service.likeCheckRegistry(likeCheckData);
			else service.likeCheckUpdate(likeCheckData);

		//TBL_BOARD 내의 likecnt,dislikecnt 입력/수정 
		BoardVO board = service.view(seqno);
		
		int likeCnt = board.getLikecnt();
		int dislikeCnt = board.getDislikecnt();
			
		switch(checkCnt){
	    	case 1 : likeCnt --; break;
	    	case 2 : likeCnt ++; dislikeCnt --; break;
	    	case 3 : likeCnt ++; break;
	    	case 4 : dislikeCnt --; break;
	    	case 5 : likeCnt --; dislikeCnt ++; break;
	    	case 6 : dislikeCnt ++; break;
		}
		
		service.boardLikeUpdate(seqno,likeCnt,dislikeCnt);
		
		//AJAX에 전달할 map JSON 데이터 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seqno", seqno);
		map.put("likeCnt", likeCnt);
		map.put("dislikeCnt", dislikeCnt);
		
		return map;
	}

	//게시물 수정(화면 보기)
	@GetMapping("/board/modify")
	public void getModify(@RequestParam("seqno") int seqno, @RequestParam("page") int pageNum, Model model,
			@RequestParam(name="keyword",defaultValue="",required=false) String keyword
			) throws Exception{ 
		System.out.println("===GET /board/modify keyword? : "+ keyword);
		//model.addAttribute("view", mapper.view(seqno));
		model.addAttribute("view", service.view(seqno));
		model.addAttribute("page", pageNum);
		model.addAttribute("keyword", keyword);
		model.addAttribute("fileListView", service.fileListView(seqno));
		
	}
	
	//게시물 수정
	@PostMapping("/board/modify")
	public String postModify(BoardVO board,@RequestParam("page") int pageNum,
			@RequestParam(name="keyword",defaultValue="",required=false) String keyword, // 뭔가 빠진듯?
			@RequestParam(name="deleteFileList", required=false) int[] deleteFileList
			) throws Exception {
		System.out.println("===POST /board/modify --> redirect:/board/view?seqno=번호&page=페이지번호&keyword=키워드");
		//mapper.modify(board);
		
		service.modify(board);
		
		return "redirect:/board/view?seqno=" + board.getSeqno() + "&page=" + pageNum+ "&keyword=" + URLEncoder.encode(keyword, "UTF-8");
	}
	
	//게시물 삭제
	@GetMapping("/board/delete")
	public String getDelete(@RequestParam("seqno") int seqno) throws Exception {
		
		System.out.println("===GET  /board/delete --> redirect:/board/list?page=1");
		
		Map<String, Object> data = new HashMap<>();
		data.put("kind", "B"); // 게시물 삭제시 tbl_file 내의 파일 정보 수정
		data.put("seqno", seqno);
		service.deleteFileList(data);
		service.delete(seqno);
		return "redirect:/board/list?page=1";
	}
	
	//파일 다운로드
	@GetMapping("/board/filedownload")
	public void filedownload(@RequestParam("fileseqno") int fileseqno, HttpServletResponse rs) throws Exception {
		System.out.println("===GET  /board/filedownload");
		String path = "c:\\Repository\\file\\";
		
		//BoardVO board = mapper.view(seqno);
		FileVO fileInfo = service.fileInfo(fileseqno);
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File(path+fileInfo.getStored_filename()));
		
		//헤드값을 Content-Disposition로 주게 되면 Response Body로 오는 값을 filename으로 다운받으라는 것임
		//예) Content-Disposition: attachment; filename="hello.jpg"
		rs.setContentType("application/octet-stream");
		rs.setContentLength(fileByte.length);
		rs.setHeader("Content-Disposition",  "attachment; filename=\""+URLEncoder.encode(fileInfo.getOrg_filename(), "UTF-8")+"\";");
		rs.getOutputStream().write(fileByte);
		rs.getOutputStream().flush();//버퍼에 있는 내용을 write
		rs.getOutputStream().close();
	}

	//댓글 처리
	@ResponseBody
	@PostMapping("/board/reply")
	public List<ReplyVO> postReply(@RequestBody ReplyVO reply,@RequestParam("option") String option)throws Exception{
		System.out.println("===POST  /board/reply");
		switch(option) {
		
		case "I" : service.replyRegistry(reply); //댓글 등록
				   break;
		case "U" : service.replyUpdate(reply); //댓글 수정
				   break;
		case "D" : service.replyDelete(reply); //댓글 삭제
				   break;
		}

		return service.replyView(reply);
	}
	
}
