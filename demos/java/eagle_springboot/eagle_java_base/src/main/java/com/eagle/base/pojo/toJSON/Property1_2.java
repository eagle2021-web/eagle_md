package com.eagle.base.pojo.toJSON;

import com.alibaba.fastjson.annotation.JSONField;

public class Property1_2 implements PropertyAbs{
    @JSONField(name = "key")
    private String name;
    @JSONField(name = "value")
    private String value;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    public void setKey(String key) {
        this.name = key;
    }
    public String getKey(){
        return this.name;
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
    public String toString() {
        return "Property1_2{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
