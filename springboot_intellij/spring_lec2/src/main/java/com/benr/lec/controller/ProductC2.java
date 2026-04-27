package com.benr.lec.controller;

import com.benr.lec.service.productService;
import com.benr.lec.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductC2 {

    // url - 더더 이쁘게 만들자 rest API

    // product/all
    // product/detail
    // product/create
    // product/update
    // product/delete

    //  GET     /products       : 모든 제품 조회 r
    //          /products/{id}  : 특정 제품 조회 r
    //  POST    /products       : 새 제품 생성 c insert
    //  PUT     /products/{id}  : 특정 제품 수정 u update
    //  DELETE  /products/{id}  : 특정 제품 삭제 d delete

    @Autowired // DI : 객체 만들고 넣어주는 거. 세상 결합도 낮은 걸 선호. 컨트롤러 아이폰, 지금 이거 배터리?
    private productService productService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "product/products2";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id, Model model) { // 파라미터 아니고 경로에 있는 걸 받는 경로변수
        model.addAttribute("product", productService.getOneProduct(id));
        return "product/detail";
    }

    @PostMapping
    public String addPost(ProductVO product) {
        productService.createProduct(product);
        return  "redirect:/products"; // redirect?
    }

    @DeleteMapping()
    public String deleteProduct(@RequestParam int pk) {
        System.out.println("delete 요청 -----");
        productService.deleteProduct(pk);
        return "redirect:/products";
    }

    @PutMapping
    public String updateProduct(ProductVO product) {
        System.out.println("put 요청 ------");
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @ResponseBody
    @GetMapping("/json/{id}")
    public ProductVO getProduct(@PathVariable int id) {
        return productService.getOneProduct(id);
    }

    // fetch , $ajax




}
