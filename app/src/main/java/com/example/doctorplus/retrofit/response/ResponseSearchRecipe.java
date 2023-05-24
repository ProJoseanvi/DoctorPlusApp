package com.example.doctorplus.retrofit.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSearchRecipe {

    @SerializedName("receta_id")
    @Expose
    private List recetaId;
    @SerializedName("paciente")
    @Expose
    private List paciente;
    @SerializedName("fecha")
    @Expose
    private List fecha;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseSearchRecipe() {
    }

    /**
     *
     * @param date
     * @param paciente
     * @param recetaId
     */

    public ResponseSearchRecipe (List recetaId, List paciente, List date) {
        super();
        this.recetaId = recetaId;
        this.paciente = paciente;
        this.fecha = date;
    }

    public List getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(List recetaId) {
        this.recetaId = recetaId;
    }

    public List getPaciente() {
        return paciente;
    }

    public void setPaciente(List paciente) {
        this.paciente = paciente;
    }

    public List getFecha() {
        return fecha;
    }

    public void setFecha(List fecha) {
        this.fecha = fecha;
    }

    public String getMessage() {
        return null;
    }
    public Object getSuccess() {
        return null;
    }
}
