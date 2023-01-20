package io.github.juniqlim.realworld;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class AdviceController {
    @ExceptionHandler(Exception.class)
    public Response handle(Exception e) {
        e.printStackTrace();
        return new Response(e.getMessage());
    }

    private static class Response {
        private final String message;

        private Response(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
