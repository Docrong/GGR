package com.boco.eoms.extra.supplierkpi.report.statistic.entity;

public class StatisticEntity {
    String name;
    float value;

    public StatisticEntity() {
        this.name = "";
        this.value = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        StatisticEntity validate = (StatisticEntity) obj;
        if (this.name.equals(validate.getName())) {
            return true;
        } else {
            return false;
        }
    }


}
