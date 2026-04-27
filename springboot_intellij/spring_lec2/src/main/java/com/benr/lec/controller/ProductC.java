package com.benr.lec.controller;

import com.benr.lec.service.productService;
import com.benr.lec.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/product")
@Controller
public class ProductC {

    // url - 조금 이쁘게 만들자
    // product/all
    // product/detail
    // product/create
    // product/update
    // product/delete

    @Autowired
    private productService productService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<ProductVO> products = productService.getProducts();
        System.out.println(products);
        model.addAttribute("products", products);
//        model.addAttribute("products", productService.getProducts()); 콘솔에 찍으려면 지금 코드로
        return "product/products";
    }

    @GetMapping("/detail")
    public String getProduct(@RequestParam int pk, Model model) {
        ProductVO product = productService.getOneProduct(pk);
        System.out.println(product);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @PostMapping("/create")
    public String createProduct(ProductVO product) { // jsp input name == vo name이랑 멤버변수? 같아야함
        productService.createProduct(product);
        return "redirect:/product/all";
    }

    @PostMapping("/update")
    public String updateProduct(ProductVO product) {
        productService.updateProduct(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int pk) {
        // 세션 o 있으면 로그인 되어있는 거니까 삭제하고? 세션?
        // 세션 x 없으면 로그인 안된 거니까 로그인 페이지로

        productService.deleteProduct(pk);
        return "redirect:/product/all";
    }





}
