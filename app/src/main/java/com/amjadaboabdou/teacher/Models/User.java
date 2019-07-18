package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("pk_i_id")
    @Expose
    private Integer pkIId;
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_mobile_number")
    @Expose
    private String sMobileNumber;
    @SerializedName("i_gender")
    @Expose
    private String iGender;
    @SerializedName("s_avatar")
    @Expose
    private String sAvatar;
    @SerializedName("i_registeration_period")
    @Expose
    private String iRegisterationPeriod;
    @SerializedName("s_specilization")
    @Expose
    private String sSpecilization;
    @SerializedName("s_address")
    @Expose
    private Object sAddress;
    @SerializedName("s_identification_number")
    @Expose
    private String sIdentificationNumber;
    @SerializedName("dt_registered_date")
    @Expose
    private String dtRegisteredDate;
    @SerializedName("d_rate")
    @Expose
    private String dRate;
    @SerializedName("b_approved")
    @Expose
    private String bApproved;
    @SerializedName("b_enabled")
    @Expose
    private String bEnabled;
    @SerializedName("dt_created_date")
    @Expose
    private String dtCreatedDate;
    @SerializedName("dt_modified_date")
    @Expose
    private String dtModifiedDate;
    @SerializedName("dt_deleted_date")
    @Expose
    private Object dtDeletedDate;
    @SerializedName("areas")
    @Expose
    private List<Areas> areas = null;
    @SerializedName("grades")
    @Expose
    private List<Grade> grades = null;

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

    public String getsMobileNumber() {
        return sMobileNumber;
    }

    public void setsMobileNumber(String sMobileNumber) {
        this.sMobileNumber = sMobileNumber;
    }

    public String getiGender() {
        return iGender;
    }

    public void setiGender(String iGender) {
        this.iGender = iGender;
    }

    public String getsAvatar() {
        return sAvatar;
    }

    public void setsAvatar(String sAvatar) {
        this.sAvatar = sAvatar;
    }

    public String getiRegisterationPeriod() {
        return iRegisterationPeriod;
    }

    public void setiRegisterationPeriod(String iRegisterationPeriod) {
        this.iRegisterationPeriod = iRegisterationPeriod;
    }

    public String getsSpecilization() {
        return sSpecilization;
    }

    public void setsSpecilization(String sSpecilization) {
        this.sSpecilization = sSpecilization;
    }

    public Object getsAddress() {
        return sAddress;
    }

    public void setsAddress(Object sAddress) {
        this.sAddress = sAddress;
    }

    public String getsIdentificationNumber() {
        return sIdentificationNumber;
    }

    public void setsIdentificationNumber(String sIdentificationNumber) {
        this.sIdentificationNumber = sIdentificationNumber;
    }

    public String getDtRegisteredDate() {
        return dtRegisteredDate;
    }

    public void setDtRegisteredDate(String dtRegisteredDate) {
        this.dtRegisteredDate = dtRegisteredDate;
    }

    public String getdRate() {
        return dRate;
    }

    public void setdRate(String dRate) {
        this.dRate = dRate;
    }

    public String getbApproved() {
        return bApproved;
    }

    public void setbApproved(String bApproved) {
        this.bApproved = bApproved;
    }

    public String getbEnabled() {
        return bEnabled;
    }

    public void setbEnabled(String bEnabled) {
        this.bEnabled = bEnabled;
    }

    public String getDtCreatedDate() {
        return dtCreatedDate;
    }

    public void setDtCreatedDate(String dtCreatedDate) {
        this.dtCreatedDate = dtCreatedDate;
    }

    public String getDtModifiedDate() {
        return dtModifiedDate;
    }

    public void setDtModifiedDate(String dtModifiedDate) {
        this.dtModifiedDate = dtModifiedDate;
    }

    public Object getDtDeletedDate() {
        return dtDeletedDate;
    }

    public void setDtDeletedDate(Object dtDeletedDate) {
        this.dtDeletedDate = dtDeletedDate;
    }

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Areas> areas) {
        this.areas = areas;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}