package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainGrade {

    @SerializedName("pk_i_id")
    @Expose
    private Integer pkIId;
    @SerializedName("s_title")
    @Expose
    private String sTitle;
    @SerializedName("fk_i_parent_id")
    @Expose
    private Object fkIParentId;
    @SerializedName("grades")
    @Expose
    private List<Grade> grades = null;

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

    public Object getFkIParentId() {
        return fkIParentId;
    }

    public void setFkIParentId(Object fkIParentId) {
        this.fkIParentId = fkIParentId;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
