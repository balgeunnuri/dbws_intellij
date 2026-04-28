package com.benr.lec.service;

import com.benr.lec.mapper.ProductMapper;
import com.benr.lec.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    @Autowired
    private ProductMapper productMapper;

    public List<ProductVO> getProducts() {
        List<ProductVO> products = productMapper.selectAll();
        System.out.println(products);
        return products;
//        return productMapper.selectAll(); // 콘솔에 띄울려면 위에 코드로 바꾸기

    }

    public int deleteProduct(int pk) {
//        if(productMapper.deleteProduct(pk) == 1) { // 윗줄밑줄 deleteProduct 다른 거. 구분할 수 있을 때 이름 똑같게 사용.
//            System.out.println("delete success");
//        } // 왜 지운 거지?
        return productMapper.deleteProduct(pk);
    }

    public int createProduct(ProductVO product) {
        int row = productMapper.insertProduct(product);
        if (row == 1) { // Mapper에는 sql대응으로 이름 만들기
            System.out.println("Product created successfully");
        }
        return row;
    }

    public String updateProduct(ProductVO product) {
        int row = productMapper.updateProduct(product);
        if (row == 1) {
            System.out.println("Product updated successfully");
            return "success";
        }
        return "fail";
    }

    public ProductVO getOneProduct(int pk) {
        return productMapper.selectOne(pk);
    }
}
