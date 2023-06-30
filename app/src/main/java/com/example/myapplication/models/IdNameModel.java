package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class IdNameModel {

    public IdNameModel() {
    }

    public IdNameModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long id;
    private String name;


    public static List<IdNameModel> mockData() {
        List<IdNameModel> list = new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            list.add(new IdNameModel(i, String.format("第 %s 条数据", i)));
        }
        return list;
    }
}
