package com.SFTest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SFTest.dto.UserVO;
import com.SFTest.mapper.SFTestMapper;

@Controller
public class UserController {

	@Autowired
	SFTestMapper mapper;
	
	@Autowired //비밀번호 암호화 의존성 주입
	private BCryptPasswordEncoder pwdEncoder; 

	//로그인
	@GetMapping("/user/login")
	public void getLogin(Model model) {}
	
	@PostMapping("/user/login")
	public String postLogIn(UserVO loginData,Model model,HttpSession session,
			RedirectAttributes rttr) {
		
		//아이디 존재 여부 확인
		if(mapper.idCheck(loginData.getUserid()) == 0) {
			rttr.addFlashAttribute("message", "ID_NOT_FOUND");
			return "redirect:/user/login";
		}
		
		UserVO member = mapper.login(loginData.getUserid());
		
		//패스워드 확인
		if(!pwdEncoder.matches(loginData.getPassword(),member.getPassword())) {
			rttr.addFlashAttribute("message", "PASSWORD_NOT_FOUND");
			return "redirect:/user/login";
		}else {
		
			
		//세션 생성
		session.setMaxInactiveInterval(3600*7);
		session.setAttribute("userid", loginData.getUserid());
		session.setAttribute("username", member.getUsername());

		return "redirect:/board/list?page=1";
		}

	}
	
	//로그아웃
	@GetMapping("/user/logout")
	public String getLogout(HttpSession session) throws Exception {
		
		session.invalidate();
		return "redirect:/";
	}
	
	//회원 가입
	@GetMapping("/user/signup")
	public void getSignup() throws Exception { }
	
	//회원 가입
	@ResponseBody
	@PostMapping("/user/signup")
	public String postSignup(@RequestBody UserVO user) throws Exception {
	
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		
		mapper.signup(user);		
		//return "{\"username\":" + user.getUsername() + "\",\"status\":\"good\"}";
		return "{\"username\":\"" + user.getUsername() + "\",\"status\":\"good\"}";

		// json 포맷으로 문자열 만든 것 {username: "김철수", status: "good"}
		
	}
	
	//아이디 중복 체크
	@ResponseBody
	@PostMapping("/user/idCheck")
	public int getIdCheck(@RequestBody String userid) {
		
		return mapper.idCheck(userid);
		
	}
	
	
}
