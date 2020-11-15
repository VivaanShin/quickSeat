package com.quickSeat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quickSeat.jdbc.MyUserDAO;

@Controller
public class Mycontroller {

	@Autowired
	private MyUserDAO userDao;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "JdbcTemplate 사용하기";
	}
	
	//GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userlistPage(Model model) {
		model.addAttribute("users", userDao.list());
		return "/userlist";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView MAin() {
		ModelAndView mav = new ModelAndView("/index");
		
		return mav;
	}
	
}
