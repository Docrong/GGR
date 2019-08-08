// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProcessListCommonFaultDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

public class ProcessListCommonFaultDisplaytagDecoratorHelper extends ProcessListDisplaytagDecoratorHelper {

    public ProcessListCommonFaultDisplaytagDecoratorHelper() {
    }

    public String getId() {
        Map taskMap = (HashMap) getCurrentRowObject();
        Object object = getPageContext().getRequest().getAttribute("batchTaskMap");
        System.out.println("�����ظ�" + object);
        String inputStr = "<input name=\"batchIds\" type=\"checkbox\" disabled=\"disabled\"/>";
        if (object != null) {
            HashMap taskNames = (HashMap) object;
            System.out.println(taskMap.get("taskName") + "========" + taskMap.get("subTaskFlag"));
            if (taskNames.get(taskMap.get("taskName")) != null && (taskMap.get("subTaskFlag") == null || taskMap.get("subTaskFlag").equals("false") || taskMap.get("subTaskFlag").equals("")))
                inputStr = "<input name=\"batchIds\" type=\"checkbox\" onclick=\"selectedSelf(this)\" value=\"" + taskMap.get("taskName") + "\" id=\"" + taskMap.get("id") + "\" operateRoleId=\"" + taskMap.get("operateRoleId") + "\" ifwaitforsubtask=\"" + taskMap.get("ifWaitForSubTask") + "\"/>";
        }
        return inputStr;
    }

    public String getTaskDisplayName() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String name = StaticMethod.nullObject2String(taskMap.get("taskDisplayName"));
        if (name.equals("һ������"))
            name = "T1����";
        else if (name.equals("��������"))
            name = "T2����";
        else if (name.equals("��������"))
            name = "T3����";
        if (taskMap.get("subTaskFlag") != null && taskMap.get("subTaskFlag").equals("true"))
            name = name + "(������)";
        return name;
    }

    public String addRowClass() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String mainFaultResponseLevel = StaticMethod.nullObject2String(taskMap.get("mainFaultResponseLevel"));
        String color = "";
        if (!mainFaultResponseLevel.equals(""))
            color = " jl-level-" + mainFaultResponseLevel;
        return super.addRowClass() + color;
    }
}
