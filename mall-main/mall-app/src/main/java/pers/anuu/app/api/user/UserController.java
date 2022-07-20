package pers.anuu.app.api.user;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.anuu.core.response.BaseResponse;
import pers.anuu.member.service.UserService;

/**
 * @author pangxiong
 * @title: UserController
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1410:16
 */
@RestController
public class UserController {
    @DubboReference //(url = "dubbo://127.0.0.1:2222")
    private UserService userService;

    @RequestMapping("/username/say")
    public BaseResponse userName(String userName) {
        userService.sayUserName(userName);
        return BaseResponse.successResponse();
    }

    @RequestMapping("/user/login")
    public BaseResponse login(Long id) {
        userService.login(id);
        return BaseResponse.successResponse();
    }
}
