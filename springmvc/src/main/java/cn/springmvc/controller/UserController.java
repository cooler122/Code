package cn.springmvc.controller;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

	@RequestMapping("/queryAllUser")
	public String queryAllUser(ModelMap modelMap){
        List<User> userList = userService.selectAllUsers();
        modelMap.addAttribute("userList", userList);
        System.out.println("here");
		return "/user/listAll";
	}
	
}
