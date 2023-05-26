package io.github.juniqlim.realworld.user.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByToken(String token);
    List<User> findByIdIn(List<Id> userIds);
    @Query(value = "call next value for user_sequence", nativeQuery = true)
    long sequence();
}
