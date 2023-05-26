package io.github.juniqlim.realworld.user.repository.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserFollowJpaRepository extends JpaRepository<UserFollowEntity, UserFollowEntity.Id> {
}
