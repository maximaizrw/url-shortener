package com.maxi.urlshortener.persistence.repository;

import com.maxi.urlshortener.persistence.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByCode(String code);
}
