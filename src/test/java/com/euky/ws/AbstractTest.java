package com.euky.ws;

import com.euky.Application;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by euky on 2017/3/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public abstract class AbstractTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
