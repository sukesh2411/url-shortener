package com.sukesh.url.shortener;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url shortenUrl(String originalUrl) {

        String shortCode = UUID.randomUUID()
                .toString()
                .substring(0, 6);

        Url url = new Url(originalUrl, shortCode);

        return urlRepository.save(url);
    }

    public Url getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
    }
}