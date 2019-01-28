package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.domain.BoardVO;
import com.yi.persistence.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {//서비스는@Service달기, Dao는 @Repository달기
	
	@Autowired
	private BoardDao dao;

	@Override
	public void regist(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void remove(int bno) {
		// TODO Auto-generated method stub
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public void updateviewCount(int bno) {
		// TODO Auto-generated method stub
		dao.viewCount(bno);
		
	}

}
