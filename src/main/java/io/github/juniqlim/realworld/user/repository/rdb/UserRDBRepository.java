package io.github.juniqlim.realworld.user.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserRDBRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRDBRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
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
}
