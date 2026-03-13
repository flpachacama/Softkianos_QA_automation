package com.sofkianos.consumer.service;

import com.sofkianos.consumer.domain.events.KudoEvent;
import com.sofkianos.consumer.entity.Kudo;

import java.util.List;

/**
 * Service interface for persisting Kudos.
 */
public interface KudoService {

    /**
     * Persists a kudo from the given typed event.
     *
     * @param event the deserialized kudo event from the message broker
     */
    void saveKudo(KudoEvent event);

    /**
     * Retrieves persisted kudos ordered by recency.
     */
    List<Kudo> getKudosHistory();
}