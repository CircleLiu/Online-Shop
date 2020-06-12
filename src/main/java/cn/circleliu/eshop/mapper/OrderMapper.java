package cn.circleliu.eshop.mapper;

import cn.circleliu.eshop.bean.Order;
import cn.circleliu.eshop.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface OrderMapper {

    @Insert("insert into user_order values(#{orderId}, #{userId}, #{time, jdbcType=TIMESTAMP}, #{total})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId", keyColumn = "order_id")
    void createOrder(Order order);

    @Select("select * from user_order where user_id=#{id}")
    ArrayList<Order> findOrderByUser(User user);
}
