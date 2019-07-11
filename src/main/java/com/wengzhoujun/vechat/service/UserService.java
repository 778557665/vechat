package com.wengzhoujun.vechat.service;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.entity.User;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
public interface UserService {

    User save(User user);

    Result signUp(User user);

    User findByPhone(String phone);

    User findById(Long id);

    User findByKeyword(String keyword);
}
