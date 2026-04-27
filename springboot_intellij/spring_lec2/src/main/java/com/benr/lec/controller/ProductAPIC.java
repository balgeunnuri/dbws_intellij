package com.benr.lec.controller;

// json으로 결과물을 뽑는 공간.

import com.benr.lec.service.productService;
import com.benr.lec.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// url 이쁘게 만드는 거. -> 동일 url / 다른 method 요청 방식
                                // GET, POST, PUT, DELETE

@RequestMapping("/api/product")
@RestController
public class ProductAPIC {
        // all : 전체조회

    @Autowired
    private productService productService;

    @GetMapping("/all")
    public List<ProductVO> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public int createProduct(@RequestBody ProductVO product) {
        return productService.createProduct(product);
    }
    

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
