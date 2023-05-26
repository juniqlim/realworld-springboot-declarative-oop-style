package io.github.juniqlim.realworld.user.repository.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserFollowJpaRepository extends JpaRepository<UserFollowEntity, UserFollowEntity.Id> {
    List<UserFollowEntity> findByIdFollowerUserId(long followerUserId);
}
