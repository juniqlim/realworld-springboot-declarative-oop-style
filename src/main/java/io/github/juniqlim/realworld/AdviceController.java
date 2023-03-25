package io.github.juniqlim.realworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class AdviceController {
    @ExceptionHandler(Exception.class)
    public Response handle(Exception e) throws JsonProcessingException {
        new ObjectMapper().readValue("", Object.class);
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
