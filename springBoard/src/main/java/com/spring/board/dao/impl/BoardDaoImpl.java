package com.spring.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		String a = sqlSession.selectOne("board.boardList");
		return a;
	}

	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		return sqlSession.selectList("board.boardList",pageVo);
	}
	
	@Override
	public List<BoardVo> selectBoardListByBoardType(PageVo pageVo, List<String> boardTypeList) throws Exception {
		return sqlSession.selectList("board.boardList",pageVo);
	}
	
	@Override
	public int selectBoardCnt(List<String> boardTypeList) throws Exception {
		return sqlSession.selectOne("board.boardTotal", boardTypeList);
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public List<CodeVo> selectCodeNamePhone(CodeVo codeVo) throws Exception {
		return sqlSession.selectList("board.codeNamePhoneList", codeVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	//수정
	@Override
	public int boardModify(BoardVo boardVo) throws Exception {
		return sqlSession.insert("board.boardModify", boardVo);
	}
	
	//삭제
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		return sqlSession.insert("board.boardDelete", boardVo);
	}
	
	@Override
	public int insertUser(UserVo userVo) throws Exception {
		return sqlSession.insert("user.insertUser", userVo);
	}
	
	@Override
	public int selectId(UserVo userVo) throws Exception {
		return sqlSession.selectOne("user.selectId",userVo);
	}
	
	@Override
	public UserVo selectUser(UserVo userVo) throws Exception {
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
}
