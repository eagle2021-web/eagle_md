package com.hspedu.spring.tx.service;

import com.hspedu.spring.tx.dao.GoodsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service //将 GoodsService对象注入到spring容器
public class GoodsService {

    //定义属性GoodsDao
    @Resource
    private GoodsDao goodsDao;

    //编写一个方法，完成用户购买商品的业务, 这里主要是讲解事务管理

    /**
     * @param userId  用户id
     * @param goodsId 商品id
     * @param amount  购买数量
     */
    public void buyGoods(int userId, int goodsId, int amount) {

        //输出购买的相关信息
        System.out.println("用户购买信息 userId=" + userId
                + " goodsId=" + goodsId + " 购买数量=" + amount);

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById(userId);
        //2. 减少用户的余额
        goodsDao.updateBalance(userId, price * amount);
        //3. 减少库存量
        goodsDao.updateAmount(goodsId, amount);

        System.out.println("用户购买成功~");

    }

    /**
     * @param userId
     * @param goodsId
     * @param amount
     * @Transactional 注解解读
     * 1. 使用@Transactional 可以进行声明式事务控制
     * 2. 即将标识的方法中的，对数据库的操作作为一个事务管理
     * 3. @Transactional 底层使用的仍然是AOP机制
     * 4. 底层是使用动态代理对象来调用buyGoodsByTx
     * 5. 在执行buyGoodsByTx() 方法 先调用 事务管理器的 doBegin() , 调用 buyGoodsByTx()
     * 如果执行没有发生异常，则调用 事务管理器的 doCommit(), 如果发生异常 调用事务管理器的 doRollback()
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyGoodsByTx(int userId, int goodsId, int amount) {


        //输出购买的相关信息
        System.out.println("用户购买信息 userId=" + userId
                + " goodsId=" + goodsId + " 购买数量=" + amount);

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById(userId);
        //2. 减少用户的余额
        goodsDao.updateBalance(userId, price * amount);
        //3. 减少库存量
        goodsDao.updateAmount(goodsId, amount);

        System.out.println("用户购买成功~");

    }


    /**
     * 这个方法是第二套进行商品购买的方法
     *
     * @param userId
     * @param goodsId
     * @param amount
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void buyGoodsByTx2(int userId, int goodsId, int amount) {


        //输出购买的相关信息
        System.out.println("用户购买信息 userId=" + userId
                + " goodsId=" + goodsId + " 购买数量=" + amount);

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById2(userId);
        //2. 减少用户的余额
        goodsDao.updateBalance2(userId, price * amount);
        //3. 减少库存量
        goodsDao.updateAmount2(goodsId, amount);

        System.out.println("用户购买成功~");

    }

    /**
     * 老师说明
     * 1. 在默认情况下 声明式事务的隔离级别是 REPEATABLE_READ
     * 2. 我们将buyGoodsByTxISOLATION的隔离级别设置为 Isolation.READ_COMMITTED
     * ,表示只要是提交的数据，在当前事务是可以读取到最新数据
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void buyGoodsByTxISOLATION() {

        //查询两次商品的价格
        Float price = goodsDao.queryPriceById(1);
        System.out.println("第一次查询的price= " + price);

        Float price2 = goodsDao.queryPriceById(1);
        System.out.println("第二次查询的price= " + price2);

    }

    /**
     * 老韩解读
     * 1. @Transactional(timeout = 2)
     * 2. timeout = 2 表示 buyGoodsByTxTimeout 如果执行时间超过了2秒
     *    , 该事务就进行回滚.
     * 3. 如果你没有设置 timeout, 默认是 -1，表示使用事务的默认超时时间,
     *    或者不支持
     */
    @Transactional(timeout = 2)
    public void buyGoodsByTxTimeout(int userId, int goodsId, int amount) {

        //输出购买的相关信息
        System.out.println("用户购买信息 userId=" + userId
                + " goodsId=" + goodsId + " 购买数量=" + amount);

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById2(userId);
        //2. 减少用户的余额
        goodsDao.updateBalance2(userId, price * amount);

        //模拟超时
        System.out.println("=====超时开始4s=====");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====超时结束4s=====");

        //3. 减少库存量
        goodsDao.updateAmount2(goodsId, amount);

        System.out.println("用户购买成功~");


    }

}
