package com.benr.lec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HC {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/product") // forward 왜 여기에? 다른 곳에서는 forward가 안됨. /주소값. APIC는 json만?
    public String productAsync() {
        return "product/product_async";
    }


}
