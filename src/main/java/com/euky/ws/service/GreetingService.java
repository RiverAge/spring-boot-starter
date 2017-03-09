package com.euky.ws.service;

import com.euky.ws.web.api.Greeting;

import java.util.Collection;

/**
 * Created by euky on 2017/3/9.
 */
public interface GreetingService {
    Collection<Greeting> findAll();

    Greeting findOne(Long id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Long id);
}
