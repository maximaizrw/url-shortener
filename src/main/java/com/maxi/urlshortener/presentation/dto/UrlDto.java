package com.maxi.urlshortener.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.maxi.urlshortener.persistence.entity.UrlEntity}
 */
public record UrlDto(Long id, String code, @Size(max = 2048) @NotBlank String longUrl, LocalDateTime createdAt,
                     LocalDateTime expiresAt, long clicks){
}