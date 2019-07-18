package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModelGrades {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("mainGrades")
    @Expose
    private List<MainGrade> mainGrades = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<MainGrade> getMainGrades() {
        return mainGrades;
    }

    public void setMainGrades(List<MainGrade> mainGrades) {
        this.mainGrades = mainGrades;
    }

}
