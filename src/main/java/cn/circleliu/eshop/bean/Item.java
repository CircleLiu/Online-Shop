package cn.circleliu.eshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private int id;
    private String name;
    private String img;
    private String description;
    private double price;
}
