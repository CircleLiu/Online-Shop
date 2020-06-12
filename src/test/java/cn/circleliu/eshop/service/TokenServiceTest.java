package cn.circleliu.eshop.service;

import cn.circleliu.eshop.bean.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    static User user;
    static TokenService tokenService;

    @BeforeAll
    static void initUser() {
        user = new User(1, "abc", "123123");
        tokenService = new TokenService();
    }

    @Test
    void getToken() {
        String token = tokenService.getToken(user);
        System.out.println(token);
    }

    @Test
    void getUsername() {
        String token = tokenService.getToken(user);
        System.out.println(token);
        String username = tokenService.getUsername(token);
        System.out.println(username);
    }
}