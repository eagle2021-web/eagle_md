package com.eagle.base.pojo.toJSON;

import com.alibaba.fastjson.annotation.JSONField;

public interface PropertyAbs {
    public abstract String getName();
    public abstract void setName(String _name);
    public abstract String getValue();
    public abstract void setValue(String _value);
    public void setKey(String key);
    public String getKey();
}


