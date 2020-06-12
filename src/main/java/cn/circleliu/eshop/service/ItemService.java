package cn.circleliu.eshop.service;

import cn.circleliu.eshop.bean.Item;
import cn.circleliu.eshop.bean.Result;
import cn.circleliu.eshop.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ItemService {

    @Autowired
    ItemMapper itemMapper;

    public Result findItemById(int id) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            Item item = itemMapper.findItemById(id);

            result.setMsg("成功");
            result.setSuccess(true);
            result.setDetail(item);

        } catch (Exception e) {
            throw new RuntimeException("No such item");
        }

        return result;
    }
}
