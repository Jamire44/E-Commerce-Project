package com.jamie.ecommerce_proj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ProductController {

    @RequestMapping("products")
    public String greet(){
        return "Hello World";
    }

}
