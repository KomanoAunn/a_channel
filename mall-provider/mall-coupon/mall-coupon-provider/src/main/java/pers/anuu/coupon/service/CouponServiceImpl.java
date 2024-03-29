package pers.anuu.coupon.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import pers.anuu.coupon.model.ScCoupon;

import javax.annotation.Resource;

/**
 * @author pangxiong
 * @title: CouponServiceImpl
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1815:56
 */
@DubboService
public class CouponServiceImpl implements CouponService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendCoupon(ScCoupon coupon) {
        jdbcTemplate.execute(String.format("INSERT INTO coupon(`amount`,`name`,`user_id`) " +
                "VALUES('%s','%s','%s')", coupon.getAmount(), coupon.getName(), null));
    }
}
