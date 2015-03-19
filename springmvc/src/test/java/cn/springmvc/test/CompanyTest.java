package cn.springmvc.test;

import cn.springmvc.model.Company;
import cn.springmvc.model.Position;
import cn.springmvc.model.Company;
import cn.springmvc.service.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CompanyTest {

    private CompanyService companyService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml"}
        );
		companyService = (CompanyService) context.getBean("companyServiceImpl");
	}

    @Test
    public void selectAllCompany(){
        List<Company> companyList = companyService.selectAllCompanys();
        Assert.assertTrue(companyList != null);
    }

    @Test
    public void selectCompanysByPK(){
        Company company = companyService.selectCompanysByPK(1);
        Assert.assertTrue(company != null);
    }
}
