package com.example.doctorplus.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class RequestCreateRecipe {

        @SerializedName("id")
        @Expose
        private String recetaId;
        @SerializedName("patientId")
        @Expose
        private Integer patientId;
        @SerializedName("date")
        @Expose
        private String fecha;
        @SerializedName("med")
        @Expose
        private String medicamento;
        @SerializedName("takes")
        @Expose
        private String tomasDiarias;

        /**
         * No args constructor for use in serialization
         *
         */
        public RequestCreateRecipe(Integer patient, String date, String medication, String tomas) {
        }

        /**
         * @param recetaId
         * @param fecha
         * @param tomasDiarias
         * @param patientId
         * @param medicamento
         */
        public RequestCreateRecipe(String recetaId, Integer patientId, String fecha, String medicamento, String tomasDiarias) {
            super();
            this.recetaId = recetaId;
            this.patientId = patientId;
            this.fecha = fecha;
            this.medicamento = medicamento;
            this.tomasDiarias = tomasDiarias;
        }

        public RequestCreateRecipe(int numberRecipe, String patient, String date, String medication, int tomas) {
        }

        public String getRecetaId() { return recetaId; }

        public void setRecetaId(String recetaId) {this.recetaId = recetaId; }

        public Integer getPatientId() {
            return patientId;
        }

        public void setPatientId(Integer patientId) {
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


