package io.github.juniqlim.realworld.article;

class CreateArticle {
    Article create(Request request) {
        return new Article();
    }

    static class Request {
    }
}
