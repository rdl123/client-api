package com.foundever.clientmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundever.clientmanagementapi.model.ClientCase;

@Repository
public interface ClientCaseRepository extends JpaRepository<ClientCase, Long> {
}