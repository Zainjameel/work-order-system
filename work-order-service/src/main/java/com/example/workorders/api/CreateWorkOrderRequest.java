package com.example.workorders.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateWorkOrderRequest(
    @NotBlank String site,
    @NotBlank String assetType,
    @NotBlank String assetId,
    @NotBlank String priority,
    @NotBlank String description
) {}