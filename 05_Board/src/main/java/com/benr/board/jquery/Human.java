package com.benr.board.jquery;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Human {
    private int no;
    private String name;
    private int age;


    @Override
    public String toString() {
        return "Human{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }







}


