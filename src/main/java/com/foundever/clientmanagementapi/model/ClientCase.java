package com.foundever.clientmanagementapi.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_cases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientName;

    @Column
    private String clientReference;

    @OneToMany(mappedBy = "clientCase", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Message> messages;
}