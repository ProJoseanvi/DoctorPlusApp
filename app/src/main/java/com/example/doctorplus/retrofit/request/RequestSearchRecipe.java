package com.example.doctorplus.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class RequestSearchRecipe {

        @SerializedName("receta_id")
        @Expose
        private String recetaId;
        @SerializedName("patient_id")
        @Expose
        private String patientId;
        @SerializedName("fecha")
        @Expose
        private String fecha;

        /**
         * No args constructor for use in serialization
         *
         */
        public RequestSearchRecipe(int numberRecipe, String patient, String date, String medication, int tomas) {
        }

        /**
         * @param recetaId
         * @param patientId
         * @param fecha
         */


        public RequestSearchRecipe(String recetaId, String patientId, String fecha, String medicamento) {
            super();
            this.recetaId = recetaId;
            this.patientId = patientId;
            this.fecha = fecha;
        }

        public String getRecetaId() {
            return recetaId;
        }

        public void setRecetaId(String recetaId) {
            this.recetaId = recetaId;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

    }

