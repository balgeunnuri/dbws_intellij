package com.benr.lec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    // spring -> springboot (DI, IoC)

    @Autowired // 인터페이스에 구현된 메서드를 사용할 수 있도록 주입.
    // DI(dependency injection) => 객체 만들어서 집어넣어준 거.
    private MenuMapper menuMapper;


    public List<MenuVO> selectAllMenu() {
        // 싱글톤으로 하면 static을 붙여야 할텐데 여기선 @처리로 안 해도 됨?
        // db -> mybatis ( db server, sql ) => .xml (config.xml, mapper.xml)

//  MenuMapper.selectAll(); 들어있는 게 없는 인터페이스에 static으로 부르고 있음 그래서 안 됨. @Autowired 달면 쓸 수 있음
        return menuMapper.selectAll();


    }


    public void deleteMenu(int no) {
        menuMapper.deleteMenu(no);
    }

    public void addMenu(MenuVO menuVO) {
        menuMapper.insertMenu(menuVO);
    }


    public void editMenu(MenuVO menuVO) {
        menuMapper.updateMenu(menuVO);
    }


    public MenuVO getMenu(int no) { // return으로 받아야하는 이유? MenuVO? int no?
        return menuMapper.selectOne(no);
    }

    public void updateMenu(MenuVO menuVO) {
        menuMapper.updateMenu2(menuVO);
    }
}
