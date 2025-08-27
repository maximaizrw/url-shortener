package com.maxi.urlshortener.service.implementation;

import com.maxi.urlshortener.persistence.entity.UrlEntity;
import com.maxi.urlshortener.persistence.repository.UrlRepository;
import com.maxi.urlshortener.presentation.dto.LongUrlResponseDto;
import com.maxi.urlshortener.presentation.dto.ShortenRequestDto;
import com.maxi.urlshortener.presentation.dto.ShortenResponseDto;
import com.maxi.urlshortener.service.interfaces.IUrlService;
import com.maxi.urlshortener.util.Base62Encoder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements IUrlService {

    private final UrlRepository urlRepository;
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Value("${app.base-url}")
    private String baseUrl;

    @Transactional
    @Override
    public Optional<ShortenResponseDto> shorten(ShortenRequestDto shortenRequestDto) {

        var now = LocalDateTime.now();

        UrlEntity url = UrlEntity.builder()
                .longUrl(shortenRequestDto.longUrl())
                .createdAt(now)
                .expiresAt(now.plusHours(48))
                .clicks(0L)
                .build();
        UrlEntity savedUrl = urlRepository.save(url);

        String code = Base62Encoder.encode(savedUrl.getId());
        // Update the entity with the generated short URL
        savedUrl.setCode(code);
        urlRepository.save(savedUrl);
        String shortUrl = baseUrl + "/" + code;

        return Optional.of(new ShortenResponseDto(shortUrl));
    }

    @Transactional
    @Override
    public Optional<LongUrlResponseDto> getOriginalUrl(String code) {
        return urlRepository.findByCode(code).map(urlEntity -> {
            urlEntity.setClicks(urlEntity.getClicks() + 1);
            urlRepository.save(urlEntity);
            return new LongUrlResponseDto(urlEntity.getLongUrl());
        });
    }



}

