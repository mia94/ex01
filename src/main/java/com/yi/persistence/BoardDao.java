package com.yi.persistence;

import java.util.List;

import com.yi.domain.BoardVO;

public interface BoardDao {
	
	public void insert(BoardVO vo);
	public BoardVO read(int bno);
	public void update(BoardVO vo);
	public void delete(int bno);
	public List<BoardVO> listAll();
	
	public void viewCount(int bno);
}
