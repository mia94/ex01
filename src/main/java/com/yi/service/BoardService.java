package com.yi.service;

import java.util.List;

import com.yi.domain.BoardVO;

public interface BoardService {
	public void regist(BoardVO vo);
	public BoardVO read(int bno);
	public void modify(BoardVO vo);
	public void remove(int bno);
	public List<BoardVO> listAll();
	
	public void updateviewCount(int bno);
}
