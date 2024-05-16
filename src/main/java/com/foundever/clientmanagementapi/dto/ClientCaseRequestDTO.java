package com.foundever.clientmanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCaseRequestDTO {

    @NotBlank(message = "Client name must not be blank")
    private String clientName;

    @NotNull(message = "Message ID must be provided")
    private Long messageId;

}
