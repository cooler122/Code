package app;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.model.Book;
import com.zs.service.BookService;

public class JarTest implements JavaSamplerClient {
	private static long start = 0;
	private static long end = 0;
	private static String result;
	private BookService bookService;
	
	@Override
	public Arguments getDefaultParameters() { //���ô���Ĳ������������ö���������õĲ�������ʾ��Jmeter�Ĳ����б���
		Arguments args = new Arguments();
		args.addArgument("id", 1 + "");
		args.addArgument("id2", 2 + "");
		return args;
	}
	
	@Override
	public void setupTest(JavaSamplerContext arg0) {	//��ʼ��������ʵ������ʱÿ���߳̽�ִ��һ�Σ��ڲ��Է�������ǰִ��	
		String configLocation="/dubbo-consumer.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
		bookService = (BookService) context.getBean("bookService");
		start = System.currentTimeMillis();
	}
	
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {	//����ִ�е�ѭ���壬�����߳�����ѭ�������Ĳ�ͬ��ִ�ж��
		SampleResult sr = new SampleResult();
		try {
			sr.sampleStart();
	        Book book = new Book();
	        book.setId(1);
	        Book bookResult = bookService.select(book);
			result = bookResult.getName();			
			if(result != null){
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
	public void teardownTest(JavaSamplerContext arg0) {	//����������ʵ������ʱÿ���߳̽�ִ��һ�Σ��ڲ��Է������н�����ִ��
		end = System.currentTimeMillis();
		System.out.println("cost time :" + (end - start));

	}

}
