package com.wengzhoujun.vechat.service.impl;

import com.wengzhoujun.vechat.entity.UserRelationship;
import com.wengzhoujun.vechat.repository.UserRelationshipRepository;
import com.wengzhoujun.vechat.service.UserRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2019/7/11.
 *
 * @author WengZhoujun
 */
@Service
public class UserRelationshipServiceImpl implements UserRelationshipService {

    @Autowired
    private UserRelationshipRepository userRelationshipRepository;

    @Override
    public UserRelationship save(UserRelationship userRelationship) {
        return userRelationshipRepository.save(userRelationship);
    }

    @Override
    public List<UserRelationship> findAllByUserId(Long userId) {
        return userRelationshipRepository.findAllByUserId(userId);
    }
}
