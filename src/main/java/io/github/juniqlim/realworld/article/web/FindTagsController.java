package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.tag.TagUseCase;
import io.github.juniqlim.realworld.tag.domain.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
