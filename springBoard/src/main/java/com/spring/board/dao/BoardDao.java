package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

public interface BoardDao {

	public String selectTest() throws Exception;
	
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception;
	
	public List<BoardVo> selectBoardListByBoardType(PageVo pageVo, List<String> boardTypeList) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt(List<String> boardTypeList) throws Exception;
	
	public List<CodeVo> selectCodeNamePhone(CodeVo codeVo) throws Exception;
	
	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int boardModify(BoardVo boardVo) throws Exception;
	
	public int boardDelete(BoardVo boardVo) throws Exception;

	public int insertUser(UserVo userVo) throws Exception;
	
	public int selectId(UserVo userVo) throws Exception;

	public UserVo selectUser(UserVo userVo) throws Exception;
}
