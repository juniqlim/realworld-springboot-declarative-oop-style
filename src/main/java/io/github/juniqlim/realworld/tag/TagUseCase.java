package io.github.juniqlim.realworld.tag;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tag;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagUseCase {
    private final TagRepository tagRepository;

    public TagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> merge(Request request) {
        List<Tag> tags = request.tags.stream()
            .map(tag -> new Tag(tag, Collections.singletonList(request.articleId)))
            .collect(Collectors.toList());
        tagRepository.merges(tags);
        return tags;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void deleteByTagString(String tag) {
        tagRepository.deleteByTagString(tag);
    }

    List<Id> findArticleIdsByTag(String tag) {
        return tagRepository.findArticleIdsByTagString(tag);
    }

    public static class Request {
        private final Id articleId;
        private final List<String> tags;

        public Request(Id articleId, List<String> tags) {
            this.articleId = articleId;
            this.tags = tags;
        }
    }
}
