package com.euky.ws.service;

import com.euky.ws.web.api.Greeting;

import java.util.concurrent.Future;

/**
 * Created by euky on 2017/3/10.
 */
public interface EmailService {

    Boolean send(Greeting greeting);

    void sendAsync(Greeting greeting);

    Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
