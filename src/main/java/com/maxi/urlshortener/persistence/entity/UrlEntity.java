package com.maxi.urlshortener.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "url_entity")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "long_url", nullable = false)
    @NotBlank
    @Size(max = 2048)
    private String longUrl;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "clicks", nullable = false)
    private long clicks = 0L;
}