package com.wengzhoujun.vechat.repository;


import com.wengzhoujun.vechat.entity.User;

/**
 * Created on 2019/6/19.
 *
 * @author WengZhoujun
 */
public interface UserRepository extends CommonRepository<User, Long> {

    User findByPhone(String phone);
}
