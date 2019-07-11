package com.wengzhoujun.vechat.repository;

import com.wengzhoujun.vechat.entity.UserRelationship;

import java.util.List;

/**
 * Created on 2019/7/11.
 *
 * @author WengZhoujun
 */
public interface UserRelationshipRepository extends CommonRepository<UserRelationship, Long> {
    List<UserRelationship> findAllByUserId(Long userId);
}
