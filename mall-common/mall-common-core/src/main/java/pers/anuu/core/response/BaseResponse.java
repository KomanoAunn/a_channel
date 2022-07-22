package pers.anuu.core.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pangxiong
 * @title: BaseResponse
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1311:07
 */
@Data
@NoArgsConstructor
public class BaseResponse implements Serializable {
    private String code;
    private String msg;

    BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BaseResponse successResponse() {
        return new SuccessResponse();
    }

    public static BaseResponse errorResponse(String msg) {
        return new ErrorResponse(msg);
    }
}
