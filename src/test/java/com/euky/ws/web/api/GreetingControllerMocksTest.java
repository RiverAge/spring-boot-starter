package com.euky.ws.web.api;

import com.euky.ws.AbstractControllerTest;
import com.euky.ws.service.EmailService;
import com.euky.ws.service.GreetingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by euky on 2017/3/11.
 */
public class GreetingControllerMocksTest extends AbstractControllerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private GreetingService greetingService;

    @InjectMocks
    private  GreetingController controller;

    private Collection<Greeting> getEntityListStubData() {
        Collection<Greeting> list = new ArrayList<Greeting>();
        list.add(getEntityStubData());
        return list;
    }

    private Greeting getEntityStubData() {
        Greeting entity = new Greeting();
        entity.setId(1L);
        entity.setText("hello");
        return entity;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(controller);
    }

    @Test
    public void testGetGreetings() throws Exception {

        Collection<Greeting> list = getEntityListStubData();

        when(greetingService.findAll()).thenReturn(list);

        String uri = "/api/greetings";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(greetingService, times(1)).findAll();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

    }
}
