package com.euky.ws.service;

import com.euky.ws.repository.GreetingRepository;
import com.euky.ws.web.api.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by euky on 2017/3/9.
 */

@Service
public class GreetingServiceBean implements  GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Collection<Greeting> findAll() {
        return greetingRepository.findAll();
    }

    @Override
    @Cacheable(value = "greetings", key = "#id")
    public Greeting findOne(Long id) {
        return greetingRepository.findOne(id);
    }

    @Override
    @CachePut(value = "greetings", key = "#result")
    public Greeting create(Greeting greeting) {
        if (greeting.getId() != null) return null;
        return greetingRepository.save(greeting);
    }

    @Override
    @CachePut(value = "greetings", key = "#greeting.id")
    public Greeting update(Greeting greeting) {
        Greeting greetingPersisted = findOne(greeting.getId());
        if (greetingPersisted == null) {
            return null;
        }
        return greetingRepository.save(greeting);
    }

    @Override
    @CacheEvict(value = "greetings", key = "#id")
    public void delete(Long id) {
        greetingRepository.delete(id);
    }

    @Override
    @CacheEvict(value = "greetings", allEntries = true)
    public void evictCache() {

    }
}
