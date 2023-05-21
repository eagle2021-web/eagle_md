package com.hspedu.spring.tx.homework.service;

import com.hspedu.spring.tx.homework.dao.BuyerDao;
import com.hspedu.spring.tx.homework.dao.Goods2Dao;
import com.hspedu.spring.tx.homework.dao.SellerDao;
import com.hspedu.spring.tx.homework.dao.TaobaoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service
public class Goods2Service {

    @Resource
    private BuyerDao buyerDao;
    @Resource
    private SellerDao sellerDao;
    @Resource
    private Goods2Dao goods2Dao;
    @Resource
    private TaobaoDao taobaoDao;

    //用户的购买商品的行为会操作多张表，视为一个事务进行管理

    //@Transactional
    public void shopping(int goodsId, int buyerId, int sellerId, int amount) {

        Double price = goods2Dao.queryPriceById(goodsId);
        //花费多少钱
        double money = price * amount;
        buyerDao.updateMoney(buyerId, money);
        //将成交额的90% 转入卖家
        sellerDao.updateMoney(sellerId, money * 0.9);
        //将成交额的10% 转入淘宝账号
        taobaoDao.updateMoney(1, money * 0.1);
        //减去商品的库存
        goods2Dao.updateAmount(goodsId,amount);

    }

}
