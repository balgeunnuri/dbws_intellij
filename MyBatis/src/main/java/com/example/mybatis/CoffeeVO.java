package com.example.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class CoffeeVO
{
    private int c_no;
    private String c_name;
    private String c_img;
    private int c_price;
    private Date c_exp;
}
