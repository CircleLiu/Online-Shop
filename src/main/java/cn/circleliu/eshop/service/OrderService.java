package cn.circleliu.eshop.service;

import cn.circleliu.eshop.bean.Item;
import cn.circleliu.eshop.bean.Order;
import cn.circleliu.eshop.bean.Result;
import cn.circleliu.eshop.bean.User;
import cn.circleliu.eshop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public Result createOrder(Order order) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            order.setTime(new Date());
            orderMapper.createOrder(order);

            result.setMsg("成功");
            result.setSuccess(true);
            result.setDetail(order);

        } catch (Exception e) {
            throw new RuntimeException("创建订单失败");
        }

        return result;
    }

    public Result findOrderByUser(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            ArrayList<Order> orders = orderMapper.findOrderByUser(user);

            result.setMsg("成功");
            result.setSuccess(true);
            result.setDetail(orders);

        } catch (Exception e) {
            throw new RuntimeException("无订单");
        }

        return result;
    }
}
