package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ISellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/11 22:04
 * @Description: TODO
 **/
public class SellerDaoImpl implements ISellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";

        return template.queryForObject(sql,
                new BeanPropertyRowMapper<Seller>(Seller.class), sid);
    }
}
