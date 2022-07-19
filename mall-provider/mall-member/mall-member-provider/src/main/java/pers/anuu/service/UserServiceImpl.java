package pers.anuu.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import pers.anuu.model.Coupon;
import pers.anuu.model.User;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author pangxiong
 * @title: UserServiceImpl
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1410:40
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @DubboReference
    private CouponService couponService;

    @Override
    public String sayUserName(String username) {
        System.out.println(username);
        return "Hello:" + username;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void login(Long id) {
        Coupon coupon = new Coupon();
        coupon.setName("测试券");
        coupon.setAmount(BigDecimal.TEN);
        coupon.setUserId(id);
        couponService.sendCoupon(coupon);

        User user = new User();
        jdbcTemplate.update("UPDATE user SET last_login_time=NOW() WHERE id=" + id);
        if (id == 1) {
            throw new RuntimeException("!!!!!!!!TEST MUST EXCEPTION!!!!!!!");
        }
    }
}
