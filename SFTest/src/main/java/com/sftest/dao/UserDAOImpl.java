package com.SFTest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SFTest.dto.UserVO;
import com.SFTest.dto.AddressVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sql;	
	private static String namespace = "com.SFTest.mappers.User";
	
	//아이디 중복 체크
	@Override
	public int idCheck(String userid) {
		return sql.selectOne(namespace + ".idCheck", userid);
	}

	//로그인 정보 가져 오기
	@Override
	public UserVO login(String userid) {
		return sql.selectOne(namespace + ".login", userid);
	}

	//사용자 등록
	@Override
	public void signup(UserVO user) {
		sql.insert(namespace + ".signup", user);		
	}

	
	//주소 전체 갯수 계산
	@Override
	public int addrTotalCount(String addrSearch) {
		return sql.selectOne(namespace + ".addrTotalCount", addrSearch);
	}

	//주소 검색
	@Override
	public List<AddressVO> addrSearch(int startPoint, int postNum, String addrSearch){
		Map<String, Object> data = new HashMap<>();
		data.put("startPoint", startPoint);
		data.put("postNum", postNum);
		data.put("addrSearch", addrSearch);
		return  sql.selectList(namespace + ".addrSearch", data);
	}
	
}
