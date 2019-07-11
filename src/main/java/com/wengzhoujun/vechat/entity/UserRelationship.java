package com.wengzhoujun.vechat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 2019/7/11.
 *
 * @author WengZhoujun
 */
@Data
@Entity
@Table(name = "vc_us_user_relationship")
@ApiModel(value = "用户关系")
public class UserRelationship {

    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(generator = "id_generator")
    @GenericGenerator(name = "id_generator", strategy = "redis_id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "朋友id")
    private Long friendUserId;

    @ApiModelProperty(value = "关系状态")
    private Byte status;

    public UserRelationship() {
    }


}
