package com.zs.service;

import com.zs.po.Child;
import com.zs.po.Company;
import com.zs.po.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LuncherConsumer  {
	public static void main(String[] args) throws InterruptedException{
		LuncherConsumer luncher=new LuncherConsumer();
		luncher.start();
	}
	
	
	void start(){
		String configLocation= "spring/spring-service-consumer.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
//		DubboService dubboService = (DubboService) context.getBean("dubboService");
        ChildService childService = (ChildService) context.getBean("childService");
        UserService userService = (UserService) context.getBean("userService");
		
//		MsgInfo info =new MsgInfo();
//		info.setId(1);
//		info.setName("ruisheh");
//		List<String> msgs=new ArrayList<String>();
//		msgs.add("I");
//		msgs.add("am");
//		msgs.add("com.zs.service");
//		info.setMsgs(msgs);
//        List<String> msgInfo = dubboService.returnMsgInfo(info).getMsgs();
//		System.out.println(msgInfo);

        List<Child> childList = childService.selectAllChilds();
        for(Child child : childList){
            System.out.println(child.getChildName());
        }

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



	}
}
