<%@page contentType="text/html;charset=gb2312" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.resmanage.entity.*" %>
<%@page import="com.boco.eoms.resmanage.operator.*" %>
<%@page import="mcs.common.db.*" %>
<%@include file="../power.jsp" %>
<%
    /**
     *@ E-DIS (四川省)
     *@ Copyright : (c) 2003
     *@ Company : BOCO.
     *@ 显示资源列表信息
     *@ version 1.0
     **/
%>
<%
    request.setCharacterEncoding("GBK");
    String cityId = null;
    if (request.getParameter("cityid") != null)
        cityId = request.getParameter("cityid");
    else
        cityId = ug.getCity() + "";
    String pi_id = null;
    if (request.getParameter("pi_id") != null)
        pi_id = request.getParameter("pi_id");
    else
        pi_id = "0";
    int flag = 0;
    String spage = "editSave.jsp";
    String ErrMsg = null;
    if (request.getParameter("ErrMsg") != null)
        ErrMsg = request.getParameter("ErrMsg");
    if (ErrMsg != null) {
        out.println("<script>alert('" + ErrMsg + "');history.go(-2)</script>");
    }
    String sId = null;
    if (request.getParameter("id") != null)
        sId = request.getParameter("id");
    else
        sId = "2";

    String devType = null;
    if (request.getParameter("type") != null)
        devType = request.getParameter("type");
    else
        devType = "0";
    String classid = null;
    if (request.getParameter("class") != null)
        classid = request.getParameter("class");
    else
        classid = "0";
	/*if (devType.equals("33") && classid.equals("0"))
	{
		flag = 1;
		spage = "conditionSelect.jsp";
		//response.sendRedirect("conditionSelect.jsp");
		//return;
	}*/
    //out.println("dev type is:"+devType);

%>
<html>
<head>

    <script language=javascript>

        function formsubmit() {
            editform.submit();
        }

        function clearinput() {
            editform.reset();
        }
    </script>

    <title>添加资源</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<%
    if (flag == 1)
        out.println("<body  onload=formsubmit()>");
    else
        out.println("<body>");
