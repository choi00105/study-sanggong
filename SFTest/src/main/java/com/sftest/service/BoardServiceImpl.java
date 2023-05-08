package com.SFTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SFTest.dao.BoardDAO;
import com.SFTest.dto.BoardVO;
import com.SFTest.dto.ReplyVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;

	//게시물 목록 보기
	@Override
	public List<BoardVO> list(int startPoint, int postNum, String keyword) {
		return dao.list(startPoint,postNum,keyword);
	}
	
	//게시물 전체 갯수 계산
	@Override
	public int getTotalCount(String keyword) {
		return dao.getTotalCount(keyword);
	}

	//게시물 등록
	@Override
	public void write(BoardVO board) {
		dao.write(board);
	}

	//게시물 내용 보기
	@Override
	public BoardVO view(int seqno) {
		return dao.view(seqno);
	}

	//이전 보기
	@Override
	public int pre_seqno(int seqno, String keyword) {
		return dao.pre_seqno(seqno, keyword);
	}
	
	//다음 보기
	@Override
	public int next_seqno(int seqno, String keyword) {
		return dao.next_seqno(seqno, keyword);
	}
	//조회수 등록
	@Override
	public void hitno(BoardVO board) {
		dao.hitno(board);		
	}

	//게시물 수정
	@Override
	public void modify(BoardVO board) {
		dao.modify(board);		
	}

	//게시물 삭제
	@Override
	public void delete(int seqno) {
		dao.delete(seqno);		
	}
	
	//댓글 보기
	@Override
	public List<ReplyVO> replyView(ReplyVO reply) throws Exception{
		return dao.replyView(reply);
	}
	
	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO reply) throws Exception{
		dao.replyUpdate(reply);
	}
	
	//댓글 등록
	@Override
	public void replyRegistry(ReplyVO reply) throws Exception{
		dao.replyRegistry(reply);
	}
	
	//댓글 삭제
	@Override
	public void replyDelete(ReplyVO reply) throws Exception{
		dao.replyDelete(reply);
	}

}