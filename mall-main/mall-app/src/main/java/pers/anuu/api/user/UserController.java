package pers.anuu.api.user;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.anuu.service.UserService;
import response.BaseResponse;

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
        return null;
    }

    @RequestMapping("/user/login")
    public BaseResponse login(Long id) {
        userService.login(id);
        return null;
    }
}
