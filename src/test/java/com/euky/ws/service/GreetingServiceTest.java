package com.euky.ws.service;

import com.euky.ws.AbstractTest;
import com.euky.ws.web.api.Greeting;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by euky on 2017/3/11.
 */

@Transactional
public class GreetingServiceTest extends AbstractTest {

    @Autowired
    private GreetingService service;

    @Before
    public void setUp() {
        service.evictCache();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testFindAll() {
        Collection<Greeting> list = service.findAll();
        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected size", 0, list.size());
    }

    @Test
    public void testCreateForIdNotNull() {
        Exception e = null;
        Greeting greeting = new Greeting();
        greeting.setId(123L);
        greeting.setText("Test For Create");
        try {
            service.create(greeting);
        } catch (IllegalArgumentException ee) {
            e = ee;
        }
        Assert.assertNotNull("failure - expected exception", e);
        Assert.assertTrue("failure - expected EntityExistsException", e instanceof IllegalArgumentException);
    }

    @Test
    public void testCreate() {
        Greeting greeting = new Greeting();
        greeting.setText("Test For Create");
        Greeting ret = service.create(greeting);
        Assert.assertNotNull(ret.getId());
        Assert.assertEquals(ret.getText(), "Test For Create");
    }

    @Test
    public void testFindOneForNotExist() {
       Greeting ret = service.findOne(Long.MAX_VALUE);
       Assert.assertNull(ret);
    }

    @Test
    public void testFindOne() {
        Greeting greeting = new Greeting();
        greeting.setText("original value");
        Greeting ret = service.create(greeting);
        Assert.assertEquals(ret.getText(), "original value");
    }

    @Test
    public void testForUpdate() {
        Greeting origin = new Greeting();
        origin.setText("original value");
        Long id = service.create(origin).getId();

        Greeting greeting = new Greeting();
        greeting.setText("Test For Update");
        greeting.setId(id);
        Greeting ret = service.update(greeting);
        Greeting ret2 = service.findOne(id);
        Assert.assertEquals(ret.getId().toString(), id.toString());
        Assert.assertEquals(ret.getText(), "Test For Update");
        Assert.assertEquals(ret2.getText(), "Test For Update");
    }

    @Test
    public void testForUpdateNotExist() {
        Greeting origin = new Greeting();
        origin.setText("original value");
        Long id = service.create(origin).getId();

        Greeting greeting = new Greeting();
        greeting.setText("Test For Update");
        greeting.setId(Long.MAX_VALUE);
        Greeting ret = service.update(greeting);
        Greeting ret2 = service.findOne(id);
        Assert.assertNull(ret);
        Assert.assertEquals(ret2.getText(), "original value");
    }
    @Test
    public void testForDelete() {

        Greeting origin = new Greeting();
        origin.setText("original value");
        final Long id = service.create(origin).getId();

        Greeting ret1 = service.findOne(id);
        Assert.assertNotNull(ret1.getId());
        Assert.assertNotNull(ret1.getText());

        service.delete(id);

        Greeting ret2 = service.findOne(id);
        Assert.assertNull(ret2);
    }

    @Test
    public void testForDeleteNotExist() {

        Greeting origin = new Greeting();
        origin.setText("original value");
        service.create(origin).getId();

        Assert.assertEquals(service.findAll().size(), 1);

        service.delete(Long.MAX_VALUE);

        Assert.assertEquals(service.findAll().size(), 1);
    }

}
