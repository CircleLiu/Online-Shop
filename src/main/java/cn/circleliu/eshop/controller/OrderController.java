package cn.circleliu.eshop.controller;

import cn.circleliu.eshop.annotation.UserLoginToken;
import cn.circleliu.eshop.bean.Order;
import cn.circleliu.eshop.bean.Result;
import cn.circleliu.eshop.bean.User;
import cn.circleliu.eshop.mapper.UserMapper;
import cn.circleliu.eshop.service.OrderService;
import cn.circleliu.eshop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserMapper userMapper;

    @UserLoginToken
    @PostMapping(value = "/create")
    public Result createOrder(@RequestHeader(value = "token") String token, @RequestBody Order order) {
        String username = tokenService.getUsername(token);
        User user = userMapper.findUserByName(username);
        order.setUserId(user.getId());
        return orderService.createOrder(order);
    }

    @UserLoginToken
    @GetMapping(value = "/get")
    public Result findOrderByUser(@RequestHeader(value = "token") String token) {
        String username = tokenService.getUsername(token);
        User user = userMapper.findUserByName(username);
        return orderService.findOrderByUser(user);
    }
}
