package com.maxi.urlshortener.presentation.controller;

import com.maxi.urlshortener.presentation.dto.ShortenRequestDto;
import com.maxi.urlshortener.presentation.dto.ShortenResponseDto;
import com.maxi.urlshortener.service.interfaces.IUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
    private final IUrlService urlService;

    public UrlController(IUrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping()
    public ResponseEntity<ShortenResponseDto> shorten(@Valid @RequestBody ShortenRequestDto request) {

        ShortenResponseDto response = urlService.shorten(request)
                .orElseThrow();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
