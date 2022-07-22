package pers.anuu.app.error;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.anuu.core.exception.BusinessException;
import pers.anuu.core.response.BaseResponse;

@ControllerAdvice
public class GlobalErrorControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public BaseResponse errorHandler(Throwable ex) {
        StringBuilder errorStr = new StringBuilder();
        if (ex instanceof BusinessException) {
            //业务异常
            errorStr.append("业务异常：").append(ex.getMessage());
        } else {
            //系统异常
            errorStr.append("系统错误：").append(ex.getMessage());
        }
        return BaseResponse.errorResponse(errorStr.toString());
    }
}
