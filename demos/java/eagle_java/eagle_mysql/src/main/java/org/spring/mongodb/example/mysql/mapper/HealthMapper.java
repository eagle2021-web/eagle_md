package org.spring.mongodb.example.mysql.mapper;

import org.spring.mongodb.example.mysql.pojo.entity.Health;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eagle
 * @since 2022-11-15
 */
@Mapper
public interface HealthMapper extends BaseMapper<Health> {

}
