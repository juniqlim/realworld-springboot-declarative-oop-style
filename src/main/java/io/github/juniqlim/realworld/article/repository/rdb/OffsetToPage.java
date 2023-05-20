package io.github.juniqlim.realworld.article.repository.rdb;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

class OffsetToPage {
    private final int offset;
    private final int limit;
    private final Sort sort;

    public OffsetToPage(int offset, int limit, Sort sort) {
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    PageRequest pageable() {
        int pageSize = limit - offset;
        return PageRequest.of(offset/pageSize, pageSize, sort);
    }
}
