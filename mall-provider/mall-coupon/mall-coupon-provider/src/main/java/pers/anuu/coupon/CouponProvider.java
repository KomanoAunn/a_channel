package pers.anuu.coupon;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pangxiong
 * @title: CouponProvider
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1816:03
 */
@SpringBootApplication
@EnableAutoConfiguration
@DubboComponentScan
public class CouponProvider {
    public static void main(String[] args) {
        SpringApplication.run(CouponProvider.class, args);
    }
}
