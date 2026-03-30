package com.benr.board.review;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewVO {
    private int no;
    private String title;
    private String txt;
    private Date date;
}
