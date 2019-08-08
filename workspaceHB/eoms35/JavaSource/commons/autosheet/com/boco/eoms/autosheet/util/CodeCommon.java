package com.boco.eoms.autosheet.util;

/**
 * <p>Title: 值班管理后台配置系统</p>
 * <p>Description: 值班管理后台配置系统</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class CodeCommon {

    public CodeCommon() {
    }

    /**
     * 返回输入框文字
     */
    public String getInputType(int inputtypecode) {
        String inputtype = "";
        switch (inputtypecode) {
            case 0:
                inputtype = "TextField";
                break;
            case 1:
                inputtype = "RadioBox";
                break;
            case 2:
                inputtype = "CheckBox";
                break;
            case 3:
                inputtype = "Select";
                break;
            case 4:
                inputtype = "MulSelect";
                break;
            case 5:
                inputtype = "TextArea";
                break;
            case 7:
                inputtype = "UserSelect";
                break;

        }
        return inputtype;
    }

    /*** 返回存储类别str*/
    public String getStoreType(String storetype) {
        String stystr = "";
        if (storetype.equals("varchar(4000)"))
            stystr = "LongString";
        else if (storetype.equals("varchar(255)"))
            stystr = "String";
        else if (storetype.equals("date"))
            stystr = "Date";
        else if (storetype.equals("date"))
            stystr = "Date";
        else if (storetype.equals("integer"))
            stystr = "Integer";
        else if (storetype.equals("float"))
            stystr = "Float";
        else if (storetype.equals("text"))
            stystr = "Text";

        return stystr;
    }

    /**
     * 返回控制vctrlstr
     */
    public String getVctrl(int vctrl) {
        String vctrlstr = "";
        if (vctrl == 0)
            vctrlstr = "非空";
        else
            vctrlstr = "允许空";
        return vctrlstr;
    }

}
