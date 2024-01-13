package com.lumaivzqz.urlshortener.infrastructure.repositories;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {

    Url findByLongUrl(String longUrl);
}