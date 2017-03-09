package com.euky.ws.repository;

import com.euky.ws.web.api.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by euky on 2017/3/9.
 */

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long>{
}
