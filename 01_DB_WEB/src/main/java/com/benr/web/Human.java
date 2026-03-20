package com.benr.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Human {
    // 결과에서 뭘 쓸지? or db
    private String name;
    private int age;
}
