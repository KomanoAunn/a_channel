package pers.anuu.core.response;

/**
 * @author pangxiong
 * @title: ErrorResponse
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1314:08
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(String msg){
        super(ResponseCodeEnum.FAIL.getCode(), msg);
    }
}
