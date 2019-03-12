package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.IFavoriteDao;
import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.dao.IRouteImgDao;
import cn.itcast.travel.dao.ISellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.IRouteService;

import java.util.List;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/5 21:02
 * @Description: TODO
 **/
public class RouteServiceImpl implements IRouteService {
    private IRouteDao routeDao = new RouteDaoImpl();
    private IRouteImgDao routeImgDao = new RouteImgDaoImpl();
    private ISellerDao sellerDao = new SellerDaoImpl();
    private IFavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pageBean = new PageBean<Route>();

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid, rname);
        pageBean.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pageBean.setList(list);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));

        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);

        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);

        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }


}
