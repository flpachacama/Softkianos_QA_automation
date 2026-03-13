package com.sofkianos.producer.dto;

import java.time.LocalDateTime;

public record KudoHistoryItemResponse(
        String id,
        String fromUser,
        String toUser,
        String category,
        String message,
        int points,
        LocalDateTime createdAt
) {
}
