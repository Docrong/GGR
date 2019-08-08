// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MainListCommonFaultDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.MainListDisplaytagDecoratorHelper;

import java.util.Map;

public class MainListCommonFaultDisplaytagDecoratorHelper extends MainListDisplaytagDecoratorHelper {

    public MainListCommonFaultDisplaytagDecoratorHelper() {
    }

    public String addRowClass() {
        Map Map = (Map) getCurrentRowObject();
        String mainFaultResponseLevel = StaticMethod.nullObject2String(Map.get("mainFaultResponseLevel"));
        String color = "";
        if (!mainFaultResponseLevel.equals(""))
            color = " jl-level-" + mainFaultResponseLevel;
        return super.addRowClass() + color;
    }
}
