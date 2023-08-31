package com.eagle.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.pojo.entity.Monster;
import com.eagle.mysql.service.IMonsterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-07-02
 */
@Service
public class MonsterServiceImpl extends ServiceImpl<MonsterMapper, Monster> implements IMonsterService {
    @Resource
    private MonsterMapper monsterMapper;

//    @Override
//    public void deleteById(Integer id) {
//        monsterMapper.deleteById()
//    }
}
