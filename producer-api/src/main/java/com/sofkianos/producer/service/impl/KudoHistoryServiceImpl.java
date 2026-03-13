package com.sofkianos.producer.service.impl;

import com.sofkianos.producer.dto.KudoHistoryItemResponse;
import com.sofkianos.producer.exception.KudoQueryException;
import com.sofkianos.producer.service.KudoHistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class KudoHistoryServiceImpl implements KudoHistoryService {

    private final RestClient restClient;
    private final String kudosHistoryUrl;

    public KudoHistoryServiceImpl(
            RestClient.Builder restClientBuilder,
            @Value("${app.kudos-history.url}") String kudosHistoryUrl
    ) {
        this.restClient = restClientBuilder.build();
        this.kudosHistoryUrl = kudosHistoryUrl;
    }

    @Override
    public List<KudoHistoryItemResponse> getKudosHistory() {
        try {
            List<KudoHistoryItemResponse> response = restClient.get()
                    .uri(kudosHistoryUrl)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return response == null ? List.of() : response;
        } catch (RestClientException ex) {
            throw new KudoQueryException("Unable to retrieve kudos history from consumer-worker", ex);
        }
    }
}
