package com.zs.dao;

import com.zs.po.Child;
import sun.misc.Resource;

import java.util.List;

public interface ChildDAO {

	/**
	 * 添加新用户
	 * @param Child
	 * @return
	 */
	public Child insertChild(Child Child);

    /**
     * 删除Child
     * @param Child
     */
    public void deleteChild(Child Child);

    /**
     * 改Child
     * @param Child
     * @return
     */
    public Child updateChild(Child Child);

    /**
     * 查询所有Child
     * @return 所有Child
     */
    public List<Child> selectAllChilds();

    /**
     * 条件查询Child
     * @return Child
     */
    public List<Child> selectChildsByCon(Child Child);

    /**
     * Id查询Child
     * @return Child
     */
    public Child selectChildsByPK(Integer ChildId);
	
}
