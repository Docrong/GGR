package com.boco.eoms.sheet.branchindexreduction.webapp.form;

import org.apache.struts.upload.FormFile;
import com.boco.eoms.base.webapp.form.BaseForm;

public class FileUploadForm extends BaseForm {
    private FormFile files;

    public FormFile getFiles() {
        return files;
    }

    public void setFiles(FormFile files) {
        this.files = files;
    }

}
