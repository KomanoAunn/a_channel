package pers.anuu.coupon.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import pers.anuu.coupon.mapper.ScCouponMapper;
import pers.anuu.coupon.model.ScCoupon;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author KomanoAunn
 * @since 2022-07-22
 */
@DubboService
public class ScCouponServiceImpl extends ServiceImpl<ScCouponMapper, ScCoupon> implements IScCouponService {

}
