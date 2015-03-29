package com.zs.dao;

import com.zs.po.Company;
import java.util.List;

public interface CompanyDAO {

	/**
	 * 添加新用户
	 * @param Company
	 * @return
	 */
	public Company insertCompany(Company Company);

    /**
     * 删除Company
     * @param Company
     */
    public void deleteCompany(Company Company);

    /**
     * 改Company
     * @param Company
     * @return
     */
    public Company updateCompany(Company Company);

    /**
     * 查询所有Company
     * @return 所有Company
     */
    public List<Company> selectAllCompanys();

    /**
     * 条件查询Company
     * @return Company
     */
    public List<Company> selectCompanysByCon(Company Company);

    /**
     * Id查询Company
     * @return Company
     */
    public Company selectCompanysByPK(Integer CompanyId);
	
}
