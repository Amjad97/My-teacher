package com.amjadaboabdou.teacher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;
    @SerializedName("mainGrades")
    @Expose
    private List<MainGrade> mainGrades = null;
    @SerializedName("banks")
    @Expose
    private List<Bank> banks = null;
    @SerializedName("slider")
    @Expose
    private List<Slider> slider = null;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("rules")
    @Expose
    private String rules;
    @SerializedName("suggestion_word")
    @Expose
    private String suggestionWord;
    @SerializedName("pay_page_word")
    @Expose
    private String payPageWord;
    @SerializedName("search_page_word")
    @Expose
    private String searchPageWord;
    @SerializedName("contact_page_word")
    @Expose
    private String contactPageWord;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<MainGrade> getMainGrades() {
        return mainGrades;
    }

    public void setMainGrades(List<MainGrade> mainGrades) {
        this.mainGrades = mainGrades;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Slider> getSlider() {
        return slider;
    }

    public void setSlider(List<Slider> slider) {
        this.slider = slider;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getSuggestionWord() {
        return suggestionWord;
    }

    public void setSuggestionWord(String suggestionWord) {
        this.suggestionWord = suggestionWord;
    }

    public String getPayPageWord() {
        return payPageWord;
    }

    public void setPayPageWord(String payPageWord) {
        this.payPageWord = payPageWord;
    }

    public String getSearchPageWord() {
        return searchPageWord;
    }

    public void setSearchPageWord(String searchPageWord) {
        this.searchPageWord = searchPageWord;
    }

    public String getContactPageWord() {
        return contactPageWord;
    }

    public void setContactPageWord(String contactPageWord) {
        this.contactPageWord = contactPageWord;
    }
}
