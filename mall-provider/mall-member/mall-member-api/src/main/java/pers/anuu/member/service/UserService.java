package pers.anuu.member.service;

/**
 * @author pangxiong
 * @title: UserService
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1410:11
 */
public interface UserService {
    String sayUserName(String username);

    void login(Long id);
}
