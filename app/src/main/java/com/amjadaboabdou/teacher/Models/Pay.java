package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pay {

    @SerializedName("i_bank_id")
    @Expose
    private String id;
    @SerializedName("s_name")
    @Expose
    private String name;
    @SerializedName("s_transfer_number")
    @Expose
    private String transfer_number;

    public Pay(String id, String name, String transfer_number) {
        this.id = id;
        this.name = name;
        this.transfer_number = transfer_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransfer_number() {
        return transfer_number;
    }

    public void setTransfer_number(String transfer_number) {
        this.transfer_number = transfer_number;
    }
}
