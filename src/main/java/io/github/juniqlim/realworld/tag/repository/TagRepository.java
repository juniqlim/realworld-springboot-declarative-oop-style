package io.github.juniqlim.realworld.tag.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tag;
import io.github.juniqlim.realworld.tag.domain.Tags;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public interface TagRepository {
    List<String> merges(Tags tags);
    List<String> findAll();
    List<Id> findArticleIdsByTagString(String tag);
    List<String> findByArticleId(Id articleId);

    class TagArrayListRepository implements TagRepository {
        private final List<Tag> tags = new ArrayList<>();

        private void merge(Tag tag) {
            tags.stream()
                .filter(t -> t.value().equals(tag.value()))
                .findFirst()
                .ifPresent(
                    t -> new Tag(
                        tag.value(),
                        Stream.concat(t.articleIds().stream(), tag.articleIds().stream()).distinct().collect(Collectors.toList())
                    )
                );
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }

        public List<String> merges(Tags tags) {
            tags.tags().stream()
                .map(tag -> new Tag(tag, Collections.singletonList(tags.articleId())))
                .forEach(this::merge);
            return null;
        }

        public List<String> findAll() {
            return tags.stream().map(Tag::value).collect(Collectors.toList());
        }


        public List<Id> findArticleIdsByTagString(String tag) {
            return tags.stream()
                .filter(t -> t.value().equals(tag))
                .findFirst()
                .map(Tag::articleIds)
                .orElse(new ArrayList<>());
        }

        public List<String> findByArticleId(Id articleId) {
            return tags.stream()
                .filter(t -> t.articleIds().contains(articleId))
                .map(Tag::value)
                .collect(Collectors.toList());
        }
    }
}
