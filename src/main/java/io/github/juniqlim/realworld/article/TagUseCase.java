package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagUseCase {
    private final TagRepository tagRepository;

    public TagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void merges(List<Request> requests) {
        List<Tag> tags = requests.stream()
            .collect(Collectors.groupingBy(
                r -> r.tag,
                Collectors.mapping(r -> r.articleId, Collectors.toList())
            ))
            .entrySet().stream()
            .map(entry -> new Tag(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        tagRepository.merges(tags);
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
        private final String tag;

        Request(Id articleId, String tag) {
            this.articleId = articleId;
            this.tag = tag;
        }
    }
}
