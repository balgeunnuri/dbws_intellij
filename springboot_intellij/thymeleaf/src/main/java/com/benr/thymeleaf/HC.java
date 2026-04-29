package com.benr.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HC {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("str1", "애트리뷰트값");
        model.addAttribute("str2", "thymeleaf test~~");

        model.addAttribute("a", "<a> 링크 </a>");
        model.addAttribute("b", "차이점 <b> 확인 </b>");

        model.addAttribute("name", "mz");
        model.addAttribute("age", 20);

        return "index";
    }

}
