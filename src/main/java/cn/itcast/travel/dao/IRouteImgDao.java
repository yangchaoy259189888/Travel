package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/11 21:55
 * @Description: TODO
 **/
public interface IRouteImgDao {
    List<RouteImg> findByRid(int rid);
}
