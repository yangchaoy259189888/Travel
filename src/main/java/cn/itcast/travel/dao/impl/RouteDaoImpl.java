package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/5 21:11
 * @Description: TODO
 **/
public class RouteDaoImpl implements IRouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
//        String sql = "select count(*) from tab_route where cid = ?";
        String sql = "select count(*) from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ?");
            params.add("%" + rname + "%");
        }

        sql = sb.toString();

        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid = ? limit ?, ?";
        String sql = "select * from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ?");
            params.add("%" + rname + "%");
        }
        sb.append(" limit ?, ?");
        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,
                new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,
                new BeanPropertyRowMapper<Route>(Route.class), rid);
    }
}
