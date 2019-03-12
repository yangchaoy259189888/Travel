package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2018/12/25 9:33
 * @Description: TODO
 **/
public interface IUserDao {
    /**
     * @param: [username]
     * @return: cn.itcast.travel.domain.User
     * @author: 杨朝阳
     * @date: 2018/12/25 9:55
     * @Description: 根据username查询用户信息
     */
    User findByUsername(String username);

    /**
     * @param: [user]
     * @return: void
     * @author: 杨朝阳
     * @date: 2018/12/25 9:56
     * @Description: 保存用户信息到数据库
     */
    void save(User user);

    /**
     * @param: [code]
     * @return: cn.itcast.travel.domain.User
     * @author: 杨朝阳
     * @date: 2018/12/26 10:28
     * @Description: 根据激活码
     */
    User findByCode(String code);

    /**
     * @param: [user]
     * @return: void
     * @author: 杨朝阳
     * @date: 2018/12/26 10:40
     * @Description: 改变激活状态status
     */
    void updateStatus(User user);

    /**
     * @param: [username, password]
     * @return: void
     * @author: 杨朝阳
     * @date: 2018/12/26 14:17
     * @Description: 根据用户名及密码进行登录
     */
    User findByUsernameAndPassword(String username, String password);
}
