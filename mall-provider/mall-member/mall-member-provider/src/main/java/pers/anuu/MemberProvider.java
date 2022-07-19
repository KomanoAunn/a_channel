package pers.anuu;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pangxiong
 * @title: pers.anuu.MemberProvider
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1415:01
 */
@SpringBootApplication
@EnableAutoConfiguration
@DubboComponentScan
public class MemberProvider {
    public static void main(String[] args) {
        SpringApplication.run(MemberProvider.class, args);
    }
}
