package com.maxi.urlshortener.presentation.controller;

import com.maxi.urlshortener.service.interfaces.IUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {
    private final IUrlService urlService;

    public RedirectController(IUrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String code) {
        return urlService.getOriginalUrl(code)
                .map(dto -> ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(dto.longUrl()))
                        .header("Cache-Control", "no-cache, no-store, must-revalidate")
                        .header("Pragma", "no-cache")
                        .header("Expires", "0")
                        .build())
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("URL not found or expired"));
    }








}
