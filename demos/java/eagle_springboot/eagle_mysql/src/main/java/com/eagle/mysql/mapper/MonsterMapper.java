package com.eagle.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eagle.mysql.pojo.entity.Monster;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-07-02
 */
public interface MonsterMapper extends BaseMapper<Monster> {
    //添加monster
    void addMonster2(Monster monster);
    List<Monster> findMonsterByAge(Integer age);
}
