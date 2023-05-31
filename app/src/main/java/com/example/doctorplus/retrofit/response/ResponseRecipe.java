package com.example.doctorplus.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRecipe extends ResponseRecipeId{

    @SerializedName("patient_name")
    @Expose
    private String namePatient;
    @SerializedName("medication_name")
    @Expose
    private String med;

    @SerializedName("number_takes")
    @Expose
    private String takes;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("state")
    @Expose
    private Integer state;

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public String getTakes() {
        return takes;
    }

    public void setTakes(String takes) {
        this.takes = takes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
