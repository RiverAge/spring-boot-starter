package com.euky.ws.batch;

import com.euky.ws.service.GreetingService;
import com.euky.ws.web.api.Greeting;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


/**
 * Created by euky on 2017/3/10.
 */

@Profile("batch")
@Component
public class GreetingBatchBean {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GreetingService greetingService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${batch.greeting.cron}")
    public void cronJob() {
        logger.info("> cron job");
        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store", greetings.size());
        logger.info("< cronJob");
    }

    @Scheduled(initialDelayString = "${batch.greeting.initialDelay}", fixedRateString = "${batch.greeting.fixedRate}")
    public void fixedRateJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");
        long pause = 5000;
        long start = System.currentTimeMillis();

        do {
            if (start * pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);

        logger.info("Process time was {} seconds.", pause / 1000);

        logger.info("< fixedRateJobWithInitialDelay");

        logger.info("The time is now {}", dateFormat.format(new Date()));
    }

}
