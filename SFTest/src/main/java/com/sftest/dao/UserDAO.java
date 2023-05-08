package com.SFTest.dao;

import com.SFTest.dto.UserVO;

public interface UserDAO {

	//아이디 중복 체크. 카운터가 0이면 아이디 사용 가능, 1이면 기존 사용 중인 아이디
	public int idCheck(String userid);
	
	//로그인 정보 가져 오기
	public UserVO login(String userid);
	
	//사용자등록
	public void signup(UserVO user);
	
}
