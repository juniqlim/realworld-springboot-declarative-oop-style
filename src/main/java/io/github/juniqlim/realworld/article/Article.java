package io.github.juniqlim.realworld.article;

class Article {
    private String slug;
    private String title;
    private String description;
    private String body;
    private String[] tagList;
    private String createdAt;
    private String updatedAt;
    private boolean favorited;
    private int favoritesCount;
    private Author author;

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public String[] getTagList() {
        return tagList;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public Author getAuthor() {
        return author;
    }

    static class Author {
        private String username;
        private String bio;
        private String image;
        private boolean following;

        public String getUsername() {
            return username;
        }

        public String getBio() {
            return bio;
        }

        public String getImage() {
            return image;
        }

        public boolean isFollowing() {
            return following;
        }
    }
}
