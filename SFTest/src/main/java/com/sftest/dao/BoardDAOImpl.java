package com.SFTest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SFTest.dto.BoardVO;
import com.SFTest.dto.ReplyVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sql;	
	private static String namespace = "com.SFTest.mappers.Board";
	
	//게시물 목록 보기
	@Override
	public List<BoardVO> list(int startPoint, int postNum,String keyword) {
		Map<String,Object> data = new HashMap<>();
		data.put("startPoint", startPoint);
		data.put("postNum", postNum);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".list",data);
	}
	
	//게시물 전체 갯수 계산
	@Override
	public int getTotalCount(String keyword) {
		return sql.selectOne(namespace + ".getTotalCount",keyword);
	}

	//게시물 등록
	@Override
	public void write(BoardVO board) {
		sql.insert(namespace + ".write",board);		
	}

	//게시물 상세 보기
	@Override
	public BoardVO view(int seqno) {
		return sql.selectOne(namespace + ".view", seqno);
	}

	//이전 보기
	@Override
	public int pre_seqno(int seqno, String keyword) {
		Map<String,Object> data = new HashMap<>();
		data.put("seqno", seqno);
		data.put("keyword", keyword);
		return sql.selectOne(namespace + ".pre_seqno", data);		
	}
	
	//다음 보기
	@Override
	public int next_seqno(int seqno, String keyword) {
		Map<String,Object> data = new HashMap<>();
		data.put("seqno", seqno);
		data.put("keyword", keyword);
		return sql.selectOne(namespace + ".next_seqno", data);
	}
	
	//조회수 등록
	@Override
	public void hitno(BoardVO board) {
		sql.update(namespace + ".hitno", board);		
	}

	//게시물 수정
	@Override
	public void modify(BoardVO board) {
		sql.update(namespace + ".modify", board);		
	}

	//게시물 삭제
	@Override
	public void delete(int seqno) {
		sql.delete(namespace + ".delete", seqno);		
	}
	
	//댓글 보기
	@Override
	public List<ReplyVO> replyView(ReplyVO reply) throws Exception{
		
		return sql.selectList(namespace + ".replyView", reply);
	}
	
	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO reply) throws Exception{
		sql.update(namespace + ".replyUpdate", reply);
	}
	
	//댓글 등록
	@Override
	public void replyRegistry(ReplyVO reply) throws Exception{
		sql.insert(namespace + ".replyRegistry", reply);
	}
	
	//댓글 삭제
	@Override
	public void replyDelete(ReplyVO reply) throws Exception{
		sql.delete(namespace + ".replyDelete", reply);
	}

}
