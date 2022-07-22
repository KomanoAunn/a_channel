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
public class UserCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer couponId;

    private Integer userId;

    private Date addTime;


}
