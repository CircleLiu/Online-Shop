package cn.circleliu.eshop.mapper;

import cn.circleliu.eshop.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select(value = "select * from user u where u.username=#{username}")
    User findUserByName(@Param("username") String username);

    @Insert("insert into user values(#{id}, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void regist(User user);

    @Select("select u.id from user u where u.username=#{username} and password = #{password}")
    Integer login(User user);
}
