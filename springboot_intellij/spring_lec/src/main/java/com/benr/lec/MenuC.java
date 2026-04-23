package com.benr.lec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuC {

    @GetMapping("/menu")
    public String menu() {
        return "index";
    }

//   @PostMapping("/menu")
//   public String addMenu(@RequestParam String name, @RequestParam int price) {
//       System.out.println(name);
//       System.out.println(price);
//        return "index";
//
//    }

    @PostMapping("/menu")
    public String addMenu(MenuVO menuVO) { //MenuVO 클래스 설계도 new가 있어야 객체?
        System.out.println(menuVO); // 인스턴스 자동으로 만들어줌 IOC. framework에서 IOC 중요
        return "index";

    }

}