%>
<form name="editform" action='<%=spage%>' method=post>
    <%
        Date date = new Date();
        String SysTime = mtools.CovDate(date);
        entityoperate Entity = new entityoperate();
        syscolindex SysColIndex = new syscolindex();
        //得到位置信息
        systabindex SysTab = new systabindex();
        Vector TabVect = new Vector();
        TabVect = Entity.getTabinfor(sId);
        String tabname = null;
        String cc_location = "";
        for (int i = 0; i < TabVect.size(); i++) {
            SysTab = (systabindex) TabVect.get(i);
            tabname = StaticMethod.dbNull2String(SysTab.getCc_name());
            cc_location = StaticMethod.dbNull2String(SysTab.getCc_location());
        }
        out.println("<tr>");
        out.println("<td align=left width='50%' class='table_title'>" + cc_location + "->" + tabname + "的添加</td></tr>");
        Vector SysVect = new Vector();
        SysVect = Entity.getcolinfor(sId);
        int colNum = SysVect.size();                //列信息

        if (colNum != 0) {
            coldata colData = new coldata();
            /******************	构造内容	*****************/
            out.println("<br><table class='table_show' border=0 cellspacing=1 width='100%'>");
            int colorCounter = 0;
            for (int Col = 1; Col < SysVect.size(); Col++) {
                SysColIndex = (syscolindex) SysVect.get(Col);

                int ref_flag = SysColIndex.getCi_ref_flag();
                int ass_flag = SysColIndex.getCi_ass_flag();
                int nul_flag = SysColIndex.getCi_nul_flag();
                int read_flag = SysColIndex.getCi_read_flag();
                int len = 0;
                String cc_type = SysColIndex.getCc_type();
                // out.println("cc_code is: "+SysColIndex.getCc_code());
                int pos1 = cc_type.indexOf("(");
                int pos2 = cc_type.indexOf(")");

                if (pos1 > 0 && pos2 > 0) {
                    len = Integer.parseInt(cc_type.substring(pos1 + 1, pos2));

                }

                if (ass_flag != 1 && ass_flag != 2) {
                    if (!SysColIndex.getCc_code().equals("fi_deviceclass")) {
                        colorCounter++;
                        out.println("<tr class='tr_show'>");
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + StaticMethod.dbNull2String(SysColIndex.getCc_name()) + "</font></td>");
                    }
                }
                //println  input text
                switch (ref_flag) {
                    case 0:
                        String strTemp = "<input type=text  name=" + SysColIndex.getCc_code() + ">";
                        if (SysColIndex.getCc_code().equals("fi_deviceclass") || SysColIndex.getCc_code().equals("fi_device")) {
                            strTemp = "<input type=hidden  name=" + SysColIndex.getCc_code() + ">";
                        } else {
                            if (len > 0) {
                                strTemp = "<input type=text  name=" + SysColIndex.getCc_code() + "     maxlength=" + len + ">";
                            }
                        }
                        if (read_flag == 1) {
                            strTemp = "<input type=hidden  name=" + SysColIndex.getCc_code() + "> ";
                        }
                        if (nul_flag == 1) {
                            strTemp += "*必填";
                        }
                        if (cc_type.equalsIgnoreCase("date")) {
                            strTemp += "日期格式:yyyy-mm-dd";
                        }
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + strTemp + "</font></td>");
                        break;
                    //drop down control
                    case 1:
                        if (SysColIndex.getCc_code().equals("fi_deviceclass")) {
                            out.println("<input type=hidden value=" + devType + " name=" + SysColIndex.getCc_code() + ">");
                        }
                        if (SysColIndex.getCc_code().equals("fi_device")) {
					   /*if (devType.equals("33"))
						{
							//out.println("<td>"+"asfsdf"+"</td>");
							out.println("<input  type=hidden name="+SysColIndex.getCc_code()+" value="+pi_id+">");
							out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
							String tempname = "";
							tempname = Entity.getNameValueByTabId(devType,pi_id);
							out.println(tempname);
							out.println("</font></td>");
						}
						else
						{*/
                            out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
                            out.println("<select name=" + SysColIndex.getCc_code() + ">");
                            String TabName = Entity.getTableName(devType);
                            Vector TabVec = new Vector();
                            TabClass tabclass = new TabClass();
                            TabVec = Entity.getTabVecByTabName(TabName, cityId);
                            for (int i = 0; i < TabVec.size(); i++) {
                                tabclass = (TabClass) TabVec.get(i);
                                out.println("<option  value=" + tabclass.getPi_id() + ">" + StaticMethod.dbNull2String(tabclass.getCc_name()) + "</option>");
                            }
                            out.println("</select>");
                            out.println("</font></td>");
                        }
                        //}
                        if (!SysColIndex.getCc_code().equals("fi_device") && !SysColIndex.getCc_code().equals("fi_deviceclass")) {
                            out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>");
                            out.println("<select name=" + SysColIndex.getCc_code() + ">");

                            refcoldata RefColData = new refcoldata();

                            Vector RefColVec = new Vector();

                            //得到参考列的id,name的value
                            RefColVec = Entity.getrefColValueByCity(Integer.toString(SysColIndex.getPi_id()), "0", cityId);
                            for (int i = 0; i < RefColVec.size(); i++) {
                                int idvalue = ((refcoldata) RefColVec.elementAt(i)).getPi_idvalue();
                                String namevalue = ((refcoldata) RefColVec.elementAt(i)).getCc_namevalue();
                                out.println("<option  value=" + idvalue + ">" + StaticMethod.dbNull2String(namevalue) + "</option>");
                            }
                            out.println("</select>");
                            out.println("</font></td>");
                        }
                        break;
                    case 2:
                        out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>" + "<textarea name=" + SysColIndex.getCc_code() + "></textarea>" + "</font></td>");
                        break;
                    //拓扑图的连接
				/*case 3:
					String topoName = Entity.getTopoName(Integer.toString(SysColIndex.getPi_id()));
				    if (topoName==null)
						topoName = "";
                    out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input type=hidden name="+SysColIndex.getCc_code()+" value="+topoName+"></input>"+"</font></td>");
					break;
					
			    //文件和图片
				case 4:
					break;
				//记录情况
				case 5:
					out.println("<td align=center><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>"+"<input type=hidden name="+SysColIndex.getCc_code()+" value='' "+"></input>"+"</font></td>");
					break;*/
                    default:
                        break;
                }
                if (ass_flag != 1 && ass_flag != 2) out.println("</tr>");
            }
        } else
            out.println("<br><br><br><br><font size=2 color=#000000 face='Verdana, Arial, Helvetica, sans-serif'>No Info at This Id : " + sId + "</font>");
    %>
    </table>
    <br>
    <table width='100%'>
        <tr>
            <td align=center width='100%'>
                <table>
                    <tr>
                        <td width=60 align=center><input type="button" name="b_001" value="保  存" alt="保存录入的数据"
                                                         onclick="javascript:formsubmit()" class="clsbtn2"></td>
                        <td width=20></td>
                        <td width=60 align=center><input type="button" name="b_001" value="取  消" alt="取消录入的数据"
                                                         onclick="javascript:clearinput()" class="clsbtn2"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <input type="hidden" name="id" value=<%=sId%>></input>
        <input type="hidden" name="OptType" value="1"></input>
    </table>
</form>
</body>
</html>
