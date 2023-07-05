package com.eagle.base.pojo.toJSON;

import com.alibaba.fastjson.annotation.JSONField;

public class Property1_0Beta implements PropertyAbs{
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "value")
    private String value;
    @Override
    public String getName() {
        System.out.println(111);
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String _value) {
        value = _value;
    }

    @Override
    public void setKey(String key) {

    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String toString() {
        return "Property1_0Beta{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
