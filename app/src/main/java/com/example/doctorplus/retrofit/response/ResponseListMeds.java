package com.example.doctorplus.retrofit.response;

import com.example.doctorplus.common.Med;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseListMeds {

    @SerializedName("meds")
    @Expose
    List<Med> meds;

    public List<Med> getMeds() {
        return meds;
    }

    public List<String> getMedsNames(){
        List<String> medsNames = new ArrayList<>();
        for(Med med : meds){
            medsNames.add(med.getName());
        }
        return medsNames;
    }

    public void setMeds(List<Med> meds) {
        this.meds = meds;
    }
}
