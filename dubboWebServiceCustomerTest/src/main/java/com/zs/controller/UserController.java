package com.zs.controller;

import com.zs.po.Child;
import com.zs.po.Company;
import com.zs.po.User;
import com.zs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/json")
    public ModelMap json(ModelMap map) {
        System.out.println("UserController -- json()");
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/testUser")
	public String testUser(ModelMap map) {
        System.out.println("UserController -- testUser()");
		if (userService != null) {
            List<User> userList = userService.selectAllUsers();
            for(User user : userList){
                System.out.println("用户名称：" + user.getName());
                Company company =  user.getCompany();
                System.out.println("公司名称：" + (company != null ? company.getCompanyName() : "none"));
                List<Child> childList1 = user.getChildList();
                for(Child child : childList1){
                    System.out.println("孩子:" + child.getChildName());
                }
                System.out.println("-----------------");
            }
            map.addAttribute("userList", userList);
			return "pages/userPage";
		}
		return "pages/error";
	}
}