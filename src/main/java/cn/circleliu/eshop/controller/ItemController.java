package cn.circleliu.eshop.controller;

import cn.circleliu.eshop.bean.Result;
import cn.circleliu.eshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/id")
    public Result findItemById(int id) {
        return itemService.findItemById(id);
    }
}
