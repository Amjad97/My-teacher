package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bank {
    @SerializedName("pk_i_id")
    @Expose
    private Integer pkIId;
    @SerializedName("s_title")
    @Expose
    private String sTitle;
    @SerializedName("s_details")
    @Expose
    private String sDetails;
    @SerializedName("s_account_owner")
    @Expose
    private String sAccountOwner;
    @SerializedName("s_account_number")
    @Expose
    private String sAccountNumber;
    @SerializedName("s_iban_number")
    @Expose
    private String sIbanNumber;
    @SerializedName("s_avatar_path")
    @Expose
    private Object sAvatarPath;
    @SerializedName("b_enabled")
    @Expose
    private String bEnabled;
    @SerializedName("dt_created_date")
    @Expose
    private String dtCreatedDate;
    @SerializedName("dt_modified_date")
    @Expose
    private Object dtModifiedDate;
    @SerializedName("dt_deleted_date")
    @Expose
    private Object dtDeletedDate;

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

    public String getsDetails() {
        return sDetails;
    }

    public void setsDetails(String sDetails) {
        this.sDetails = sDetails;
    }

    public String getsAccountOwner() {
        return sAccountOwner;
    }

    public void setsAccountOwner(String sAccountOwner) {
        this.sAccountOwner = sAccountOwner;
    }

    public String getsAccountNumber() {
        return sAccountNumber;
    }

    public void setsAccountNumber(String sAccountNumber) {
        this.sAccountNumber = sAccountNumber;
    }

    public String getsIbanNumber() {
        return sIbanNumber;
    }

    public void setsIbanNumber(String sIbanNumber) {
        this.sIbanNumber = sIbanNumber;
    }

    public Object getsAvatarPath() {
        return sAvatarPath;
    }

    public void setsAvatarPath(Object sAvatarPath) {
        this.sAvatarPath = sAvatarPath;
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

    public Object getDtModifiedDate() {
        return dtModifiedDate;
    }

    public void setDtModifiedDate(Object dtModifiedDate) {
        this.dtModifiedDate = dtModifiedDate;
    }

    public Object getDtDeletedDate() {
        return dtDeletedDate;
    }

    public void setDtDeletedDate(Object dtDeletedDate) {
        this.dtDeletedDate = dtDeletedDate;
    }
}