package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.CreateArticleAndTag;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;

@RestController
public class CreateArticleController {
    private final CreateArticleAndTag createArticleAndTag;
    private final FindArticleResponse findArticleResponse;
    private final FindUser findUser;
    private final PublicKey publicKey;

    public CreateArticleController(CreateArticleAndTag createArticleAndTag, FindArticleResponse findArticleResponse, FindUser findUser, PublicKey publicKey) {
        this.createArticleAndTag = createArticleAndTag;
        this.findArticleResponse = findArticleResponse;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/articles")
    public Response articles(@RequestHeader("Authorization") String token, @RequestBody Request request) {
        Id loginUserId = findUser.findIdByToken(new Token.Jws(publicKey, token));
        Request.Article a = request.article;
        CreateArticleAndTag.Response response = createArticleAndTag.create(
            new CreateArticleAndTag.Request(a.title, a.description, a.body, loginUserId, a.tagList)
        );
        return new Response(findArticleResponse.find(response.article().slug(), loginUserId));
    }

    private static class Request {
        private Article article;

        public Article getArticle() {
            return article;
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
