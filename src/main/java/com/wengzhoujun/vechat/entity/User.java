package com.wengzhoujun.vechat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
@Data
@Entity
@Table(name = "vc_us_user")
@ApiModel(value = "用户")
public class User {

    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(generator = "id_generator")
    @GenericGenerator(name = "id_generator", strategy = "redis_id")
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "微信号 全局唯一")
    @Column(unique = true)
    private String chatMark;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "加密")
    private String salt;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "状态")
    private Byte status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "注册ip")
    private String regIp;

    @ApiModelProperty(value = "地区")
    private String region;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "二维码")
    private String qrCode;

    public User() {
    }

    public User(String phone, String nickname, Byte status, Date createTime, String regIp) {
        this.phone = phone;
        this.nickname = nickname;
        this.status = status;
        this.createTime = createTime;
        this.regIp = regIp;
    }

    public enum StatusEnum {

        NORMAL((byte) 0, "正常"),
        FREEZING((byte) 1, "冻结");

        private static Map<Byte, StatusEnum> map = new HashMap<>();

        static {
            for (StatusEnum statusEnum : StatusEnum.values()) {
                map.put(statusEnum.getCode(), statusEnum);
            }
        }

        private Byte code;
        private String desc;

        StatusEnum(Byte code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static StatusEnum getEnumByCode(Byte code) {
            return map.get(code);
        }

        public Byte getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

}
