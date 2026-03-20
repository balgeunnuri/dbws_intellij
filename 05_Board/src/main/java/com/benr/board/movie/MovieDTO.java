package com.benr.board.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MovieDTO {
    // 결과 뭐? or DB tbl

    private int no;
    private String title;
    private String actor;
    private String img;
    private String story;

}
