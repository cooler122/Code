package com.zs.service.impl;

import com.zs.dao.CompanyDAO;
import com.zs.po.Company;
import com.zs.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
	private CompanyDAO companyDAO;


    @Override
    public Company insertCompany(Company company) {
        return companyDAO.insertCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyDAO.deleteCompany(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyDAO.updateCompany(company);
    }

    @Override
    public List<Company> selectAllCompanys() {
        return companyDAO.selectAllCompanys();
    }

    @Override
    public List<Company> selectCompanysByCon(Company company) {
        return companyDAO.selectCompanysByCon(company);
    }

    @Override
    public Company selectCompanysByPK(Integer companyId) {
        return companyDAO.selectCompanysByPK(companyId);
    }
}
