package com.eagle.mysql.service.impl;

import com.eagle.mysql.mapper.HealthMapper;
import com.eagle.mysql.pojo.entity.Health;
import com.eagle.mysql.service.HealthService;
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
