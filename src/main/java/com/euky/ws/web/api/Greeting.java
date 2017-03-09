package com.euky.ws.web.api;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * Created by euky on 2017/3/9.
 */

@Entity
public class Greeting {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
