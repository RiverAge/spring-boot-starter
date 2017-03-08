package com.euky.tests.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by euky on 2017/3/7.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getTopicsTest() {
        String body = this.restTemplate.getForObject("/topics", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void getTopicTest() {
        String body = this.restTemplate.getForObject("/topic/java", String.class);
        assertThat(body).isEqualTo("Hello World");
    }



}
