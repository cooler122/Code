package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class LuncherProvider  {
	public static void main(String[] args) throws InterruptedException{
		LuncherProvider luncher=new LuncherProvider();
		luncher.start();
		Thread.sleep(1000*60*100);
	}
	
	void start(){
		String configLocation= "spring/spring-service-provider.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
		String [] names=context.getBeanDefinitionNames();
        for(String name : names){
            System.out.println(name);
        }
	}
}
