package com.yi.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yi.domain.BoardVO;
import com.yi.domain.PageMaker;
import com.yi.domain.SearchCriteria;
import com.yi.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject //자바에서 나온 주입방식, autowired는 spring에서 나온 주입방식, 활용은 같음
	private BoardService service;
	
	//sboard/list?page=10
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void list(SearchCriteria cri, Model model) {
		logger.info("list ------------ GET");
		List<BoardVO> list = service.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.searchTotalCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
	}
	
	//New Board용
	@RequestMapping(value="register",method=RequestMethod.GET)
	public void registerGet() {
		logger.info("register ------- get");
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String registerPost(BoardVO vo, Model model) {
		logger.info("register -------- post");
		
		service.regist(vo);
		model.addAttribute("result","success");
		
		return "redirect:/sboard/list";
	}
	
}























