package com.zs.app;

import com.zs.model.MsgInfo;
import com.zs.service.DubboService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.ArrayList;
import java.util.List;

public class ConsumerApp {
	public static void main(String[] args) throws InterruptedException{
		ConsumerApp luncher=new ConsumerApp();
		luncher.returnMsgInfoTest();
	}
	
	void returnMsgInfoTest(){
		ApplicationContext context =new  ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        DubboService ds=(DubboService) context.getBean("dubboService");
		String [] names=context.getBeanDefinitionNames();
		System.out.print("Beans:");
		for (String string : names) {
			System.out.print(string);
		}
		
		MsgInfo info =new MsgInfo();
		info.setId(1);
		info.setName("ruisheh");
		List<String> msgs=new ArrayList<String>();
		msgs.add("I");
		msgs.add("am");
		msgs.add("service");
		info.setMsgs(msgs);
		System.out.println(ds.returnMsgInfo(info).getMsgs());
	}
}
