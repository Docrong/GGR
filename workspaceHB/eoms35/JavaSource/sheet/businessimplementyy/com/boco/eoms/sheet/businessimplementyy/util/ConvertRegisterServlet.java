package com.boco.eoms.sheet.businessimplementyy.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;

public class ConvertRegisterServlet extends HttpServlet {

    public void init() throws ServletException {
        ConvertUtils.register(new UtilDateConverter(), Date.class);// →注册语句，在struts的核心类ActionServlet中也是这么注册Integer，boolean等转换器的
    }

}
