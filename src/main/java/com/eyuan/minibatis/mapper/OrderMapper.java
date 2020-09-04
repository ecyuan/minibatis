package com.eyuan.minibatis.mapper;

import com.eyuan.minibatis.annotation.Select;

public interface OrderMapper {
    @Select("select id from my_order where order_sn = '#'")
    String getOrderId(String orderSn);
}
