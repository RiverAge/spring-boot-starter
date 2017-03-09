package com.euky.ws.service;

import com.euky.ws.repository.GreetingRepository;
import com.euky.ws.web.api.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Greeting findOne(Long id) {
        return greetingRepository.findOne(id);
    }

    @Override
    public Greeting create(Greeting greeting) {
        if (greeting.getId() != null) return null;
        return greetingRepository.save(greeting);
    }

    @Override
    public Greeting update(Greeting greeting) {
        Greeting greetingPersisted = findOne(greeting.getId());
        if (greetingPersisted == null) {
            return null;
        }
        return greetingRepository.save(greeting);
    }

    @Override
    public void delete(Long id) {
        greetingRepository.delete(id);
    }

}
