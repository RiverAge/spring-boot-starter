package com.euky.ws.web.api;

import com.euky.ws.service.EmailService;
import com.euky.ws.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.Future;

/**
 * The GreetingController class is a Restful web service controller. The
 * <code>@RestController</code> annotation informs Spring that each
 * <code>@RequestMapping</code> methods returns a <code>@ResponseBody</code>
 * which, by default, contains a ResponseEntity converted to JSON with an
 * associated HTTP status code.
 *
 * @author euky
 */

@RestController
public class GreetingController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The greetingService business service.
     */
    @Autowired
    private GreetingService greetingService;

    /**
     * The EmailService business service.
     */
    @Autowired
    private EmailService emailService;

    /**
     * Wb service endpoint to fetch all Greeting entities. The service returns
     * the collection of Greeting entities as JSON.
     *
     * @return A ResponseEntity containing a Collection of Greeting objects.
     */
    @GetMapping(
            value = "/api/greetings",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {
        Collection<Greeting> greetings = greetingService.findAll();

        return new ResponseEntity<>(greetings, HttpStatus.OK);
    }

    @GetMapping(
            value = "/api/greetings/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreeting(@PathVariable Long id) {
        Greeting greeting = greetingService.findOne(id);
        if (greeting == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    @PostMapping(
            value = "/api/greetings",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        Greeting savedGreeting = greetingService.create(greeting);
        return new ResponseEntity<>(savedGreeting, HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/api/greetings/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
        Greeting updatedGreeting = greetingService.update(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedGreeting, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/greetings/{id}")
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable Long id) {
        greetingService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/api/greetings/{id}/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> sendGreeting(@PathVariable Long id, @RequestParam(value = "wait", defaultValue = "false") boolean waitForAsyncResult) {
        logger.info("> sendGreeting");

        Greeting greeting;

        try {
            greeting = greetingService.findOne(id);
            if (greeting == null) {
                logger.info("< sendGreeting");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (waitForAsyncResult) {
                Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(greeting);
                boolean emailSent = asyncResponse.get();
                logger.info("- greeting email send ? {} ", emailSent);
            } else {
                emailService.sendAsync(greeting);
            }
        } catch (Exception e) {
            logger.error("A problem occurred sending the Greeting.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< sendGreeting");
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }



}
