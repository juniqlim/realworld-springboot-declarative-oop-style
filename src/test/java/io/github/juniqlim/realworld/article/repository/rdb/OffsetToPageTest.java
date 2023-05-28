package io.github.juniqlim.realworld.article.repository.rdb;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OffsetToPageTest {
    @Test
    void test() {
        Sort sort = Sort.by(Direction.DESC, "createAt");
        assertEquals(PageRequest.of(0, 10, sort), new OffsetToPage(0, 10, sort).pageable());
        assertEquals(PageRequest.of(1, 10, sort), new OffsetToPage(10, 20, sort).pageable());
        assertEquals(PageRequest.of(2, 10, sort), new OffsetToPage(20, 30, sort).pageable());

        assertEquals(PageRequest.of(0, 20, sort), new OffsetToPage(0, 20, sort).pageable());
        assertEquals(PageRequest.of(1, 20, sort), new OffsetToPage(20, 40, sort).pageable());
        assertEquals(PageRequest.of(2, 20, sort), new OffsetToPage(40, 60, sort).pageable());

        assertEquals(PageRequest.of(2, 20, Sort.unsorted()), new OffsetToPage(40, 60, Sort.unsorted()).pageable());
    }
}