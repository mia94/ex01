package com.yi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yi.domain.BoardVO;
import com.yi.service.BoardService;

@Controller
@RequestMapping("/board/*")//BoardController �ȿ� ��� urlĿ�ǵ�տ� �ڵ����� board/�� �ٴ´�.
public class BoardController {
	
	@Autowired//DI : Dependency Injection
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public void listAll(Model model) {
		List<BoardVO> list = service.listAll();
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public void registerGet() {
		logger.info("register ------- get");
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String registerPost(BoardVO vo, Model model) {
		logger.info("register -------- post");
		
		service.regist(vo);
		model.addAttribute("result","success");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) {//�ּ� ?�ڿ� �´� ���·� Ű���� �� �;���, int�� �ص״µ� String ���� ���� ������ ��
		BoardVO vo = service.read(bno);
		service.updateviewCount(bno);
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno) {
		logger.info("remove ---------- post");
		service.remove(bno);
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public void modifyGet(@RequestParam("bno") int bno, Model model) {
		logger.info("modify ---------- get");
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPost(BoardVO vo) {
		logger.info("modify ---------- post");
		service.modify(vo);
		logger.info(vo.toString());
		return "redirect:/board/listAll";
	}
	
}
























