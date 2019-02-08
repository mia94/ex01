package com.yi.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
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
	
	@RequestMapping(value="readPage", method=RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, SearchCriteria cri,Model model) {//주소 ?뒤에 맞는 형태로 키값이 꼭 와야함, int로 해뒀는데 String 값이 오면 에러가 남
		BoardVO vo = service.read(bno);
		service.updateviewCount(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri",cri);
	}
	
	@RequestMapping(value="removePage", method=RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, SearchCriteria cri) {
		logger.info("remove ---------- post");
		service.remove(bno);
		System.out.println("&searchType="+cri.getSearchType()+"&keyword="+cri.getKeyword());
		return "redirect:/sboard/list?page="+cri.getPage()+"&searchType="+cri.getSearchType()+"&keyword="+cri.getKeyword();
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public void modifyGet(@RequestParam("bno") int bno,SearchCriteria cri,Model model) {
		logger.info("modify ---------- get");
		BoardVO vo = service.read(bno);
		model.addAttribute("cri", cri);
		model.addAttribute("boardVO", vo);
		logger.info("수정 전 페이지번호"+cri.getPage()+"키워드"+cri.getSearchType()+cri.getKeyword());
	}
	
	@RequestMapping(value="modifyPage", method=RequestMethod.POST)
	public String modifyPostPage(BoardVO vo,SearchCriteria cri,Model model) {
		logger.info("modify ---------- post2");
		service.modify(vo);
		logger.info("수정 전 페이지번호"+cri.getPage()+"키워드"+cri.getSearchType()+cri.getKeyword());
		model.addAttribute("cri", cri);
		model.addAttribute("boardVO", vo);
		model.addAttribute("keyword", cri.getKeyword());//cri가 들고있는 내용을 심어서 보내야한다. 객체통으로는 전달이 안됨
		return "redirect:/sboard/readPage?page="+cri.getPage()+"&bno="+vo.getBno()+"&searchType="+cri.getSearchType();
	}
	
}























