package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.Jwt.Jws;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.domain.User.Id;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class TestRepository {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public TestRepository(UserRepository userRepository, ArticleRepository articleRepository)
        throws InvalidKeySpecException, NoSuchAlgorithmException {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        setData();
    }

    public UserRepository userRepository() {
        return userRepository;
    }

    public ArticleRepository articleRepository() {
        return articleRepository;
    }

    private void setData() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String testPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHqqXqGEjE8KEVlNd0OFWGMqEUXkE5PD1jC+h4/YK+1mrtq0b5MXH4Toy5w9WCezxHNFND7x1RG2tidHkqbNTgUa/8g3feh6VsN3nuSPlgPPQiNZSFgeB9VJdES/igwE8qjPT0T/eU7G10L9alGtiGgDvD6252vD4CHnUIziLUJAxevV3jhHXHISwVcKXa2Z/H0YRzblEhGDFVeFjQjOsrDvLq6YbWB6GEmCkQC0gUYoqEiRQmqMOl/DeSQe1xPPMpkOnpplW00nkVbampRGOEa78jUoV370BGLF05Hqz8TUaTQCThNTpA6jjVPlk1sLrqxteXzm/DV549WYCRS16BAgMBAAECggEATnXEyqQMwckggCi6ij5iR+BUBEGWmxFZt0SVyBNlYBUFdjqOK2QLM73J6U1216WJ8Ow0E5/gZp3i9ufBg2W5n5nem700v//XDcTvwX12q8/UqUwvfx7jw9f+O8NsIRvXLRakO/9bgkdR7dYU3xutievzpJUuJ4Eqo3MV4GfHAMSYvTdwEcJezfD6JuBdvoAW4wdt0LU6VPy888EHNPdWuRE/AuUIAhqfvyCLITaZY6RwYhjzYu6MK3R25rvu/rcHplZ78ukeR+OXNKO+NRg9vF2Lxjp2ZqGa4/CsNvzi2EO5lR1ITfqbza20iPppKC4/gtVXIfdWgn0rWt3nWVTRuQKBgQC+o9pr/ch8vmUKiTeSdt+/YnZEoL3Y5CMzqdbnZvrTCUbuhQ6uzNiibi4kOneP+igilmgYnHd/4VWFTU5ymPvvzbW3s5q2a4SOswLBMgOgW04pbl59Bp4jPsmbkX2zAi0r3y90MDMCPSz34lelo7hnOniCsmLSb3SKq1KYp2SnlwKBgQC2Ld6uWMGhCZnVEEByfBOPdXTbE1WF9hj6XvvBJzoFU0pObHJc5eUiAj5zwSCAOsV2aF84RmFOnZEUkBD7+1lTdIIlYdG+YEv2g3dwMZ0Pesr+JIupcX4POekcdhzWVXPinD5rpA+zp/qs7mxoaxVAMEesEkP2veP7idv85cetpwKBgQCFZV4XJrO9JfTJ1I5APFIAN0OObfOp6qj/I56uuQ5V0S4DBLPreIc10WcUDp1O77pZyWj5n7K2ltQivJ9h7M6NVCINcu2VK9LLj1MkIH0NHhapQwN29MR+4RZtdva/5Yv6IOexo3Bt5qSqp4Sw8Mi94tokifJAUaD4zzyyeJ8j7QKBgBo9wTWb00g++cmW7bGP7cFcSdjEkC0bpb1qsRjBbboWpT1moZKACuE7MYO67dOKo6bKoeyZNqr8R0mO9uNU8Sj0P0rjLDMf69E2Xp3qh5UTOuogmX1uu5m13b4bxUuaQ3cAynz2xMw8Bf+i2DqyMf1s9uXyO5fvAkfZM7bZ4klBAoGAXn5MIgPlFF+XfgDGOODliNNTsVbubL9ByOQIUZF18NRjqr9qzYRYwlacUExLC6Bv/giApAMUDqPlrt5hUx9y6JOHifvOVAp5BMn6mcL6tcLTNgemtSkc8wxEk1lpTkeOh/KflxZc3P0pJQAhksAT8PvD4fD//4D7bsavLfXaSm0=";
        PrivateKey privateKey = KeyFactory.getInstance("RSA")
            .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(testPrivateKey)));

        User jake = new User(userRepository.findSequence(), new Jws(privateKey).token(), "jakejake", "Jacob", "jake@jake.jake");
        User juniq = new User(userRepository.findSequence(), new Jws(privateKey).token(), "juniqjuniq", "juniq", "juniq@juniq.juniq");
        User mink = new User(userRepository.findSequence(), new Jws(privateKey).token(), "minkmink", "mink", "mink@mink.mink");
        userRepository.save(jake);
        userRepository.save(juniq);
        userRepository.save(mink);

        Article jakeArticle = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            jake.id(), new ArrayList<>());
        Article juniqArticle = new Article("Good day", "So toothless", "You have to believe",
            new Id(2), new ArrayList<>());
        Article minkArticle = new Article("Learn Elm", "learn", "It's like a functional language",
            new Id(3), new ArrayList<>());
        jakeArticle.addComment(new Comment(articleRepository.findCommentSequence(), "It's easy", juniq.id()));
        juniqArticle.addComment(new Comment(articleRepository.findCommentSequence(), "It's good", mink.id()));
        minkArticle.addComment(new Comment(articleRepository.findCommentSequence(), "It's hard", jake.id()));
        minkArticle.addComment(new Comment(articleRepository.findCommentSequence(), "It's big", juniq.id()));
        articleRepository.save(jakeArticle);
        articleRepository.save(juniqArticle);
        articleRepository.save(minkArticle);
    }
}
