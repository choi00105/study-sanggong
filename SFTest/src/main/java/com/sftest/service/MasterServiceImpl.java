package com.SFTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFTest.dao.MasterDAO;
import com.SFTest.dto.FileVO;

@Service
public class MasterServiceImpl implements MasterService{

	@Autowired
	MasterDAO dao;
	
	// 삭제 할 파일 목록 갯수
	public int filedeleteCount() {
		return dao.filedeleteList();
	}
	
	// 삭제 파일 목록 정보
	public List<FileVO> filedeleteList(){
		
	}
	
	// 파일 삭제
	public void deleteFile(int fileseqno) {
		
	}
		
}
