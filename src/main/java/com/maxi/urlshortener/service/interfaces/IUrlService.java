package com.maxi.urlshortener.service.interfaces;

import com.maxi.urlshortener.presentation.dto.ShortenRequestDto;
import com.maxi.urlshortener.presentation.dto.ShortenResponseDto;

import java.util.Optional;

public interface IUrlService {
    Optional<ShortenResponseDto> shorten(ShortenRequestDto shortenRequestDto);

}
