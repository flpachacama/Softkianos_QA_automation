package com.sofkianos.producer.service;

import com.sofkianos.producer.dto.KudoHistoryItemResponse;

import java.util.List;

public interface KudoHistoryService {

    List<KudoHistoryItemResponse> getKudosHistory();
}
