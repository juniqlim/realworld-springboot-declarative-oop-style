package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.CreateArticle;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateArticleController {
    private final CreateArticle createArticle;
    private final FindUser findUser;
    private final PublicKey publicKey;

    CreateArticleController(CreateArticle createArticle, FindUser findUser, PublicKey publicKey) {
        this.createArticle = createArticle;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/articles")
    public Response articles(@RequestHeader("Authorization") String token, @RequestBody Request request) {
        String jwsToken = new Token(publicKey, token).jwsToken();
        return new Response(new ArticleResponse(
            createArticle.create(request.createArticleRequest(jwsToken)),
            findUser.find(jwsToken).profile()
        ));
    }

    private static class Request {
        private Article article;

        public Article getArticle() {
            return article;
        }

        private CreateArticle.Request createArticleRequest(String jwsToken) {
            return new CreateArticle.Request(article.getTitle(), article.getDescription(), article.getBody(), jwsToken, article.getTagList());
        }

        private static class Article {
            private String title;
            private String description;
            private String body;
            private List<String> tagList;

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getBody() {
                return body;
            }

            public List<String> getTagList() {
                return tagList;
            }
        }
    }

    private static class Response {
        private final ArticleResponse article;

        Response(ArticleResponse article) {
            this.article = article;
        }

        public ArticleResponse getArticle() {
            return article;
        }
    }
}
