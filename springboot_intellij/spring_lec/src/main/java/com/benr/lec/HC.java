package com.benr.lec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HC {

    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "index"; // forward
        // req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @GetMapping("/")
    public String aaaaaaaaaaaaaaaa() {
        System.out.println("aaaaaaaaaa");
        return "index"; // forward
    }

    @GetMapping("/qq")
    public String aaaaaaaaaaaaaa(@RequestParam int a) {
        System.out.println("qqq");
        System.out.println(a - 2);
        return "index"; // forward
    }
}
