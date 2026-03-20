package com.benr.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MemberDTO {
    // 결과에서 뭐쓸지? or tbl 디비보고 따라 써라 테이블보고
    private int no;
    private String name;
    private int age;
}
