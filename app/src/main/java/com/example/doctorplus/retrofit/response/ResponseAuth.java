
package com.example.doctorplus.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseAuth {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String nombre;
    @SerializedName("role")
    @Expose
    private String rol;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAuth() {
    }

    /**
     * 
     * @param success
     * @param message
     * @param accessToken
     * @param userId
     * @param nombre
     * @param rol
     */
    public ResponseAuth(String success, String message, String accessToken, String userId, String nombre, String rol) {
        super();
        this.success = success;
        this.message = message;
        this.accessToken = accessToken;
        this.userId = userId;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
