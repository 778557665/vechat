package com.wengzhoujun.vechat.domain;


import java.util.HashMap;
import java.util.Objects;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
public class JwtClaims extends HashMap<String, Object> {

    String ID = "uid";
    String NICKNAME = "nickname";

    public JwtClaims(Long id, String nickname) {
        super(2);
        this.put(ID, id);
        this.put(NICKNAME, nickname);
    }

    @Override
    public JwtClaims put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 重写hashCode方法
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this);
    }
}
