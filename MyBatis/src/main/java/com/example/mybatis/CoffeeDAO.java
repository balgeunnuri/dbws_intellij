package com.example.mybatis;

import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeDAO {
    // mybatis -> db orm framework -> 다 해주는 거
    // 편하기도 한데 유지보수 때문에 하는 거 확장성 고려, ...등 -> 설정. (db서버정보, sql) 외부 파일에.(xml)


    public static List<CoffeeVO> selectAllCoffee() {
        // dbmanager 안 씀

        SqlSession ss = null;
        try {
            ss = MyBatisDB.connect();
//        List<CoffeeVO> coffees = ss.selectList("benrqqq.getAllCoffee");
//        request.setAttribute("coffees", coffees);
            return ss.selectList("benrqqq.getAllCoffee");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return null;

    }

    public static void addCoffee(HttpServletRequest request) throws IOException, ServletException {
        SqlSession ss = null;
        try {
            ss = MyBatisDB.connect();

            // 값 받기
            String product_name = request.getParameter("product_name");
            String product_image = request.getParameter("product_image");
            String product_price = request.getParameter("product_price");
            String product_expiry = request.getParameter("product_expiry");

            System.out.println(product_expiry);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            CoffeeVO coffeeVO = new CoffeeVO();
            coffeeVO.setC_name(product_name);
            coffeeVO.setC_img(product_image);
            coffeeVO.setC_price(Integer.parseInt(product_price));
            coffeeVO.setC_exp(sdf.parse(product_expiry));
//            coffeeVO.setC_exp(Date.valueOf(product_expiry));


            if(ss.insert("benrqqq.addCoffee", coffeeVO) == 1) {
                System.out.println("add coffee success");
                ss.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }

    public static void delCoffee(HttpServletRequest request) {

        String no = request.getParameter("no");

        try(SqlSession ss = MyBatisDB.connect();){ // try 리소스 구문?, 알아서 닫아서 안 닫아도 됨
            if(ss.delete("benrqqq.delCoffee", no) == 1){
                System.out.println("delete coffee success");
                ss.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void searchCoffee(HttpServletRequest request) {
        try(SqlSession ss = MyBatisDB.connect()){


        // 값 받기
        String price = request.getParameter("price_search");
        String searchName = request.getParameter("product_search_name");
        List<CoffeeVO> coffees = null;

        if(searchName != null) {
            coffees = ss.selectList("benrqqq.searchCoffeeByName", searchName);
        } else if (price != null) {
            coffees = ss.selectList("benrqqq.searchCoffeeByPrice", price);
        } else {
            // 1. 객체로 넘기기
            // 2. map
            Map<String, String> vals = new HashMap<>();
            vals.put("price1", request.getParameter("price_min"));
            vals.put("price2", request.getParameter("price_max"));
            coffees = ss.selectList("benrqqq.searchCoffeeByRange", vals);


        }
        request.setAttribute("coffees", coffees);


            request.setAttribute("coffees", coffees);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editCoffee(HttpServletRequest request) {
        try(SqlSession ss = MyBatisDB.connect()){

            Map<String, String> vals = new HashMap<>();
            vals.put("price1", request.getParameter("price1"));
            vals.put("price2", request.getParameter("price2"));

            if(ss.update("benrqqq.updateCoffeePrice", vals) >= 1) {
                System.out.println("update coffee price success");
                ss.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
