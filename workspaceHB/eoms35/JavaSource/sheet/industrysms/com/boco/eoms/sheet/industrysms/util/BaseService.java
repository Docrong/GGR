package com.boco.eoms.sheet.industrysms.util;


import javax.servlet.http.HttpServletRequest;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

public class BaseService {
    public String getClientIp() {
        MessageContext mc = MessageContext.getCurrentContext();
        HttpServletRequest request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
        System.out.println("remote ip: " + request.getRemoteAddr());
        return request.getRemoteAddr();
    }
}
