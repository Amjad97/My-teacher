package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grade {

    @SerializedName("pk_i_id")
    @Expose
    private Integer pkIId;
    @SerializedName("s_title")
    @Expose
    private String sTitle;
    @SerializedName("fk_i_parent_id")
    @Expose
    private String fkIParentId;

    public Integer getPkIId() {
        return pkIId;
    }

    public void setPkIId(Integer pkIId) {
        this.pkIId = pkIId;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getFkIParentId() {
        return fkIParentId;
    }

    public void setFkIParentId(String fkIParentId) {
        this.fkIParentId = fkIParentId;
    }
}
