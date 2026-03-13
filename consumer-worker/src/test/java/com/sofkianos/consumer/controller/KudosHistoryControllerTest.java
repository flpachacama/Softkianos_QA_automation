package com.sofkianos.consumer.controller;

import com.sofkianos.consumer.domain.model.KudoCategory;
import com.sofkianos.consumer.entity.Kudo;
import com.sofkianos.consumer.service.KudoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KudosHistoryControllerTest {

    @Mock
    private KudoService kudoService;

    @Test
    void getKudosHistory_ReturnsMappedHistory() {
        Kudo storedKudo = Kudo.builder()
                .id(1L)
                .fromUser("alice@example.com")
                .toUser("bob@example.com")
                .category(KudoCategory.TEAMWORK)
                .message("Great collaboration on the release")
                .createdAt(LocalDateTime.of(2026, 3, 13, 10, 30))
                .build();

        when(kudoService.getKudosHistory()).thenReturn(List.of(storedKudo));

        KudosHistoryController controller = new KudosHistoryController(kudoService);
        ResponseEntity<List<com.sofkianos.consumer.dto.KudoHistoryItemResponse>> response = controller.getKudosHistory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Teamwork", response.getBody().get(0).category());
        assertEquals(10, response.getBody().get(0).points());
        verify(kudoService, times(1)).getKudosHistory();
    }
}
