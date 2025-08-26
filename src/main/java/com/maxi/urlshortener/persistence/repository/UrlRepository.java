package com.maxi.urlshortener.persistence.repository;

import com.maxi.urlshortener.persistence.entity.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Long> {

}
