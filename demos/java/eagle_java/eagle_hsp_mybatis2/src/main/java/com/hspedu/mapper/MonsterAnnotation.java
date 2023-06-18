package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import org.apache.ibatis.annotations.Select;

public interface MonsterAnnotation {
    @Select("SELECT * FROM `monster` WHERE id = #{id}")
    Monster getMonsterById(Integer id);
}
