package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {
    @SerializedName("pk_i_id")
    @Expose
    public Integer pkIId;
    @SerializedName("s_name")
    @Expose
    public String sName;
    @SerializedName("s_sequence_name")
    @Expose
    public String sSequenceName;
    @SerializedName("s_language_code")
    @Expose
    public String sLanguageCode;
    @SerializedName("fk_i_parent_id")
    @Expose
    public Object fkIParentId;
    @SerializedName("cities")
    @Expose
    public List<City> cities = null;

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

    public Object getFkIParentId() {
        return fkIParentId;
    }

    public void setFkIParentId(Object fkIParentId) {
        this.fkIParentId = fkIParentId;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
