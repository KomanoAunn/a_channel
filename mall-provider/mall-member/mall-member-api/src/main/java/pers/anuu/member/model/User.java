package pers.anuu.member.model;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author KomanoAunn
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;

    private String headImg;

    private String loginCode;

    private String password;

    private Date lastLoginTime;

    private Date addTime;

    private Date updateTime;


}
