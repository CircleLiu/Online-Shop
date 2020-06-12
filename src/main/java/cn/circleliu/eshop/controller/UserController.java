package cn.circleliu.eshop.controller;


import cn.circleliu.eshop.annotation.UserLoginToken;
import cn.circleliu.eshop.bean.Result;
import cn.circleliu.eshop.bean.User;
import cn.circleliu.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/regist")
    public Result regist(@RequestBody User user) {
        return userService.regist(user);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @UserLoginToken
    @GetMapping(value = "/info")
    public Result getUserInfo(@RequestHeader(value = "token") String token) {
        return userService.getUserInfo(token);
    }
}
