package pers.anuu.member.model;

import lombok.Data;

import java.util.Date;

/**
 * @author pangxiong
 * @title: User
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1815:08
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Date lastLoginTime;
}
