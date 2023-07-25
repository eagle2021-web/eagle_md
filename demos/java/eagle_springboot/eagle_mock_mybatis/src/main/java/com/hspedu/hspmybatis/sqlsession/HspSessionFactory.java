package com.hspedu.hspmybatis.sqlsession;

public class HspSessionFactory {
    public static HspSqlSession openSession() {
        return new HspSqlSession();
    }
}
