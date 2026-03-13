package com.sofkianos.consumer.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
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
