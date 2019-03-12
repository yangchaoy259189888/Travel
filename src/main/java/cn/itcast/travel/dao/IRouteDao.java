package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/5 21:08
 * @Description: TODO
 **/
public interface IRouteDao {

    int findTotalCount(int cid, String rname);

    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    Route findOne(int rid);
}
