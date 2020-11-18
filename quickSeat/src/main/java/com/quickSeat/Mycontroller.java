package com.quickSeat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quickSeat.dao.ISimpleBbsDao;

@Controller
public class Mycontroller {

	
	@Autowired 
	ISimpleBbsDao dao;
	
//	@RequestMapping("/")
//	public @ResponseBody String root() throws Exception{
//		return "JdbcTemplate 사용하기";
//	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView Main() {
		ModelAndView mav = new ModelAndView("/index");

		return mav;
	}
	
	/*
	//GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userlistPage(Model model) {
		model.addAttribute("users", userDao.list());
		return "/userlist";
	}
	*/
	
//	@RequestMapping(value = "/index", method = RequestMethod.GET)
//	public ModelAndView Main() {
//		ModelAndView mav = new ModelAndView("/index");
//
//		return mav;
//	}
	
	@RequestMapping("/list")
	public String userlistpage(Model model) {
		model.addAttribute("list", dao.listDao());
		return "/list";
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", dao.viewDao(sId));
		return "/view";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		dao.writeDao(request.getParameter("writer"),
				request.getParameter("title"),
				request.getParameter("content"));
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		dao.deleteDao(request.getParameter("id"));
		return "redirect:list";
	}
}
