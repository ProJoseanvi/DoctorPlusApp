package com.example.doctorplus.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListDates {
    @SerializedName("dates")
    @Expose
    List<String> dates;

    public List<String> getDates(){
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
