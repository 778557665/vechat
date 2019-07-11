package com.wengzhoujun.vechat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

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

    public enum StatusEnum {

        NORMAL((byte) 0, "正常"),
        BLACK((byte) 1, "拉黑");

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

    public UserRelationship() {
    }

    public UserRelationship(Long userId, Long friendUserId, Byte status) {
        this.userId = userId;
        this.friendUserId = friendUserId;
        this.status = status;
    }
}
