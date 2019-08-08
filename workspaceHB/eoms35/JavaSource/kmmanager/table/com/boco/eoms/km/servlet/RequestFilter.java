package com.boco.eoms.km.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.boco.eoms.km.servlet.context.RequestContext;

public class RequestFilter implements Filter {

    public void destroy() {
        System.out.println("KnowledgeFilter.java destroy()");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter()");

        RequestContext reqContext = new RequestContext();
        Enumeration names = request.getParameterNames();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String values[] = request.getParameterValues(name);

            if (values.length == 0)
                reqContext.setEntityValue2(name, "");
            else if (values.length <= 1)
                reqContext.setEntityValue2(name, values[0]);
            else
                reqContext.setEntityValues(name, values);

            System.out.println("name = " + name + " | value = " + values);
            System.out.println("====================================================");
        }

        request.setAttribute(Constants.CONTAINER_REQUEST_CONTEXT, reqContext);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("KnowledgeFilter.java init()");
    }

}