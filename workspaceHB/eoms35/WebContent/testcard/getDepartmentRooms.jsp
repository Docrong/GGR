<%@ page contentType="text/html;charset=GB2312"%>
<%@ page import="java.lang.StringBuffer;"%>

<%
    
     StringBuffer xmlString=new StringBuffer();
     String department_Id=request.getParameter("selectedId");
     if (department_Id.equals("-1")) {
		 	xmlString.append("<SELECT id=\"roomId=\" name=\"roomId\" err=\"0\" des=\"ok\">");
			xmlString.append("<option value=\"-1\">��ѡ��</option>");
			xmlString.append("</SELECT>");
		
     }else{
    			xmlString.append("<SELECT id=\"roomId=\" name=\"roomId\" err=\"0\" des=\"ok\">");
			xmlString.append("<option value=\"-1\">��ѡ��</option>");
			xmlString.append("<option value=\"0\"> ��������0</option>"); 
                        xmlString.append("<option value=\"1\"> ��������1</option>");   	    	                
                        xmlString.append("</SELECT>");
     }
	
         out.print(xmlString);
        System.out.println(xmlString.toString());
%>
