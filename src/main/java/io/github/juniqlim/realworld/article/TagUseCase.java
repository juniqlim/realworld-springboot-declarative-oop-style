package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.TagRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class TagUseCase {
    private final TagRepository tagRepository;

    public TagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void merge(String tag) {
        tagRepository.merge(new Tag(tag));
    }

    public void merges(List<String> tags) {
        tagRepository.merges(tags.stream()
            .map(Tag::new)
            .collect(Collectors.toList()));
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void delete(String tag) {
        tagRepository.delete(new Tag(tag));
    }
}
