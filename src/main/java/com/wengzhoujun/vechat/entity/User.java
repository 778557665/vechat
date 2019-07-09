package com.wengzhoujun.vechat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }
}
