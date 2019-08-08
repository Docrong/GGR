package com.boco.eoms.taglib;

public class LocalList1Bean {
    private String sample = "Start value";

    //Access sample property
    public String getSample() {
        return sample;
    }

    //Access sample property
    public void setSample(String newValue) {
        if (newValue != null) {
            sample = newValue;
        }
    }
}