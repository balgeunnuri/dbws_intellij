package com.benr.lec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/menu")
@Controller
public class MenuC {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String menu(Model model) {
        // db 에서 menu tbl 전체 조회
        // DAO (M 모델) => service
//        MenuService
        List<MenuVO> menus =  menuService.selectAllMenu();
        System.out.println(menus);
        model.addAttribute("menus", menus);
        return "select";
    }

    @GetMapping("/del")
    public String deleteMenu(@RequestParam int no) { //@RequestParam int no -> 매개변수?
        System.out.println(no);
        menuService.deleteMenu(no); // pk (no) 보내주는 게 중요
        return "redirect:/menu";
    }

//   @PostMapping("/menu")
//   public String addMenu(@RequestParam String name, @RequestParam int price) {
//       System.out.println(name);
//       System.out.println(price);
//        return "index";
//
//    }

    @PostMapping
    public String addMenu(MenuVO menuVO) { //MenuVO 클래스 설계도 new가 있어야 객체?
        System.out.println(menuVO); // 인스턴스 자동으로 만들어줌 IOC. framework에서 IoC 중요
        menuService.addMenu(menuVO);
        return "redirect:/menu";

    }

    @GetMapping("/edit")
    public String editMenu(MenuVO menuVO) { //MenuVO menuVO 를 @RequestParam int no 대신 사용하려면 파라미터명 db와 같게
        menuService.editMenu(menuVO);
        return "redirect:/menu";
    }

    @GetMapping("/detail")
    public String detailMenu(@RequestParam int no, Model model) {
        model.addAttribute("menu", menuService.getMenu(no));
        return "detail";
    }

    @GetMapping("/update")
    public String updateMenu(@RequestParam int no, Model model) {
        model.addAttribute("menu", menuService.getMenu(no));
        return "update";
    }

    @PostMapping("/update")
    public String updateMenu(MenuVO menuVO) {
        menuService.updateMenu(menuVO);

        return "redirect:/menu/detail?no=" + menuVO.getM_no();
    }

    @GetMapping("/{id}")
    public String detailMenu2(@PathVariable int id, Model model) { //PathVariable?
        System.out.println(id);
        model.addAttribute("menu", menuService.getMenu(id)); // 값이 허면에 나오게 해주는 코드?
        return "detail";
    }

    @ResponseBody
    @GetMapping("/json")
    public MenuVO getJson(@RequestParam int m_no) { //@ResponseBody
        return menuService.getMenu(m_no);
    }

    @ResponseBody
    @GetMapping("/json/all")
    public List<MenuVO> getJSON() { //@ResponseBody
        return menuService.selectAllMenu();
    }

}
