package com.hspedu.spring.tx;

import com.hspedu.spring.tx.dao.GoodsDao;
import com.hspedu.spring.tx.service.GoodsService;
import com.hspedu.spring.tx.service.MultiplyService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class TxTest {

    @Test
    public void queryPriceByIdTest() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);

        Float price = goodsDao.queryPriceById(1);
        System.out.println("id=100 的price=" + price);
    }

    @Test
    public void updateBalance() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        goodsDao.updateBalance(1, 1.0F);
        System.out.println("减少用户余额成功~");

    }

    @Test
    public void updateAmount() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        goodsDao.updateAmount(1, 1);
        System.out.println("减少库存成功...");
    }

    //测试用户购买商品业务
    @Test
    public void buyGoodsTest() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoods(1, 1, 1);
    }

    //测试用户购买商品业务
    @Test
    public void buyGoodsByTxTest() {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoodsByTx(1, 1, 1);//这里我们调用的是进行了事务声明的方法
    }

    //测试事务的传播机制
    @Test
    public void multiBuyGoodsByTxTest() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");

        MultiplyService multiplyService = ioc.getBean(MultiplyService.class);

        multiplyService.multiBuyGoodsByTx();
    }

    //测试声明式事务的隔离级别
    @Test
    public void buyGoodsByTxISOLATIONTest() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);

        goodsService.buyGoodsByTxISOLATION();
    }

    //测试timeout 属性
    @Test
    public void buyGoodsByTxTimeoutTest() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);

        goodsService.buyGoodsByTxTimeout(1,1,1);
    }
}
