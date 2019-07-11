package com.wengzhoujun.vechat.service;

import com.wengzhoujun.vechat.entity.UserRelationship;

import java.util.List;

/**
 * Created on 2019/7/11.
 *
 * @author WengZhoujun
 */
public interface UserRelationshipService {

    UserRelationship save(UserRelationship userRelationship);

    List<UserRelationship> findAllByUserId(Long userId);



}
