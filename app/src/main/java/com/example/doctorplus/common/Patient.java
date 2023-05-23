package com.example.doctorplus.common;

import androidx.annotation.NonNull;

public class Patient {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @NonNull
    public String toString(){
        return this.name;
    }
}
