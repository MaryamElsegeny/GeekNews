package com.example.geeknews.models;

public class RadioButtonModel {

    private String radBtnType;
    private String radBtnDate;
    private String radBtnDateStart;
    private String radBtnDateEnd;
    private Boolean checkObserve = false ;

    public Boolean getCheckObserve() {
        return checkObserve;
    }

    public void setCheckObserve(Boolean checkObserve) {
        this.checkObserve = checkObserve;
    }

    public RadioButtonModel() {
    }

    public RadioButtonModel(String radBtnType) {
        this.radBtnType = radBtnType;
    }

    public RadioButtonModel(String radBtnDate, String radBtnDateStart, String radBtnDateEnd) {
        this.radBtnDate = radBtnDate;
        this.radBtnDateStart = radBtnDateStart;
        this.radBtnDateEnd = radBtnDateEnd;
    }

    public String getRadBtnType() {
        return radBtnType;
    }

    public void setRadBtnType(String radBtnType) {
        this.radBtnType = radBtnType;
    }

    public String getRadBtnDate() {
        return radBtnDate;
    }

    public void setRadBtnDate(String radBtnDate) {
        this.radBtnDate = radBtnDate;
    }

    public String getRadBtnDateStart() {
        return radBtnDateStart;
    }

    public void setRadBtnDateStart(String radBtnDateStart) {
        this.radBtnDateStart = radBtnDateStart;
    }

    public String getRadBtnDateEnd() {
        return radBtnDateEnd;
    }

    public void setRadBtnDateEnd(String radBtnDateEnd) {
        this.radBtnDateEnd = radBtnDateEnd;
    }
}
