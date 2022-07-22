package pers.anuu.member.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import pers.anuu.coupon.service.IScCouponService;
import pers.anuu.member.mapper.UserMapper;
import pers.anuu.member.model.User;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author KomanoAunn
 * @since 2022-07-22
 */
@DubboService
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @DubboReference
    private IScCouponService couponService;
    @Resource
    private IUserCouponService userCouponService;

    @Override
    public String sayUserName(String username) {
        System.out.println(username);
        return "Hello:" + username;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void login(Long id) {

    }
}
