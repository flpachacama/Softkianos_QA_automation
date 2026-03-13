package com.sofkianos.producer.controller;

import com.sofkianos.producer.dto.KudoHistoryItemResponse;
import com.sofkianos.producer.service.KudoHistoryService;
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

    private final KudoHistoryService kudoHistoryService;

    @GetMapping
    public ResponseEntity<List<KudoHistoryItemResponse>> getKudosHistory() {
        return ResponseEntity.ok(kudoHistoryService.getKudosHistory());
    }
}
