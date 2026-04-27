package com.benr.lec.mapper;

import com.benr.lec.vo.ProductVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    // R
    // 반환 객채가 여러개 -> List<클래스타입>로 받기
    // 반환 객채가 하나 -> VO(bean, dto, ..)로 받기
    // c, u, d -> int (영향받은 개행 수)

    @Select("select * from product_test2")
    List<ProductVO> selectAll();

    @Delete("delete from product_test2 where p_no = #{pk}")
    int deleteProduct(int pk); // void가 int가 된 이유? -> 위 sql 실행결과 1아니면 0

    @Insert("insert into product_test2 values (product_test2_seq.nextval, #{p_name}, #{p_price})")
    int insertProduct(ProductVO product);

    @Update("update product_test2 set p_name = #{p_name}, p_price = #{p_price} where p_no = #{p_no}")
    int updateProduct(ProductVO product);

    @Select("select * from product_test2 where p_no = #{pk}")
    ProductVO selectOne(int pk); // 왜 얘는 ProductVO?
}
