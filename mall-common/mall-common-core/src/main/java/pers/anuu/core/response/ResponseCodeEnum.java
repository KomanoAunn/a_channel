package pers.anuu.core.response;

import lombok.Getter;

/**
 * @author pangxiong
 * @title: ResponseCodeEnum
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/2215:48
 */
@Getter
public enum ResponseCodeEnum {

    SUCCESS("000", "成功"),
    FAIL("100","失败"),
    PARAMS_INVALID("101","参数校验失败"),
    TOKEN_INVALID("102","token无效，未登陆"),
    API_NOT_FOUND("103","接口不存在"),
    AUTH_INVALID("104","无权限"),
    ERROR("500","服务器错误");

    private String code;
    private String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
