package io.github.juniqlim.realworld.tag.repository.rdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tag;

    protected TagEntity() {
    }

    public TagEntity(String tag) {
        this.tag = tag;
    }

    public long id() {
        return id;
    }

    public String tag() {
        return tag;
    }
}
