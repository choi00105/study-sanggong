package com.SFTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFTest.dao.UserDAO;
import com.SFTest.dto.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;
	
	//아이디 중복 체크
	@Override
	public int idCheck(String userid) {
		return dao.idCheck(userid);
	}

	//로그인 정보 가져 오기
	@Override
	public UserVO login(String userid) {
		return dao.login(userid);
	}

	//사용자 등록
	@Override
	public void signup(UserVO user) {
		dao.signup(user);		
	}

}
