package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slider {

    @SerializedName("pk_i_id")
    @Expose
    private Integer pkIId;
    @SerializedName("s_title")
    @Expose
    private String sTitle;
    @SerializedName("s_details")
    @Expose
    private Object sDetails;
    @SerializedName("s_link")
    @Expose
    private String sLink;
    @SerializedName("s_avatar_path")
    @Expose
    private String sAvatarPath;
    @SerializedName("i_order")
    @Expose
    private Object iOrder;
    @SerializedName("b_enabled")
    @Expose
    private String bEnabled;
    @SerializedName("dt_created_date")
    @Expose
    private Object dtCreatedDate;
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

    public Object getsDetails() {
        return sDetails;
    }

    public void setsDetails(Object sDetails) {
        this.sDetails = sDetails;
    }

    public String getsLink() {
        return sLink;
    }

    public void setsLink(String sLink) {
        this.sLink = sLink;
    }

    public String getsAvatarPath() {
        return sAvatarPath;
    }

    public void setsAvatarPath(String sAvatarPath) {
        this.sAvatarPath = sAvatarPath;
    }

    public Object getiOrder() {
        return iOrder;
    }

    public void setiOrder(Object iOrder) {
        this.iOrder = iOrder;
    }

    public String getbEnabled() {
        return bEnabled;
    }

    public void setbEnabled(String bEnabled) {
        this.bEnabled = bEnabled;
    }

    public Object getDtCreatedDate() {
        return dtCreatedDate;
    }

    public void setDtCreatedDate(Object dtCreatedDate) {
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
