package com.euky.ws.web.api;

import com.euky.ws.web.DefaultExceptionAttributes;
import com.euky.ws.web.ExceptionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by euky on 2017/3/11.
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(NoResultException nre, HttpServletRequest httpServletRequest) {
        logger.error("> handleNoResultException");
        logger.error("- NoResultException:", nre);

        ExceptionAttributes exceptionsAttributes = new DefaultExceptionAttributes();
        Map<String, Object> responseBody = exceptionsAttributes.getExceptionAttributes(nre, httpServletRequest, HttpStatus.NOT_FOUND);

        logger.error("< handleNoResultException");
        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e, HttpServletRequest httpServletRequest) {
        logger.error("> handleException");
        logger.error("- Exception:", e);

        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
        Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(e, httpServletRequest, HttpStatus.INTERNAL_SERVER_ERROR);

        logger.error("< handleException");
        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
