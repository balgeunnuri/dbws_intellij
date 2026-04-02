package com.benr.board.review;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewVO {
    private int no;
    private String title;
    private String txt;
    private Date date;

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this); // this==이 클래스
        // json 문법으로 변환받는 코드
    }

}
