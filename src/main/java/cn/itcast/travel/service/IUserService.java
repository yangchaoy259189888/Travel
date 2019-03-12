package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2018/12/25 9:32
 * @Description: TODO
 **/
public interface IUserService {
    boolean regist(User user);
    boolean active(String code);
    User login(User user);
}
