package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}

	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(pageVo);
	}
	
	@Override
	public List<BoardVo> SelectBoardListByBoardType(PageVo pageVo, List<String>boardTypeList) throws Exception {
		
		return boardDao.selectBoardListByBoardType(pageVo, boardTypeList);
	}
	
	@Override
	public int selectBoardCnt(List<String>boardTypeList) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt(boardTypeList);
	}	
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public List<CodeVo> selectCodeNamePhone(CodeVo codeVo) throws Exception {
		
		List<CodeVo> codeNameList = boardDao.selectCodeNamePhone(codeVo);
		
		return codeNameList;
	}

	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(boardVo);
	}
	
	//수정
	@Override
	public int boardModify(BoardVo boardVo) throws Exception {
		
		return boardDao.boardModify(boardVo);
	}

	//삭제
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		
		return boardDao.boardDelete(boardVo);
	}
	//회원가입
	@Override
	public int insertUser(UserVo userVo) throws Exception {
		
		return boardDao.insertUser(userVo);
	}
	//중복아이디 검사
	@Override
	public boolean isDuplicateId(UserVo userVo) throws Exception {
		
		int count = boardDao.selectId(userVo);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	//로그인
	@Override
	public UserVo selectUser (UserVo userVo) throws Exception {
		
		return boardDao.selectUser(userVo);
		
	}
	
	
}
