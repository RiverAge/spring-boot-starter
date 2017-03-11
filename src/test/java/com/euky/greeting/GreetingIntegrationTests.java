package com.euky.greeting;

import com.euky.ws.web.api.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by euky on 2017/3/9.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void curdTestForGreeting() {
        //POST TEST
        Greeting greeting = new Greeting();
        greeting.setText("test for post Greeting");
        String body = testRestTemplate.postForObject("/api/greetings", greeting, String.class);
        assertThat(body).isEqualTo("{\"id\":1,\"text\":\"test for post Greeting\"}");

        //PUT&GET TEST
        Greeting putGreeting = new Greeting();
        putGreeting.setId(1L);
        putGreeting.setText("test for Put Greeting");
        testRestTemplate.put("/api/greetings/1", putGreeting, String.class);
        body = testRestTemplate.getForObject("/api/greetings", String.class);
        assertThat(body).isEqualTo("[{\"id\":1,\"text\":\"test for Put Greeting\"}]");

        //DEL TEST
        testRestTemplate.delete("/api/greetings/1");
        body = testRestTemplate.getForObject("/api/greetings", String.class);
        assertThat(body).isEqualTo("[]");


    }
}
