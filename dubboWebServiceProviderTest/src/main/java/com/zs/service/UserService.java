package com.zs.service;
import com.zs.po.User;
import java.util.List;

public interface UserService {

    /**
     * 添加新用户
     * @param user
     * @return
     */
    public void insertUser(User user);

    /**
     * 删除User
     * @param user
     */
    public void deleteUser(User user);

    /**
     * 改User
     * @param user
     * @return
     */
    public User updateUser(User user);

    /**
     * 查询所有User
     * @return 所有User
     */
    public List<User> selectAllUsers();

    /**
     * 条件查询User
     * @return User
     */
    public List<User> selectUsersByCon(User user);

    /**
     * Id查询User
     * @return User
     */
    public User selectUsersByPK(Integer userId);
}
