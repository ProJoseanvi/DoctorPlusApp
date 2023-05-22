package com.example.doctorplus.retrofit.response;

import com.example.doctorplus.common.Patient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListPatients {

    @SerializedName("patients")
    @Expose
    List<Patient> patients;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

}
