package org.spring.mongodb.example.mysql.service.impl;

import org.spring.mongodb.example.mysql.pojo.entity.Health;
import org.spring.mongodb.example.mysql.mapper.HealthMapper;
import org.spring.mongodb.example.mysql.service.HealthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
