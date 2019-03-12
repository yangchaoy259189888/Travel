package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.IFavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.IFavoriteService;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/12 9:42
 * @Description: TODO
 **/
public class FavoriteServiceImpl implements IFavoriteService {
    private IFavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid), uid);
    }
}
