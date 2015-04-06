package app;

import java.util.List;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.po.Child;
import com.zs.service.ChildService;

public class JarTest implements JavaSamplerClient {
	private static long start = 0;
	private static long end = 0;
	private ChildService childService;
	
	@Override
	public Arguments getDefaultParameters() { //设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
		Arguments args = new Arguments();
		args.addArgument("id", 1 + "");
		args.addArgument("id2", 2 + "");
		return args;
	}
	
	@Override
	public void setupTest(JavaSamplerContext arg0) {	//初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行	
		String configLocation="/spring-service-consumer.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
		childService = (ChildService) context.getBean("childService");
		start = System.currentTimeMillis();
	}
	
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {	//测试执行的循环体，根据线程数和循环次数的不同可执行多次
		SampleResult sr = new SampleResult();
		try {
			sr.sampleStart();
			
			List<Child> childList = childService.selectAllChilds();	       	
			if(childList != null && childList.size() > 0){
				sr.setSuccessful(true);  
			}else{
				sr.setSuccessful(false);  
			}
			
			sr.sampleEnd();				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sr;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {	//结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
		end = System.currentTimeMillis();
		System.out.println("cost time :" + (end - start));
	}

}
