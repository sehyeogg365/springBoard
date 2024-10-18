package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

public interface boardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception;
	
	public List<BoardVo> SelectBoardListByBoardType(PageVo pageVo, List<String>boardTypeList) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt(List<String>boardTypeList) throws Exception;
	
	public List<CodeVo> selectCodeNamePhone(CodeVo codeVO)throws Exception;
	
	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int boardModify(BoardVo boardVo) throws Exception;//수정
	
	public int boardDelete(BoardVo boardVo) throws Exception;//삭제
	
	public int insertUser(UserVo userVo) throws Exception;
	
	public boolean isDuplicateId(UserVo userVo) throws Exception;

	public UserVo selectUser(UserVo userVo) throws Exception;
}
