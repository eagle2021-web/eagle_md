package com.hspedu.hspmybatis.sqlsession;

public class HspSqlSession {
    private Executor executor = new HspExecutor();
    private HspConfiguration hspConfiguration = new HspConfiguration();
    // 编写方法SelectOne 返回一条记录-对象
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }
}
