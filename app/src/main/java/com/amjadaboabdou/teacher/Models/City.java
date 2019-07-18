package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @SerializedName("pk_i_id")
    @Expose
    private Integer pkIId;
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_sequence_name")
    @Expose
    private String sSequenceName;
    @SerializedName("s_language_code")
    @Expose
    private String sLanguageCode;
    @SerializedName("fk_i_parent_id")
    @Expose
    private String fkIParentId;
    @SerializedName("cities")
    @Expose
    private List<Object> cities = null;

    public Integer getPkIId() {
        return pkIId;
    }

    public void setPkIId(Integer pkIId) {
        this.pkIId = pkIId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSequenceName() {
        return sSequenceName;
    }

    public void setsSequenceName(String sSequenceName) {
        this.sSequenceName = sSequenceName;
    }

    public String getsLanguageCode() {
        return sLanguageCode;
    }

    public void setsLanguageCode(String sLanguageCode) {
        this.sLanguageCode = sLanguageCode;
    }

    public String getFkIParentId() {
        return fkIParentId;
    }

    public void setFkIParentId(String fkIParentId) {
        this.fkIParentId = fkIParentId;
    }

    public List<Object> getCities() {
        return cities;
    }

    public void setCities(List<Object> cities) {
        this.cities = cities;
    }
}
