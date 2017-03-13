package com.euky.ws.web;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by euky on 2017/3/11.
 */
public interface ExceptionAttributes {
    Map<String, Object> getExceptionAttributes(Exception exception,
                                               HttpServletRequest httpServletRequest,
                                               HttpStatus httpStatus);
}
