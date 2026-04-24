package com.benr.lec.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
public class UserC {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUser(Model model) { // list attribute에 담으려면 Model필요
        model.addAttribute("users", userService.getAllUser());
        return "user/select";
    }

    @GetMapping("/del")
    public String deleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @PostMapping
    public String addUser(UserVO userVO) {
        userService.addUser(userVO);
        return "redirect:/user";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user/mypage";
    }

    @GetMapping("/detail")
    public String getUser(UserVO userVO) {
        return "user/mypage";
    }

}
