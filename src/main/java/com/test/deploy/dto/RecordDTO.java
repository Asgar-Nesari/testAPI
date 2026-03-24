package com.test.deploy.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RecordDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "Title must not be blank")
        private String title;

        @NotBlank(message = "Purpose must not be blank")
        private String purpose;

        @NotBlank(message = "Description must not be blank")
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private String title;
        private String purpose;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
