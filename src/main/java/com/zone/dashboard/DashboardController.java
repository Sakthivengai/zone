package com.zone.dashboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.zone.signup.SignupDao;
import com.zone.user.UserDao;
import com.zone.user.UserModel;



@RestController
public class DashboardController {
	
	@Autowired
	SignupDao signupDao;
	@Autowired
	UserDao userDao;
	
	
	
	@RequestMapping(value = "/dashboard", method=RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("names");
		UserModel user = userDao.findbyuserName(username);
		int userclientid = user.getClient_id();
		int userid = user.getUser_id();
		session.setAttribute("username", username);
		session.setAttribute("clientid", userclientid);
		session.setAttribute("userid", userid);
		ModelAndView model = new ModelAndView();
		model.setViewName("zone_dashboard");
		return model;		
	}
	
}
