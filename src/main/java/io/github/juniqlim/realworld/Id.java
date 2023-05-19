package io.github.juniqlim.realworld;

public interface Id {
    boolean equals(Id id);

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

        public long value() {
            return value;
        }
    }

    class EmptyId implements Id {
        @Override
        public boolean equals(Id id) {
            throw new UnsupportedOperationException();
        }
    }
}
