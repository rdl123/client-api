package com.foundever.clientmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundever.clientmanagementapi.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}