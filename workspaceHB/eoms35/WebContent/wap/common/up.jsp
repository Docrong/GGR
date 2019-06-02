<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletRequestContext" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/vnd.wap.wml;charset=UTF-8" language="java" %>
<jsp:useBean id="fUpload" scope="page" class="org.apache.commons.fileupload.FileUpload"/>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
    <card>
        <p>
<%
    try {
        String path = request.getSession().getServletContext().getRealPath("/");
        out.println(path);
    	out.println(request.getParameterMap());
        fUpload.setSizeMax(1000000);
        fUpload.setFileItemFactory(new org.apache.commons.fileupload.disk.DiskFileItemFactory());
        java.util.List items = fUpload.parseRequest(new ServletRequestContext(request));
        for(int i=1;i<items.size()-1;i++){
       
        FileItem fi = (FileItem) items.get(i);
        File uploadFile = new File(path+"/"+fi.getName());
        fi.write(uploadFile);
        
      }
        out.println(request.getSession().getServletContext().getRealPath("/"));
    } catch (Exception ex) {
        ex.printStackTrace(System.out);
        out.println("ERROR~!"+ex);
    }
%>
        </p>
   </card>
    </wml>