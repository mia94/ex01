package com.yi.persistence;

import java.util.List;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
import com.yi.domain.SearchCriteria;

public interface BoardDao {
	
	public void insert(BoardVO vo);
	public BoardVO read(int bno);
	public void update(BoardVO vo);
	public void delete(int bno);
	public List<BoardVO> listAll();
	
	public void viewCount(int bno);
	
	//페이징
	public List<BoardVO> listPage(int page);
	public List<BoardVO> listCriteria(Criteria cri);
	public int totalCount();
	
	//서치용
	public List<BoardVO> listSearch(SearchCriteria cri);
	public int searchTotalCount(SearchCriteria cri);
}
