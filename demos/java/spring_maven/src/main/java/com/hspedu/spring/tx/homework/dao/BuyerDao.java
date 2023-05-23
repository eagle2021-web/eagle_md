package com.hspedu.spring.tx.homework.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Repository
public class BuyerDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //从买家表中，扣除金额
    public void updateMoney(int id, double money) {
        String sql = "UPDATE buyer SET money = money - ? WHERE id = ?";
        jdbcTemplate.update(sql, money, id);
    }
}
