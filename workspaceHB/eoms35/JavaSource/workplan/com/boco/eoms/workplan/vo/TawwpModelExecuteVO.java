package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划模板执行内容数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.text.*;

public class TawwpModelExecuteVO implements Comparable {

    private String id; // 标识

    private String name; // 作业计划模板执行内容名称

    private String origin; // 来源 0:内部 1:接口

    private String cycle; // 周期

    private String cycleName; // 周期名称

    private String remark; // 执行帮助

    private String note;//备注

    private String format; // 填写格式

    private String validate; // 验证格式

    private String formId; // 执行表格标识

    private String formName; // 执行表格名称

    private String deleted; // 删除标志

    private String cruser; // 创建人

    private String crusername; // 创建人名称

    private String crtime; // 创建时间

    private String serialNo; // 序列号

    private String xlsX; // excel文件位置（横坐标）

    private String xlsY; // excel文件位置（纵坐标）

    private String netTypeId; // 网元类型

    private TawwpAddonsTableVO tawwpAddonsTableVO; // 附加表对象

    private String isHoliday; // 是否假日排班

    private String isWeekend; // 是否周末排班

    private String fileFlag; // 是否上传附件

    private String fileFlagName; // 是否上传附件中文表示

    private String executeMonth;// 执行月份

    private int executeTimes; // 第几月执行

    private String botype; // 业务类型

    private String executedeptlevel; // 执行单位级别

    private String appdesc; // 适用说明

    private String accessories; //指导文件

    private String taskid;
    private String executeDay; //执行日期（由用户选择）

    private String monthFlag;

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetTypeId() {
        if (netTypeId == null) {
            netTypeId = "";
        }

        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    public String getFormId() {
        if (formId == null) {
            formId = "";
        }

        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormat() {
        if (format == null)
            format = "";
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDeleted() {
        if (deleted == null) {
            deleted = "";
        }

        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCycle() {
        if (cycle == null) {
            cycle = "";
        }

        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCruser() {
        if (cruser == null) {
            cruser = "";
        }

        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public String getCrtime() {
        if (crtime == null) {
            crtime = "";
        }

        return crtime;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    public String getOrigin() {
        if (origin == null) {
            origin = "";
        }

        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRemark() {
        if (remark == null) {
            remark = "";
        }

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSerialNo() {
        if (serialNo == null) {
            serialNo = "";
        }

        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getValidate() {
        if (validate == null) {
            validate = "";
        }

        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getXlsX() {
        if (xlsX == null) {
            xlsX = "";
        }

        return xlsX;
    }

    public void setXlsX(String xlsX) {
        this.xlsX = xlsX;
    }

    public String getXlsY() {
        if (xlsY == null) {
            xlsY = "";
        }

        return xlsY;
    }

    public void setXlsY(String xlsY) {
        this.xlsY = xlsY;
    }

    public String getCrusername() {
        if (crusername == null) {
            crusername = "";
        }

        return crusername;
    }

    public void setCrusername(String crusername) {
        this.crusername = crusername;
    }

    public String getCycleName() {
        if (cycleName == null) {
            cycleName = "";
        }

        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public String getFormName() {

        if (formName == null)
            formName = "";
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public TawwpAddonsTableVO getTawwpAddonsTableVO() {
        return tawwpAddonsTableVO;
    }

    public void setTawwpAddonsTableVO(TawwpAddonsTableVO tawwpAddonsTableVO) {
        this.tawwpAddonsTableVO = tawwpAddonsTableVO;
    }

    // 进行排序处理，按周期进行排序
    public int compareTo(Object obj) {
        TawwpModelExecuteVO tawwpModelExecuteVO = (TawwpModelExecuteVO) obj;
        String cycyle1 = null;
        String cycyle2 = null;

        int iRet = 0;

        try {
            cycyle1 = this.getCycle();
            cycyle2 = tawwpModelExecuteVO.getCycle();

            iRet = cycyle1.compareTo(cycyle2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return iRet;
    }

    public String getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    public String getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(String isWeekend) {
        this.isWeekend = isWeekend;
    }

    public String getExecuteMonth() {
        if (executeMonth == null) {
            executeMonth = "";
        }
        return executeMonth;
    }

    public void setExecuteMonth(String executeMonth) {
        this.executeMonth = executeMonth;
    }

    public int getExecuteTimes() {

        return executeTimes;
    }

    public void setExecuteTimes(int executeTimes) {
        this.executeTimes = executeTimes;
    }

    public String getBotype() {
        if (botype == null) {
            botype = "";
        }
        return botype;
    }

    public void setBotype(String botype) {
        this.botype = botype;
    }

    public String getExecutedeptlevel() {
        if (executedeptlevel == null) {
            executedeptlevel = "";
        }
        return executedeptlevel;
    }

    public void setExecutedeptlevel(String executedeptlevel) {
        this.executedeptlevel = executedeptlevel;
    }

    public String getAppdesc() {
        if (appdesc == null) {
            appdesc = "";
        }
        return appdesc;
    }

    public void setAppdesc(String appdesc) {
        this.appdesc = appdesc;
    }

    public String getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag;
    }

    public String getFileFlagName() {
        fileFlagName = "";
        if (fileFlag != null) {
            if (fileFlag.equals("1")) {
                fileFlagName = "是";
            } else if (fileFlag.equals("0")) {
                fileFlagName = "否";
            }
        }

        return fileFlagName;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getExecuteDay() {
        return executeDay;
    }

    public void setExecuteDay(String executeDay) {
        this.executeDay = executeDay;
    }

    public String getMonthFlag() {
        return monthFlag;
    }

    public void setMonthFlag(String monthFlag) {
        this.monthFlag = monthFlag;
    }

    public String getTaskid() {
        return taskid;
    }


    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
