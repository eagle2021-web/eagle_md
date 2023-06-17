package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.config.Function;
import com.hspedu.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class HspMapperProxy implements InvocationHandler {
    private HspSqlSession hspSqlSession;
    private String mapperFile;
    private HspConfiguration hspConfiguration;

    public HspMapperProxy(HspConfiguration hspConfiguration,
                          HspSqlSession hspSqlSession,
                          Class cls) {
        this.hspConfiguration = hspConfiguration;
        this.hspSqlSession = hspSqlSession;
        this.mapperFile = cls.getSimpleName() + ".xml";
    }

    /**
     * 当执行Mapper接口的代理对象方法时，会执行到invoke方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return 接口的代理对象
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = hspConfiguration.readMapper(this.mapperFile);
        System.out.println(this.mapperFile);
        System.out.println("method.getDeclaringClass().getName() = " + method.getDeclaringClass().getName());
        System.out.println(mapperBean.getInterfaceName());
        if (!method.getDeclaringClass().getName()
                .equals(mapperBean.getInterfaceName())) {
            // 取出mapperBean的functions
            return null;
        }
        List<Function> functions = mapperBean.getFunctions();
        if (null != functions && !functions.isEmpty()) {
            for (Function function : functions) {
                // 当前执行的方法和function.getFunction
                if (method.getName().equals(function.getFuncName())) {
                    System.out.println("method " + method.getName());
                    if ("select".equalsIgnoreCase(function.getSqlType())) {
                        return hspSqlSession.selectOne(function.getSql(), String.valueOf(args[0]));
                    }
                }
            }
        }
        return null;
    }
}
;