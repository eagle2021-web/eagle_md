package com.eagle.mysql.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author eagle
 * @since 2022-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Health对象", description = "测试使用")
public class Health {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private Integer status;

    private String description;

    private Boolean deleted;


}
