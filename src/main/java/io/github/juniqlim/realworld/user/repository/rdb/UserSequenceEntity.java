package io.github.juniqlim.realworld.user.repository.rdb;

import javax.persistence.*;

@Entity
@SequenceGenerator(
    name = "USER_SEQ_GENERATOR",
    sequenceName = "userSequence",
    allocationSize = 1
)
class UserSequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private Long id;
}
