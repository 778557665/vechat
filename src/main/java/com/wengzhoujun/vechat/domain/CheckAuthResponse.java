package com.wengzhoujun.vechat.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
@Data
public class CheckAuthResponse implements Serializable {

    private static final long serialVersionUID = -3449685567541470264L;

    private String code;
    private String msg;
    private String uid;

    @Override
    public String toString() {
        return "CheckAuthResponse{" +
                "uid='" + uid + '\'' +
                "} " + super.toString();
    }
}
