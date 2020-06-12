package cn.circleliu.eshop.mapper;

import cn.circleliu.eshop.bean.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemMapper {

    @Select(value = "select * from item where id=#{id}")
    Item findItemById(@Param("id") int id);
}
