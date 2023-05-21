package com.hspedu.spring.web;

import com.hspedu.spring.service.OrderService;

/**
 * @author 韩顺平
 * @version 1.0
 * Servlet就是Controller
 */
public class OrderAction {
    //属性OrderService
    private OrderService orderService;

    //getter
    public OrderService getOrderService() {
        return orderService;
    }

    //setter
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
