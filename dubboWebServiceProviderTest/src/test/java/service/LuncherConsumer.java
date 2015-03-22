package service;

import com.zs.model.MsgInfo;
import com.zs.service.DubboService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class LuncherConsumer  {
	public static void main(String[] args) throws InterruptedException{
		LuncherConsumer luncher=new LuncherConsumer();
		luncher.start();
	}
	
	
	void start(){
		String configLocation="spring/dubbo-consumer.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
		DubboService ds=(DubboService) context.getBean("demoService");
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
