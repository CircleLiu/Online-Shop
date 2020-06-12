package cn.circleliu.eshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private int orderId;
    private int userId;
    private Date time;
    private Double total;
}
