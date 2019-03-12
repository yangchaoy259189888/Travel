package cn.itcast.travel.service;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/12 9:42
 * @Description: TODO
 **/
public interface IFavoriteService {
    boolean isFavorite(String rid, int uid);

    void add(String rid, int uid);
}
