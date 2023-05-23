package com.hspedu.spring.tx.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Service
public class MultiplyService {
    @Resource
    private GoodsService goodsService;


    /**
     * 老师解读
     * 1. multiBuyGoodsByTx 这个方法 有两次购买商品操作
     * 2. buyGoodsByTx 和 buyGoodsByTx2 都是声明式事务
     * 3. 当前buyGoodsByTx 和 buyGoodsByTx2 使用的传播属性是默认的 REQUIRED [这个含义老师前面讲过了
     *    即会当做一个整体事务进行管理 , 比如buyGoodsByTx方法成功，但是buyGoodsByTx2() 失败，会造成 整个事务的回滚
     *    即会回滚buyGoodsByTx]
     *
     * 4. 如果 buyGoodsByTx 和 buyGoodsByTx2 事务传播属性修改成 REQUIRES_NEW
     *    , 这时两个方法的事务是独立的，也就是如果 buyGoodsByTx成功 buyGoodsByTx2失败
     *    , 不会造成 buyGoodsByTx回滚.
     *
     */
    @Transactional
    public void multiBuyGoodsByTx() {

        goodsService.buyGoodsByTx(1, 1, 1);
        goodsService.buyGoodsByTx2(1, 1, 1);
    }
}
