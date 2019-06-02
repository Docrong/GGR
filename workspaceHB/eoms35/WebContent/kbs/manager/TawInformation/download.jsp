<%@ page contentType="text/html;charset=gb2312"
import="com.boco.eoms.gzjh.util.*,com.boco.eoms.common.util.StaticMethod" %>
<%
String temp = request.getParameter("name");
for(int i=0;i<temp.length();i++)
{
  if(temp.substring(i,i+1).equals("_")){
    temp=temp.substring(i+1,temp.length());
    break;
    }
  }
String nametemp = StaticMethod.null2String(request.getParameter("fileatt"));
SmartUpload su = new SmartUpload();
su.initialize(pageContext);
su.setContentDisposition(null);

su.downloadFile("kbs/upload/" + temp,"",nametemp);
%>
