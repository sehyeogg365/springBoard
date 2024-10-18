package com.spring.board.vo;

import java.util.List;

public class PageVo {
	
	private int pageNo = 0;
	
	private List<String> boardTypeList;
	
	private String codeName;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<String> getBoardTypeList() {
		return boardTypeList;
	}

	public void setBoardTypeList(List<String> boardTypeList) {
		this.boardTypeList = boardTypeList;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
}
