package com.hspedu.config;

import lombok.Data;

import java.util.List;
@Data
public class MapperBean {
    private String interfaceName;
    private List<Function> functions;
    public String getInterfaceName() {
        return interfaceName;
    }
}
