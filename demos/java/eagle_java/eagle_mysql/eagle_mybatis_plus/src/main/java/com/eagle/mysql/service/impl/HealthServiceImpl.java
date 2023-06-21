package com.eagle.mysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eagle.mysql.pojo.entity.Health;
import com.eagle.mysql.mapper.HealthMapper;
import com.eagle.mysql.service.HealthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2022-11-15
 */
@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {
    @Resource
    private HealthMapper healthMapper;

    @Override
    public Health getHealthById(Integer id) {
//        LambdaQueryWrapper<Health> qw = Wrappers.<Health>lambdaQuery()
//                .select(Health::getId, Health::getName)
//                .eq(Health::getId, id);
        QueryWrapper<Health> qw = new QueryWrapper<Health>().eq("id", id);
        return healthMapper.selectOne(qw);
    }
}
