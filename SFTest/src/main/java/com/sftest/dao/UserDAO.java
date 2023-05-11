package com.SFTest.dao;

import java.util.List;

import com.SFTest.dto.AddressVO;
import com.SFTest.dto.UserVO;

public interface UserDAO {

	//아이디 중복 체크. 카운터가 0이면 아이디 사용 가능, 1이면 기존 사용 중인 아이디
	public int idCheck(String userid);
	
	//로그인 정보 가져 오기
	public UserVO login(String userid);
	
	//사용자등록
	public void signup(UserVO user);
	
	//주소 전체 갯수 계산
	public int addrTotalCount(String addrSearch);

	//주소 검색
	public List<AddressVO> addrSearch(int startPoint, int postNum, String addrSearch);
	
}
