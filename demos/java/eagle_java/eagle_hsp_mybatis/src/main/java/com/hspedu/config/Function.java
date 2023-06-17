package com.hspedu.config;

import javax.print.DocFlavor;

public class Function {
    private String sqlType; // sql类型， 比如select, insert, update, delete
    private String funcName;
    private String sql; //执行sql语句
    private Object resultType; //返回类型
    private String parameterType;
}
