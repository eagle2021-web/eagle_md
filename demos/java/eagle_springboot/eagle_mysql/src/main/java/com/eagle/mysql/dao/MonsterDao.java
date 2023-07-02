package com.eagle.mysql.dao;

import com.eagle.mysql.pojo.entity.Monster;

import java.util.List;

public interface MonsterDao {
    Monster selectMonsterByIdAndAge(Monster monster);
}
