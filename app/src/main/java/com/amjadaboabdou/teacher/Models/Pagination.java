package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("i_total_objects")
    @Expose
    private Integer iTotalObjects;
    @SerializedName("i_items_on_page")
    @Expose
    private Integer iItemsOnPage;
    @SerializedName("i_per_pages")
    @Expose
    private Integer iPerPages;
    @SerializedName("i_current_page")
    @Expose
    private Integer iCurrentPage;
    @SerializedName("i_total_pages")
    @Expose
    private Integer iTotalPages;

    public Integer getiTotalObjects() {
        return iTotalObjects;
    }

    public void setiTotalObjects(Integer iTotalObjects) {
        this.iTotalObjects = iTotalObjects;
    }

    public Integer getiItemsOnPage() {
        return iItemsOnPage;
    }

    public void setiItemsOnPage(Integer iItemsOnPage) {
        this.iItemsOnPage = iItemsOnPage;
    }

    public Integer getiPerPages() {
        return iPerPages;
    }

    public void setiPerPages(Integer iPerPages) {
        this.iPerPages = iPerPages;
    }

    public Integer getiCurrentPage() {
        return iCurrentPage;
    }

    public void setiCurrentPage(Integer iCurrentPage) {
        this.iCurrentPage = iCurrentPage;
    }

    public Integer getiTotalPages() {
        return iTotalPages;
    }

    public void setiTotalPages(Integer iTotalPages) {
        this.iTotalPages = iTotalPages;
    }
}
