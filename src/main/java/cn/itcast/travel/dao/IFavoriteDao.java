package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/12 9:41
 * @Description: TODO
 **/
public interface IFavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    void add(int rid, int uid);
}
