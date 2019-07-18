package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggest {

    @SerializedName("s_mobile_number")
    @Expose
    private String mobile_number;
    @SerializedName("s_msg")
    @Expose
    private String msg;
    @SerializedName("s_name")
    @Expose
    private String name;
    @SerializedName("s_identification_number")
    @Expose
    private String identification_number;

    public Suggest(String mobile_number, String msg, String name, String identification_number) {
        this.mobile_number = mobile_number;
        this.msg = msg;
        this.name = name;
        this.identification_number = identification_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }
}
