package com.sftest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sftest.dto.UserVO;
import com.sftest.mapper.SFTestMapper;

@Controller
public class UserController {

	@Autowired
	SFTestMapper mapper;
	
	@Autowired //비밀번호 암호화 의존성 주입
	private BCryptPasswordEncoder pwdEncoder; 
	
	@GetMapping("/user/signup") //view 파일 경로와 똑같아야 함 
	public void getSignup() throws Exception {}
	
	
	
	@PostMapping("/user/signup")
	public String postSignup(UserVO user) throws Exception {
		
		
		mapper.signup(user);		
		return "redirect:/board/list";
		
	}
	
	@ResponseBody
	@PostMapping("/user/idCheck")
	public int getIdCheck(@RequestBody String userid) {
		
		return mapper.idCheck(userid);
		
	}
	
	
}
