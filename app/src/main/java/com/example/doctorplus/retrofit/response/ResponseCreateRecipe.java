package com.example.doctorplus.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCreateRecipe {

    @SerializedName("success")
    @Expose
    String success;
    @SerializedName("message")
    @Expose
    String message;

    public ResponseCreateRecipe(String success, String message) {
        this.success = success;
        this.message = message;
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
}
