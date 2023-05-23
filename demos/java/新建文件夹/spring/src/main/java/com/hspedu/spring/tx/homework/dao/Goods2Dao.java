package com.hspedu.spring.tx.homework.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Repository
public class Goods2Dao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //根据商品id,返回价格
    //返回单行单列
    public Double queryPriceById(int goodsId) {
        String sql = "SELECT price FROM goods2 WHERE goods_id=?";
        Double price = jdbcTemplate.queryForObject(sql, Double.class, goodsId);
        return  price;
    }
    //根据商品id ,减去库存
    public void updateAmount(int goodsId, int amount) {
        String sql = "UPDATE goods2 SET amount = amount - ? WHERE goods_id = ?";
        jdbcTemplate.update(sql,amount,goodsId);
    }

}
