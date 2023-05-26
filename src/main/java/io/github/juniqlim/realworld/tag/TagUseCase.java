package io.github.juniqlim.realworld.tag;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tags;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagUseCase {
    private final TagRepository tagRepository;

    public TagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<String> merge(Request request) {
        return tagRepository.merges(new Tags(request.articleId, request.tags));
    }

    public List<String> findAll() {
        return tagRepository.findAll();
    }

    public List<Id> findArticleIdsByTag(String tag) {
        return tagRepository.findArticleIdsByTagString(tag);
    }

    public List<String> findByArticleId(Id articleId) {
        return tagRepository.findByArticleId(articleId);
    }

    public List<Tags> findByArticleIds(List<Id> articleIds) {
        return tagRepository.findByArticleIds(articleIds);
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
