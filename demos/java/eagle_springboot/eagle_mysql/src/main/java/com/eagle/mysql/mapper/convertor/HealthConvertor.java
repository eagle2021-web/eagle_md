package com.eagle.mysql.mapper.convertor;

import com.eagle.mysql.pojo.dto.HealthDTO;
import com.eagle.mysql.pojo.entity.Health;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthConvertor {
    HealthDTO from(Health health);
}
