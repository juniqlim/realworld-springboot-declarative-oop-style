package io.github.juniqlim.realworld.article.web;

import static java.util.stream.Collectors.toList;

import io.github.juniqlim.realworld.article.TagUseCase;
import io.github.juniqlim.realworld.article.domain.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FindTagsController {
    private final TagUseCase tagUseCase;

    FindTagsController(TagUseCase tagUseCase) {
        this.tagUseCase = tagUseCase;
    }

    @GetMapping("/api/tags")
    public Response tags() {
        return new Response(tagUseCase.findAll().stream()
            .map(Tag::value)
            .collect(toList()));
    }

    private static class Response {
        private final List<String> tags;

        Response(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return tags;
        }
    }
}