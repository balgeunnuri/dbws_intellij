package com.benr.lec;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {
// body가 없는 추상 메서드만 인터페이스 안에 적을 수 있음?

    @Select("select * from menu_test")
    List<MenuVO> selectAll();
    //public, protected, default, private 정의하기

    @Delete("delete from menu_test where m_no = #{no}")
    void deleteMenu(int no);

    @Insert("insert into menu_test values (menu_test_seq.nextval, #{m_name}, #{m_price})")
    void insertMenu(MenuVO menuVO);

    @Update("update menu_test set m_price = #{m_price} where m_no = #{m_no}")
    void updateMenu(MenuVO menuVO);

    @Select("select * from menu_test where m_no = #{no}")
    MenuVO selectOne(int no);

    @Update("update menu_test set m_name = #{m_name}, m_price = #{m_price} where m_no = #{m_no}")
    void updateMenu2(MenuVO menuVO);

}
