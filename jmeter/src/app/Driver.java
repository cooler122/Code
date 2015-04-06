package app;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.po.Child;
import com.zs.po.Company;
import com.zs.po.User;
import com.zs.service.ChildService;
import com.zs.service.UserService;

public class Driver {
	private static long start = 0;
	private static long end = 0;
	
	public static void main(String[] args) throws IOException, ParseException {		
		String configLocation= "spring-service-consumer.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
        ChildService childService = (ChildService) context.getBean("childService");
        UserService userService = (UserService) context.getBean("userService");
		
		start = System.currentTimeMillis();
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
		
		end = System.currentTimeMillis();
		System.out.println("cost time:"+(end - start));
		
		if(userList != null && userList.size() > 0){
			System.out.println(true);
		}else{
			System.out.println(false);
		}
	}

}
