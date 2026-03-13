package com.sofkianos.consumer.repository;

import com.sofkianos.consumer.entity.Kudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KudoRepository extends JpaRepository<Kudo, Long> {

    List<Kudo> findAllByOrderByCreatedAtDesc();
}