package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.IRouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/11 21:55
 * @Description: TODO
 **/
public class RouteImgDaoImpl implements IRouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";

        return template.query(sql,
                new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
    }
}
