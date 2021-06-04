package com.example.geeknews.models;

import com.google.gson.annotations.SerializedName;

public class PostDetails {

 private PostDetails postDetails;

    @SerializedName("contenttype")
    private String type ;
    @SerializedName("title")
    private String title ;
    @SerializedName("url_page")
    private String pageUrl;
    @SerializedName("url_pdf")
    private String pdfUrl ;
    @SerializedName("url_doi")
    private String doiUrl;
    @SerializedName("doi")
    private String doi;
    @SerializedName("publicationdate")
    private String date;
    @SerializedName("abstract")
    private String textAbstract ;
    @SerializedName("category")
    private String category ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getDoiUrl() {
        return doiUrl;
    }

    public void setDoiUrl(String doiUrl) {
        this.doiUrl = doiUrl;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextAbstract() {
        return textAbstract;
    }

    public void setTextAbstract(String textAbstract) {
        this.textAbstract = textAbstract;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
