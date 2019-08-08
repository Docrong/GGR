
package com.boco.eoms.sheet.netFault.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;

public class NetFaultMain extends BaseMain {
    private String specialty;
    private String taskText;


    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}
