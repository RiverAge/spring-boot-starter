package com.euky.student;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by euky on 2017/4/17.
 */

@RestController
@RequestMapping("/students")
public class StudentController {
    @PostMapping("")
    public Object addStudent(@Valid @RequestBody Student s, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map ret = new HashMap();
            ret.put("result", false);
            ret.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return ret;
        }
        return s;
    }

}
