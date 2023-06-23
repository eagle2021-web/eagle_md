package org.spring.mongodb.example.mysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.spring.mongodb.example.mysql.pojo.entity.Health;
import org.spring.mongodb.example.mysql.mapper.HealthMapper;
import org.spring.mongodb.example.mysql.service.HealthService;
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
//        String fieldName = getFieldName(Health::getName);
        QueryWrapper<Health> qw = new QueryWrapper<Health>().eq("id", id);
        return healthMapper.selectOne(qw);
    }
}
