package com.moikiitos.service.result;


import lombok.Data;

/**
 *function description
 * @author xie kuan
 * @Description return to web package front。
 * @date 02/24/24
*/
@Data
public class WebResult extends BaseResult{

    private static final long serialVersionUID = 1L;
    public static  Integer RESULT_FAIL = 0;
    public static  Integer RESULT_SUCCESS = 1;

    public WebResult(Integer code, String message) {
        super(code, message);
    }

    public WebResult(Integer code, String message, Object object) {
        super(code, message, object);
    }


    @Override
    public String toString() {


        return super.toString();
    }
}
