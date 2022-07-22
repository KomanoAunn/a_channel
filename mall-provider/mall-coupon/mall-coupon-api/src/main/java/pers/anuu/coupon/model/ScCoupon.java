package pers.anuu.coupon.model;

import java.math.BigDecimal;
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
public class ScCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal amount;

    private String name;

    /**
     * 使用门槛
     */
    private BigDecimal limitAmount;

    private Date addTime;


}
