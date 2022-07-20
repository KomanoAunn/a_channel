package pers.anuu.core.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pangxiong
 * @title: SuccessResponse
 * @projectName a_channel
 * @description: TODO
 * @date 2022/7/1314:08
 */
@Data
public class SuccessResponse extends BaseResponse {

    private Map<String, Object> data = new HashMap<String, Object>();

    SuccessResponse() {
        super("100", "");
    }


    SuccessResponse(String key, Object data) {
        super("100", "");
        this.data.put(key, data);
    }
}
