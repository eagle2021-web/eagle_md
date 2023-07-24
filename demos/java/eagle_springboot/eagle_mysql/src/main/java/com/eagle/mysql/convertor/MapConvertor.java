package com.eagle.mysql.convertor;

import com.eagle.mysql.pojo.dto.HealthDTO;
import com.eagle.mysql.pojo.entity.A;
import com.eagle.mysql.pojo.entity.Health;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapConvertor {
//    MapConvertor INSTANCE = Mappers.getMapper(MapConvertor.class);

    HealthDTO from(A a);
}
