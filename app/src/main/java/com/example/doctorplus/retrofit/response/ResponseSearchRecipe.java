package com.example.doctorplus.retrofit.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSearchRecipe {

    @SerializedName("receta_id")
    @Expose
    private String recetaId;
    @SerializedName("paciente")
    @Expose
    private String paciente;
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
    public ResponseSearchRecipe() {
    }

    /**
     *
     * @param fecha
     * @param tomasDiarias
     * @param paciente
     * @param medicamento
     * @param recetaId
     */

    public ResponseSearchRecipe(String recetaId, String paciente, String fecha, String medicamento, String tomasDiarias) {
        super();
        this.recetaId = recetaId;
        this.paciente = paciente;
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

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
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
