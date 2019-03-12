package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/5 20:59
 * @Description: 线路service
 **/
public interface IRouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(String rid);
}