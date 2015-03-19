package cn.springmvc.service.impl;

import cn.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.UserDAO;
import cn.springmvc.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;


    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    @Override
    public List<User> selectUsersByCon(User user) {
        return userDAO.selectUsersByCon(user);
    }

    @Override
    public User selectUsersByPK(Integer userId) {
        return userDAO.selectUsersByPK(userId);
    }
}
