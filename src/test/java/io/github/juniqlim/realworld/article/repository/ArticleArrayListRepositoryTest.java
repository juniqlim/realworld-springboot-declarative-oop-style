package io.github.juniqlim.realworld.article.repository;

class ArticleArrayListRepositoryTest {
//    @Test
//    void save() {
//        ArticleRepository articleRepository = new ArticleArrayListRepository();
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//                "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//
//        assertDoesNotThrow(() -> articleRepository.save(article));
//    }

//    @Test
//    void filter() throws InterruptedException {
//        ArticleRepository articleRepository = new ArticleArrayListRepository();
//
//        for (int i = 1; i < 101; i++) {
////            articleRepository.save(new Article(Fixture.LONG_ID_ONE, i + "", i + "", i + "", new LongId(i % 2),
////                Arrays.asList(new Tag("reactjs" + (i % 3)), new Tag("angularjs" + i), new Tag("dragons" + i))));
//            sleep(10);
//        }
//
//        List<Article> articles = articleRepository.findByTagAndAuthorUserIdAndFavoriteUserIdOrderByRegdate("reactjs1", new Id.EmptyId(), new Id.EmptyId(), 1, 3);
//
//        System.out.println("articles.size() = " + articles.size());
//        articles.forEach(a -> System.out.println("a.getSlug() = " + a.slug() + ", a.getCreateAt = " + a.createdAt()));
//
//        assertEquals(3, articles.size());
//        assertEquals("91", articles.get(0).slug());
//        assertEquals("88", articles.get(1).slug());
//        assertEquals("85", articles.get(2).slug());
//
//        List<Article> articles2 = articleRepository.findByTagAndAuthorUserIdAndFavoriteUserIdOrderByRegdate(null, Fixture.LONG_ID_ONE, new Id.EmptyId(), 0, 100);
//
//        assertEquals(50, articles2.size());
//    }
//
//    @Test
//    void condition1() {
//        Conditional conditional = new Conditional(null, Fixture.LONG_ID_ONE, new Id.EmptyId());
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//
//        assertTrue(conditional.value(article));
//    }
//
//    @Test
//    void condition2() {
//        Conditional conditional = new Conditional("re", Fixture.LONG_ID_ONE, new Id.EmptyId());
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//
//        assertFalse(conditional.value(article));
//    }
//
//    @Test
//    void condition3() {
//        Conditional conditional = new Conditional(null, new Id.EmptyId(), new Id.EmptyId());
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//
//        assertTrue(conditional.value(article));
//    }
//
//    @Test
//    void condition4() {
//        Conditional conditional = new Conditional("reactjs", Fixture.LONG_ID_ONE, new Id.EmptyId());
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//
//        assertTrue(conditional.value(article));
//    }
//
//    @Test
//    void condition5() {
//        Conditional conditional = new Conditional("reactjs", Fixture.LONG_ID_ONE, Fixture.LONG_ID_TWO);
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//        article.favorite(Fixture.LONG_ID_TWO);
//
//        assertTrue(conditional.value(article));
//    }
//
//    @Test
//    void condition6() {
//        Conditional conditional = new Conditional(null, new Id.EmptyId(), new Id.EmptyId());
//        Article article = new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?",
//            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
//
//        assertTrue(conditional.value(article));
//    }
}
