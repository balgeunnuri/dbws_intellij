package com.benr.lec.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// lombok -> 자동으로 getter, setter, toString, equals, hashcode 생성?
// @Data -> @AllArgsConstructor, @NoArgsConstructor, @ToString, @EqualsAndHashCode, @Getter, @Setter?
// 원래는 3개 다 써야했는데 왜?

@Data
public class ProductVO {
    // mybatis라는 framework 사용하려고 이름 테이블 칼럼명과 맞춰준 것
    private int p_no;
    private String p_name;
    private int p_price;

}
