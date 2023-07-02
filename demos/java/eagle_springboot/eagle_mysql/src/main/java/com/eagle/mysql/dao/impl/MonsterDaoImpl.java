package com.eagle.mysql.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eagle.mysql.dao.MonsterDao;
import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.pojo.entity.Monster;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MonsterDaoImpl implements MonsterDao {
    @Resource
    private MonsterMapper monsterMapper;

    @Override
    public Monster selectMonsterByIdAndAge(Monster monster) {
        QueryWrapper<Monster> qw = new QueryWrapper<>();
        qw.eq("id", monster.getId())
                        .eq("age", monster.getAge());
        return monsterMapper.selectOne(qw);
    }
}
