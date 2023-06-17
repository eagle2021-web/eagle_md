package com.hspedu.hspmybatis.sqlsession;

import java.lang.reflect.Proxy;

public class HspSqlSession {
    private Executor executor = new HspExecutor();
    private HspConfiguration hspConfiguration = new HspConfiguration();
    // 编写方法SelectOne 返回一条记录-对象
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    public <T> T getMapper(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{cls},
                new HspMapperProxy(hspConfiguration, this, cls)
        );
    }

}
