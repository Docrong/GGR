<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.boco.eoms.resmanage.entity.*"%>
<%@ page import="com.boco.eoms.resmanage.excel.*"%>
<%@ page import="java.util.*,java.io.File,com.jspsmart.upload.*"%>
<jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />
<% 
	String sId=request.getParameter("sId");
	String cityId=request.getParameter("cityId");
   // Initialization
	mySmartUpload.initialize(pageContext);
	mySmartUpload.setMaxFileSize(800000000);
	mySmartUpload.upload();
	String imageName = mySmartUpload.getFiles().getFile(0).getFileName();
	String fileName="";
	String extendName="";
    int succUpload=0;
    int succInsert=0;
	String separator=File.separator;
	String pathName = util.getUploadDir();
	pathName+=separator+"tmp"+cityId+".xls";
    //String pathName="/tmp/tmp"+cityId+".xls";
    File file = new File(pathName);
//upload file
    	try {
		    mySmartUpload.getFiles().getFile(0).saveAs(pathName);
		    succUpload=1;
	    }
		catch(Exception ex){ex.printStackTrace();}
	if (succUpload == 1) {
	         excel xls = new excel();
	         boolean b_success = xls.ExcelToDB(sId,pathName);
	         if (b_success){
	            out.println("<script>");
	            out.println("alert('导入数据成功');");
	            out.println("window.close();");
	            out.println("</script>");
	            }
	          else{
	             out.println("<script>");
	             out.println("alert('导入不成功，请检查您的数据');");
	             out.println("window.close();");
	             out.println("</script>");
	        } 
  	  }else{
	          out.println("<script>");
                  out.println("alert('网络故障，请检查你的网络情况！')");
                  out.println("</script>");
                  }
	    
	/*excel xls = new excel();
	boolean b_success = xls.ExcelToDB(sId,"e:\\wzx\\tmp.xls");
	if (b_success)
	  out.println("excel import success!!");*/
%>
