package com.benr.lec;

import lombok.Data;

@Data
public class MenuVO {
    // db tbl
    private int m_no; // 바티스때문에 이름 같게 하는 것
    private String m_name;
    private int m_price;
}
