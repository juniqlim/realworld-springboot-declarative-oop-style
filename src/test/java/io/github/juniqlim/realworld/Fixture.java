package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.object.jwt.Jwt.Jws;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class Fixture {
    public static final String testPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHqqXqGEjE8KEVlNd0OFWGMqEUXkE5PD1jC+h4/YK+1mrtq0b5MXH4Toy5w9WCezxHNFND7x1RG2tidHkqbNTgUa/8g3feh6VsN3nuSPlgPPQiNZSFgeB9VJdES/igwE8qjPT0T/eU7G10L9alGtiGgDvD6252vD4CHnUIziLUJAxevV3jhHXHISwVcKXa2Z/H0YRzblEhGDFVeFjQjOsrDvLq6YbWB6GEmCkQC0gUYoqEiRQmqMOl/DeSQe1xPPMpkOnpplW00nkVbampRGOEa78jUoV370BGLF05Hqz8TUaTQCThNTpA6jjVPlk1sLrqxteXzm/DV549WYCRS16BAgMBAAECggEATnXEyqQMwckggCi6ij5iR+BUBEGWmxFZt0SVyBNlYBUFdjqOK2QLM73J6U1216WJ8Ow0E5/gZp3i9ufBg2W5n5nem700v//XDcTvwX12q8/UqUwvfx7jw9f+O8NsIRvXLRakO/9bgkdR7dYU3xutievzpJUuJ4Eqo3MV4GfHAMSYvTdwEcJezfD6JuBdvoAW4wdt0LU6VPy888EHNPdWuRE/AuUIAhqfvyCLITaZY6RwYhjzYu6MK3R25rvu/rcHplZ78ukeR+OXNKO+NRg9vF2Lxjp2ZqGa4/CsNvzi2EO5lR1ITfqbza20iPppKC4/gtVXIfdWgn0rWt3nWVTRuQKBgQC+o9pr/ch8vmUKiTeSdt+/YnZEoL3Y5CMzqdbnZvrTCUbuhQ6uzNiibi4kOneP+igilmgYnHd/4VWFTU5ymPvvzbW3s5q2a4SOswLBMgOgW04pbl59Bp4jPsmbkX2zAi0r3y90MDMCPSz34lelo7hnOniCsmLSb3SKq1KYp2SnlwKBgQC2Ld6uWMGhCZnVEEByfBOPdXTbE1WF9hj6XvvBJzoFU0pObHJc5eUiAj5zwSCAOsV2aF84RmFOnZEUkBD7+1lTdIIlYdG+YEv2g3dwMZ0Pesr+JIupcX4POekcdhzWVXPinD5rpA+zp/qs7mxoaxVAMEesEkP2veP7idv85cetpwKBgQCFZV4XJrO9JfTJ1I5APFIAN0OObfOp6qj/I56uuQ5V0S4DBLPreIc10WcUDp1O77pZyWj5n7K2ltQivJ9h7M6NVCINcu2VK9LLj1MkIH0NHhapQwN29MR+4RZtdva/5Yv6IOexo3Bt5qSqp4Sw8Mi94tokifJAUaD4zzyyeJ8j7QKBgBo9wTWb00g++cmW7bGP7cFcSdjEkC0bpb1qsRjBbboWpT1moZKACuE7MYO67dOKo6bKoeyZNqr8R0mO9uNU8Sj0P0rjLDMf69E2Xp3qh5UTOuogmX1uu5m13b4bxUuaQ3cAynz2xMw8Bf+i2DqyMf1s9uXyO5fvAkfZM7bZ4klBAoGAXn5MIgPlFF+XfgDGOODliNNTsVbubL9ByOQIUZF18NRjqr9qzYRYwlacUExLC6Bv/giApAMUDqPlrt5hUx9y6JOHifvOVAp5BMn6mcL6tcLTNgemtSkc8wxEk1lpTkeOh/KflxZc3P0pJQAhksAT8PvD4fD//4D7bsavLfXaSm0=";
    public static final PrivateKey privateKey;

    static {
        try {
            privateKey = KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(testPrivateKey)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final User JAKE = new User(1, new Jws(privateKey).token(), "jakejake", "Jacob", "jake@jake.jake");
    public static final User JUNIQ = new User(2, new Jws(privateKey).token(), "juniqjuniq", "juniq", "juniq@juniq.juniq");
    public static final User MINK = new User(3, new Jws(privateKey).token(), "minkmink", "mink", "mink@mink.mink");

    public static final Article JAKE_ARTICLE = new Article(1L, "How to train your dragon", "Ever wonder how?", "You have to believe",
        JAKE.id(), new ArrayList<>());
    public static final Article JUNIQ_ARTICLE = new Article(2L, "Good day", "So toothless", "You have to believe",
        JUNIQ.id(), new ArrayList<>());
    public static final Article MINK_ARTICLE = new Article(3L, "Learn Elm", "learn", "It's like a functional language",
        MINK.id(), new ArrayList<>());

    public static final Comment JAKE_ARTICLE_COMMENT = new Comment(new LongId(1), new LongId(JAKE_ARTICLE.id()), "It's easy", Fixture.JUNIQ.id());
    public static final Comment JUNIQ_ARTICLE_COMMENT = new Comment(new LongId(2), new LongId(JUNIQ_ARTICLE.id()), "It's good", Fixture.MINK.id());
    public static final Comment MINK_ARTICLE_COMMENT1 = new Comment(new LongId(3), new LongId(MINK_ARTICLE.id()), "It's easy", Fixture.JAKE.id());
    public static final Comment MINK_ARTICLE_COMMENT2 = new Comment(new LongId(4), new LongId(MINK_ARTICLE.id()), "It's easy", Fixture.JUNIQ.id());

    public static final UserRepository USER_REPOSITORY = new UserRepository.Collection();
    public static final ArticleRepository ARTICLE_REPOSITORY = new ArticleRepository();
    public static final CommentRepository COMMENT_REPOSITORY = new CommentRepository();

    public static final Id LONG_ID_ONE = new LongId(1);
    public static final Id LONG_ID_TWO = new LongId(2);

    static {
        USER_REPOSITORY.save(Fixture.JAKE);
        USER_REPOSITORY.save(Fixture.JUNIQ);
        USER_REPOSITORY.save(Fixture.MINK);
        ARTICLE_REPOSITORY.save(Fixture.JAKE_ARTICLE);
        ARTICLE_REPOSITORY.save(Fixture.JUNIQ_ARTICLE);
        ARTICLE_REPOSITORY.save(Fixture.MINK_ARTICLE);
        COMMENT_REPOSITORY.save(Fixture.JAKE_ARTICLE_COMMENT);
        COMMENT_REPOSITORY.save(Fixture.JUNIQ_ARTICLE_COMMENT);
        COMMENT_REPOSITORY.save(Fixture.MINK_ARTICLE_COMMENT1);
        COMMENT_REPOSITORY.save(Fixture.MINK_ARTICLE_COMMENT2);
    }
}