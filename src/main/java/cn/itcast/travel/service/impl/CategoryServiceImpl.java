package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.ICategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.ICategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/2/12 18:09
 * @Description: TODO
 **/
public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从Redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedset排序查询
//        Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.2查询sortedset的分数cid和值cname
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //2.判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0) {
            //3.如果为空，需要从数据库查，并将数据存到redis
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        } else {
            //4.如果不为空，则直接返回
            //将set的数据存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCid((int) tuple.getScore());
                category.setCname(tuple.getElement());
                cs.add(category);
            }
        }

        return cs;
    }
}
