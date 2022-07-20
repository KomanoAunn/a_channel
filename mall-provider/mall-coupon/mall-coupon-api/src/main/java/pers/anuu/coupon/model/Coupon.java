package pers.anuu.coupon.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author pangxiong
 * @title: Coupon
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1815:54
 */
@Data
public class Coupon implements Serializable {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private Long userId;
}
