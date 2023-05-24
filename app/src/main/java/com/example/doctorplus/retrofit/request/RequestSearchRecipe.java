package com.example.doctorplus.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSearchRecipe {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("patient_id")
        @Expose
        private Integer patientId;
        @SerializedName("date")
        @Expose
        private String date;

        public RequestSearchRecipe(String recetaId, Integer patientId, String fecha) {
            super();
            this.id = recetaId;
            this.patientId = patientId;
            this.date = fecha;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

