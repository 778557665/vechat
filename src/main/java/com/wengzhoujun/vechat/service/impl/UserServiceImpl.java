package com.wengzhoujun.vechat.service.impl;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.entity.User;
import com.wengzhoujun.vechat.repository.UserRepository;
import com.wengzhoujun.vechat.service.UserService;
import com.wengzhoujun.vechat.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Result signUp(User user) {
        return ResponseUtil.ok(save(user));
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByKeyword(String keyword) {
        User user = userRepository.findByPhone(keyword);
        if (null != user) {
            return user;
        }
        user = userRepository.findByChatMark(keyword);
        if (null != user) {
            return user;
        }
        return null;
    }
}
