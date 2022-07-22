package pers.anuu.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.anuu.member.model.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author KomanoAunn
 * @since 2022-07-22
 */
public interface IUserService extends IService<User> {

    String sayUserName(String username);

    void login(Long id);
}
