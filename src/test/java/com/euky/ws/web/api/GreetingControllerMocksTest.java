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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by euky on 2017/3/11.
 */
public class GreetingControllerMocksTest extends AbstractControllerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private GreetingService greetingService;

    @InjectMocks
    private GreetingController controller;

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

    @Test
    public void testGetGreetingNotFound() throws Exception {
        Long id = Long.MAX_VALUE;

        when(greetingService.findOne(id)).thenReturn(null);

        String uri = "/api/greetings/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(greetingService, times(1)).findOne(id);

        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty", content.trim().length() == 0);
    }

    @Test
    public void testCreateGreeting() throws Exception {
        Greeting entity = getEntityStubData();

        when(greetingService.create(any(Greeting.class))).thenReturn(entity);

        String uri = "/api/greetings";
        String inputJson = super.mapToJson(entity);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(greetingService, times(1)).create(any(Greeting.class));

        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

        Greeting createdEntiry = super.mapFromJson(content, Greeting.class);

        Assert.assertNotNull("failure - expected entity not null", createdEntiry);
        Assert.assertNotNull("failure - expected id attribute not null", createdEntiry.getId());
        Assert.assertEquals("failure - expected text attribute match", entity.getText(), createdEntiry.getText());
    }

    @Test
    public void testUpdateGreeting() throws Exception {
        Greeting entity = getEntityStubData();
        entity.setText(entity.getText() + "test");
        Long id = new Long(1);

        when(greetingService.update((any(Greeting.class)))).thenReturn(entity);

        String uri = "/api/greetings/{id}";
        String inputJson = super.mapToJson(entity);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders
                        .put(uri, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(greetingService, times(1)).update(any(Greeting.class));

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);

        Greeting updateEntity = super.mapFromJson(content, Greeting.class);

        Assert.assertNotNull("failure - expected entity not null", updateEntity);
        Assert.assertEquals("failure - expected id attributed unchanged", entity.getId(), updateEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", entity.getText(), updateEntity.getText());
    }

    @Test
    public void testDeleteGreeting() throws Exception {
        Long id = new Long(1);
        String uri = "/api/greetings/{id}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(greetingService, times(1)).delete(id);

        Assert.assertEquals("failure - expected HTTP status 204", 204, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty", content.trim().length() == 0);
    }

}

