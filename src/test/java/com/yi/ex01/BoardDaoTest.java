package com.yi.ex01;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.BoardVO;
import com.yi.persistence.BoardDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {

	
	@Autowired
	private BoardDao dao;
	/*
	public void insert(BoardVO vo);
	public BoardVO read(int bno);
	public void update(BoardVO vo);
	public void delete(int bno);
	public List<BoardVO> listAll();
	 * */
	@Test
	public void test01insert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("HELLO");
		vo.setContent("¾È´¨");
		vo.setWriter("È÷È÷");
		dao.insert(vo);
	}
	
	@Test
	public void test02read() {
		BoardVO vo = new BoardVO();
		vo = dao.read(1);
		Assert.assertNotNull(vo);
	}
	
	@Test
	public void test03update() {
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setTitle("Çï·Î");
		vo.setContent("ÇÏÇÏÁK");
		dao.update(vo);
	}
	
	@Test
	public void test04delete() {
		dao.delete(2);
	}
	
	@Test
	public void test05listAll() {
		List<BoardVO> list = dao.listAll();
		Assert.assertNotNull(list);
	}
}






















