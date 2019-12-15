package com.guidi.collegesearch.backCode.util;

import java.util.HashMap;

public enum Degree {
    NO_DEGREE(0,"Nothing :)"),
    CERTIFICATE(1,"Certificate"),
    ASSOCIATE(2,"Associate"),
    BACHELOR(3,"Bachelor"),
    GRADUATE(4,"Graduate");
    private int dNum;
    private String dName;
    Degree(int dNum, String dName) {
        this.dNum = dNum;
        this.dName = dName;
    }
    public String getdNameByKey(int i){
        switch(i){
            case 0:
                return NO_DEGREE.dName;
            case 1:
                return CERTIFICATE.dName;
            case 2:
                return ASSOCIATE.dName;
            case 3:
                return BACHELOR.dName;
            case 4:
                return GRADUATE.dName;
            default:
                break;
        }
        return null;
    }
}
