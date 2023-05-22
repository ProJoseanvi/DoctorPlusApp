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
        @SerializedName("medicamento")
        @Expose
        private String medicamento;
        @SerializedName("tomas_diarias")
        @Expose
        private String tomasDiarias;

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
         * @param medicamento
         */


        public RequestSearchRecipe(String recetaId, String patientId, String fecha, String medicamento) {
            super();
            this.recetaId = recetaId;
            this.patientId = patientId;
            this.fecha = fecha;
            this.medicamento = medicamento;
            this.tomasDiarias = tomasDiarias;
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

        public String getMedicamento() {
            return medicamento;
        }

        public void setMedicamento(String medicamento) {
            this.medicamento = medicamento;
        }

        public String getTomasDiarias() {
            return tomasDiarias;
        }

        public void setTomasDiarias(String tomasDiarias) {
            this.tomasDiarias = tomasDiarias;
        }

    }

