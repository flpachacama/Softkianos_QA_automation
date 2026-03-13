package com.sofkianos.producer.controller;

import com.sofkianos.producer.dto.KudoHistoryItemResponse;
import com.sofkianos.producer.service.KudoHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KudosHistoryControllerTest {

    @Mock
    private KudoHistoryService kudoHistoryService;

    @Test
    void getKudosHistory_ReturnsOkWithPayload() {
        List<KudoHistoryItemResponse> history = List.of(
                new KudoHistoryItemResponse(
                        "1",
                        "alice@example.com",
                        "bob@example.com",
                        "Teamwork",
                        "Great collaboration on the release",
                        10,
                        LocalDateTime.now()
                )
        );

        when(kudoHistoryService.getKudosHistory()).thenReturn(history);

        KudosHistoryController controller = new KudosHistoryController(kudoHistoryService);
        ResponseEntity<List<KudoHistoryItemResponse>> response = controller.getKudosHistory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(history, response.getBody());
        verify(kudoHistoryService, times(1)).getKudosHistory();
    }
}
