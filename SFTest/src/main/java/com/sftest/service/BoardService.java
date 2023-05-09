package com.SFTest.service;

import java.util.List;
import java.util.Map;

import com.SFTest.dto.BoardVO;
import com.SFTest.dto.ReplyVO;
import com.SFTest.dto.LikeVO;

public interface BoardService {

	//게시물 목록 보기
	public List<BoardVO> list(int startPoint,int postNum, String keyword);
	
	//게시물 전체 갯수 계산
	public int getTotalCount(String keyword);
	
	//게시물 등록
	public void write(BoardVO board);
	
	//게시물 상세 보기
	public BoardVO view(int seqno);
	
	//이전 보기 
	public int pre_seqno(int seqno, String keyword);
	
	//다음 보기
	public int next_seqno(int seqno, String keyword);
	
	//조회수 업데이트
	public void hitno(BoardVO board);
	
	//게시물 수정 
	public void modify(BoardVO board);
	
	//게시물 삭제
	public void delete(int seqno);
	
	//좋아요/싫어요 확인 가져 오기
	public LikeVO likeCheckView(int seqno,String userid) throws Exception;
	
	//좋아요/싫어요 갯수 수정하기
	public void boardLikeUpdate(int seqno, int likecnt, int dislikecnt) throws Exception;
	
	//좋아요/싫어요 확인 등록하기
	public void likeCheckRegistry(Map<String,Object> map) throws Exception;
	
	//좋아요/싫어요 확인 수정하기
	public void likeCheckUpdate(Map<String,Object> map) throws Exception;
	
	//댓글 보기
	public List<ReplyVO> replyView(ReplyVO reply) throws Exception;
	
	//댓글 수정
	public void replyUpdate(ReplyVO reply) throws Exception;
	
	//댓글 등록 
	public void replyRegistry(ReplyVO reply) throws Exception;
	
	//댓글 삭제
	public void replyDelete(ReplyVO reply) throws Exception;
	
}


