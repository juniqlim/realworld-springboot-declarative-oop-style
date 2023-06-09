package io.github.juniqlim.realworld.article.domain;

public class Slugify {
    public String withDash(String text) {
        return text.toLowerCase().replace(" ", "-");
    }
}
