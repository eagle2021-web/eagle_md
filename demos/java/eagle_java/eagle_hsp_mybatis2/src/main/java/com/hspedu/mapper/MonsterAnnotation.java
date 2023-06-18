package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MonsterAnnotation {
    @Select("SELECT * FROM `monster` WHERE id = #{id}")
    Monster getMonsterById(Integer id);

    /**
     * 1.useGeneratedKeys = true返回自增的值
     * 2.keyProperty = "id" 自增值对应的对象属性
     * 3.keyColumn = "id" 自增值对应的表的字段
     * 4.keyProperty和keyColumn值相同时后者可以不写
     * @param monster 对象
     */
    @Insert("INSERT INTO `monster`\n" +
            "(`age`, `birthday`, `email`, `gender`, `name`, `salary`)\n" +
            "VALUES (#{age}, #{birthday}, #{email}, #{gender}, #{name}, #{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addMonster(Monster monster);
}
