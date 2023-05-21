package com.hspedu.spring.tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Repository //将 GoodsDao-对象 注入到spring容器
public class GoodsDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据商品id,返回对应的价格
     * @param id
     * @return
     */
    public Float queryPriceById(Integer id) {
        String sql = "SELECT price From goods Where goods_id=?";
        Float price = jdbcTemplate.queryForObject(sql, Float.class, id);
        return price;
    }

    /**
     * 修改用户的余额 [减少用户余额]
     * @param user_id
     * @param money
     */
    public void updateBalance(Integer user_id, Float money) {
        String sql = "UPDATE user_account SET money=money-? Where user_id=?";
        jdbcTemplate.update(sql, money, user_id);
    }

    /**
     * 修改商品库存 [减少]
     * @param goods_id
     * @param amount
     */
    public void updateAmount(Integer goods_id, int amount){
        String sql = "UPDATE goods_amount SET goods_num=goods_num-? Where goods_id=?";
        jdbcTemplate.update(sql, amount , goods_id);
    }


    /**
     * 根据商品id,返回对应的价格
     * @param id
     * @return
     */
    public Float queryPriceById2(Integer id) {
        String sql = "SELECT price From goods Where goods_id=?";
        Float price = jdbcTemplate.queryForObject(sql, Float.class, id);
        return price;
    }

    /**
     * 修改用户的余额 [减少用户余额]
     * @param user_id
     * @param money
     */
    public void updateBalance2(Integer user_id, Float money) {
        String sql = "UPDATE user_account SET money=money-? Where user_id=?";
        jdbcTemplate.update(sql, money, user_id);
    }

    /**
     * 修改商品库存 [减少]
     * @param goods_id
     * @param amount
     */
    public void updateAmount2(Integer goods_id, int amount){
        String sql = "UPDATE goods_amount SET goods_num=goods_num-? Where goods_id=?";
        jdbcTemplate.update(sql, amount , goods_id);
    }
}
