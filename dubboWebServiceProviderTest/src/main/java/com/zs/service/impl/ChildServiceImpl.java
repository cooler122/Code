package com.zs.service.impl;

import com.zs.dao.ChildDAO;
import com.zs.po.Child;
import com.zs.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {

	@Autowired
	private ChildDAO childDAO;

    @Override
    public Child insertChild(Child child) {
        return childDAO.insertChild(child);
    }

    @Override
    public void deleteChild(Child child) {
        childDAO.deleteChild(child);
    }

    @Override
    public Child updateChild(Child child) {
        return childDAO.updateChild(child);
    }

    @Override
    public List<Child> selectAllChilds() {
        return childDAO.selectAllChilds();
    }

    @Override
    public List<Child> selectChildsByCon(Child child) {
        return childDAO.selectChildsByCon(child);
    }

    @Override
    public Child selectChildsByPK(Integer childId) {
        return childDAO.selectChildsByPK(childId);
    }
}
