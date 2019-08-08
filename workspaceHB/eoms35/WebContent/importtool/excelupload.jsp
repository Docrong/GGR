<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import="java.text.*,java.util.Date" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.lang.*" %>
<%@page import="com.boco.eoms.common.util.*" %>
<%@ page import="com.pud.study.file.*" %>
<jsp:useBean id="myUpload" scope="page" class="com.pud.study.file.SmartUpload"/>
<%
    try {


        String[] filename = new String[4];
        int[] filesize = new int[4];
        int filecount = 0;
        myUpload.initialize(pageContext);
        myUpload.upload();
        String year1 = myUpload.getRequest().getParameter("s_year");
        String month1 = myUpload.getRequest().getParameter("s_month");
        String date1 = myUpload.getRequest().getParameter("s_date");
        String my_date = year1 + "-" + month1 + "-" + date1;
        String excelname = myUpload.getRequest().getParameter("excelname") + ".xls";

        java.io.File ff = new java.io.File("");
        String savepath = ff.getAbsolutePath() + ff.separator + "xls" + ff.separator;


        out.println(savepath);

        com.pud.study.file.File myfile;
        for (int i = 0; i < myUpload.getFiles().getCount(); i++) {

            if (!myUpload.getFiles().getFile(i).isMissing()) {
                filename[filecount] = myUpload.getFiles().getFile(i).getFileName();
                filesize[filecount] = myUpload.getFiles().getFile(i).getSize();
                myfile = myUpload.getFiles().getFile(i);
                String fileext_1 = myfile.getFileExt();
                if (fileext_1.equals("xls")) {

                    myfile.saveAs(savepath + ff.separator + excelname);

                    filecount++;
                    out.println("���ڵ���,��ȴ�...");

                    out.println("<meta http-equiv='Refresh' content='0;url=exceldb.jsp?my_date=" + my_date + "&excelname=" + excelname + "'>");

                } else {

%>
<SCRIPT>
    alert('���ύ�Ĳ���EXCEL �ĵ���ϵͳ�����Ͽɣ���')
</script>
<script>
    window.location.replace("index.jsp");
</SCRIPT>
<%
            }
        }

    }
%>
<%
    }
//�������ʱ����������Ӧ�Ĵ�����Ϣ
    catch (Exception e) {
        e.printStackTrace();
        out.print("�ϴ��ļ����ִ������Ժ�����һ��");
    }
%>
