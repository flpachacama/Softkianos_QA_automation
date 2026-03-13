package com.sofkianos.consumer.controller;

import com.sofkianos.consumer.domain.model.KudoCategory;
import com.sofkianos.consumer.dto.KudoHistoryItemResponse;
import com.sofkianos.consumer.entity.Kudo;
import com.sofkianos.consumer.service.KudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kudos")
@RequiredArgsConstructor
public class KudosHistoryController {

    private final KudoService kudoService;

    @GetMapping
    public ResponseEntity<List<KudoHistoryItemResponse>> getKudosHistory() {
        List<KudoHistoryItemResponse> response = kudoService.getKudosHistory().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    private KudoHistoryItemResponse toResponse(Kudo kudo) {
        return KudoHistoryItemResponse.builder()
                .id(String.valueOf(kudo.getId()))
                .fromUser(kudo.getFromUser())
                .toUser(kudo.getToUser())
                .category(toTitleCase(kudo.getCategory()))
                .message(kudo.getMessage())
                .points(categoryPoints(kudo.getCategory()))
                .createdAt(kudo.getCreatedAt())
                .build();
    }

    private String toTitleCase(KudoCategory category) {
        String lower = category.name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    private int categoryPoints(KudoCategory category) {
        return switch (category) {
            case INNOVATION, TEAMWORK, PASSION, MASTERY -> 10;
        };
    }
}
