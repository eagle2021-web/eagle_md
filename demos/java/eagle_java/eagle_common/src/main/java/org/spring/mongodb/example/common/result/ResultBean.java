package org.spring.mongodb.example.common.result;

import lombok.Data;

@Data
public class ResultBean<T> {
    public static Integer SUCCESS = 0;
    public static Integer FAILURE = 1;
    private Integer code = SUCCESS;
    private String message;
    private T data;
    private ResultBean(){}
    public static <T> ResultBean<T> getInstance(T data) {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setData(data);
        return resultBean;
    }
}
