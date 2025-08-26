package com.maxi.urlshortener.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/**
 * DTO for {@link com.maxi.urlshortener.persistence.entity.UrlEntity}
 */
public record ShortenRequestDto(@Size(max = 2048) @NotBlank @URL String longUrl) {
}