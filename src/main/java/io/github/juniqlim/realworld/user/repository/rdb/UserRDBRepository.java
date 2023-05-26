package io.github.juniqlim.realworld.user.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Repository
class UserRDBRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserFollowJpaRepository userFollowJpaRepository;

    public UserRDBRepository(UserJpaRepository userJpaRepository, UserFollowJpaRepository userFollowJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.userFollowJpaRepository = userFollowJpaRepository;
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(new UserToUserEntity().userEntity(user));
    }

    @Override
    public User findById(Id userId) {
        return new UserToUserEntity().user(userJpaRepository.findById(userId.value())
            .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    @Override
    public User findByEmail(String email) {
        return new UserToUserEntity().user(userJpaRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    @Override
    public User findByUsername(String username) {
        return new UserToUserEntity().user(userJpaRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    @Override
    public User findByToken(String token) {
        return new UserToUserEntity().user(userJpaRepository.findByToken(token)
            .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    @Override
    public void update(User user) {
        userJpaRepository.save(new UserToUserEntity().userEntity(user));
    }

    @Override
    public long findSequence() {
        return userJpaRepository.sequence();
    }

    @Override
    public List<User> findByIds(List<Id> userIds) {
        return userJpaRepository.findByIdIn(userIds);
    }

    @Override
    public void follow(Id followerUserId, Id followeeUserId) {
        userFollowJpaRepository.save(
            new UserFollowEntity(new UserFollowEntity.Id(followerUserId.value(), followeeUserId.value()))
        );
    }

    @Override
    public void unFollow(Id followerUserId, Id followeeUserId) {
        userFollowJpaRepository.delete(
            new UserFollowEntity(new UserFollowEntity.Id(followerUserId.value(), followeeUserId.value()))
        );
    }

    @Override
    public boolean isFollowing(Id followerUserId, Id followeeUserId) {
        return userFollowJpaRepository.findById(new UserFollowEntity.Id(followerUserId.value(), followeeUserId.value()))
            .isPresent();
    }

    @Override
    public List<Id> followingUserIds(Id followerUserId) {
        return userFollowJpaRepository.findByIdFollowerUserId(followerUserId.value())
            .stream()
            .map(userFollowEntity -> new Id.LongId(userFollowEntity.id().followeeUserId()))
            .collect(Collectors.toList());
    }
}
