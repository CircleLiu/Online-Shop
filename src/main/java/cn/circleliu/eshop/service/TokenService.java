package cn.circleliu.eshop.service;

import cn.circleliu.eshop.bean.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getAudience().get(0);
    }
}
