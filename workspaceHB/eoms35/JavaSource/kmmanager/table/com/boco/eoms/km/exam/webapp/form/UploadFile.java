package com.boco.eoms.km.exam.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class UploadFile implements Serializable {
    private FormFile file;

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }
}
