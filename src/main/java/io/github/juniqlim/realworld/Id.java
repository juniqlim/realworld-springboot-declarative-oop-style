package io.github.juniqlim.realworld;

import java.util.Objects;

public interface Id {
    boolean equals(Id id);
    boolean isEmpty();

    class LongId implements Id {
        private final long value;

        public LongId(long value) {
            this.value = value;
        }

        @Override
        public boolean equals(Id id) {
            if (id instanceof LongId) {
                return value == ((LongId) id).value;
            }
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        public long value() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            LongId longId = (LongId) o;
            return value == longId.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    class EmptyId implements Id {
        @Override
        public boolean equals(Id id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
