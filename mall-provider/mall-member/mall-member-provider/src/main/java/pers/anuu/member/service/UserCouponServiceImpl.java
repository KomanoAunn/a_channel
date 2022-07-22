package pers.anuu.member.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import pers.anuu.member.mapper.UserCouponMapper;
import pers.anuu.member.model.UserCoupon;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author KomanoAunn
 * @since 2022-07-22
 */
@DubboService
public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCoupon> implements IUserCouponService {

}
