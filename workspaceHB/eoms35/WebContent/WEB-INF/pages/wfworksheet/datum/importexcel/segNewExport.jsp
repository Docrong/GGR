<html xmlns:o="urn:schemas-microsoft-com:office:office"
      xmlns:x="urn:schemas-microsoft-com:office:excel"
      xmlns="http://www.w3.org/TR/REC-html40">
    <%@page contentType="application/vnd.ms-excel;charset=GBK" %>
    <%@page import="java.util.*,com.boco.eoms.datum.webapp.form.TawBureaudataSHlrForExcel" %>
    <%
        ArrayList hlrList = (ArrayList) request.getAttribute("EXCELList");
        Map headMap = (Map) request.getAttribute("HEADMap");
        int headSize = hlrList.size();
        int size = hlrList.size();
    %>
    <head>
        <meta http-equiv= Content-Type content="text/html; charset=gb2312">
        <meta name= ProgId content= Excel.Sheet>
        <meta name= Generator content="Microsoft Excel 11">
        <!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author><%=request.getAttribute("Creator")%></o:Author>
  <o:LastAuthor><%=request.getAttribute("Creator")%></o:LastAuthor>
  <o:Created>2008-05-14T09:33:10Z</o:Created>
  <o:LastSaved>2008-05-14T09:37:32Z</o:LastSaved>
  <o:Company>BOCO</o:Company>
  <o:Version>11.9999</o:Version>
 </o:DocumentProperties>
</xml><![endif]-->
        <style>
            <!--table
                {mso-displayed-decimal-separator:"\.";
                mso-displayed-thousand-separator:"\,";}
            @page
                {margin:1.0in .75in 1.0in .75in;
                mso-header-margin:.5in;
                mso-footer-margin:.5in;}
            tr
                {mso-height-source:auto;
                mso-ruby-visibility:none;}
            col
                {mso-width-source:auto;
                mso-ruby-visibility:none;}
            br
                {mso-data-placement:same-cell;}
            .style0
                {mso-number-format:General;
                text-align:general;
                vertical-align:bottom;
                white-space:nowrap;
                mso-rotate:0;
                mso-background-source:auto;
                mso-pattern:auto;
                color:windowtext;
                font-size:10.0pt;
                font-weight:400;
                font-style:normal;
                text-decoration:none;
                font-family:Helv, sans-serif;
                mso-font-charset:0;
                border:none;
                mso-protection:locked visible;
                mso-style-name:"";}
            .style0
                {mso-number-format:General;
                text-align:general;
                vertical-align:bottom;
                white-space:nowrap;
                mso-rotate:0;
                mso-background-source:auto;
                mso-pattern:auto;
                color:windowtext;
                font-size:10.0pt;
                font-weight:400;
                font-style:normal;
                text-decoration:none;
                font-family:Helv, sans-serif;
                mso-font-charset:0;
                border:none;
                mso-protection:locked visible;
                mso-style-name:����;
                mso-style-id:0;}
            td
                {mso-style-parent:style0;
                padding-top:1px;
                padding-right:1px;
                padding-left:1px;
                mso-ignore:padding;
                color:windowtext;
                font-size:12.0pt;
                font-weight:400;
                font-style:normal;
                text-decoration:none;
                font-family:����;
                mso-generic-font-family:auto;
                mso-font-charset:134;
                mso-number-format:General;
                text-align:general;
                vertical-align:middle;
                border:none;
                mso-background-source:auto;
                mso-pattern:auto;
                mso-protection:locked visible;
                white-space:nowrap;
                mso-rotate:0;}
            .xl25
                {mso-style-parent:style0;
                font-size:10.0pt;
                font-family:Helv, sans-serif;
                mso-font-charset:0;
                mso-number-format:"\@";
                vertical-align:bottom;
                border:.5pt solid windowtext;}
            .xl26
                {mso-style-parent:style0;
                font-size:10.0pt;
                font-weight:700;
                mso-number-format:"\@";
                text-align:center;
                border:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;}
            .xl27
                {mso-style-parent:style0;
                font-size:10.0pt;
                font-weight:700;
                mso-number-format:"\@";
                vertical-align:bottom;
                border-top:.5pt solid windowtext;
                border-right:none;
                border-bottom:.5pt solid windowtext;
                border-left:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;}
            .xl28
                {mso-style-parent:style0;
                color:red;
                font-size:10.0pt;
                mso-number-format:"\@";
                text-align:center;
                border:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;
                white-space:normal;}
            .xl29
                {mso-style-parent:style0;
                color:black;
                font-size:10.0pt;
                mso-number-format:"\@";
                text-align:center;
                border:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;}
            .xl30
                {mso-style-parent:style0;
                font-size:10.0pt;
                mso-number-format:"\@";
                vertical-align:bottom;
                border:.5pt solid windowtext;}
            .xl31
                {mso-style-parent:style0;
                font-size:10.0pt;
                mso-number-format:"\@";
                vertical-align:bottom;
                border-top:.5pt solid windowtext;
                border-right:none;
                border-bottom:.5pt solid windowtext;
                border-left:.5pt solid windowtext;}
            ruby
                {ruby-align:left;}
            rt
                {color:windowtext;
                font-size:9.0pt;
                font-weight:400;
                font-style:normal;
                text-decoration:none;
                font-family:����;
                mso-generic-font-family:auto;
                mso-font-charset:134;
                mso-char-type:none;
                display:none;}
            -->
        </style>
        <!--[if gte mso 9]><xml>
         <x:ExcelWorkbook>
          <x:ExcelWorksheets>
           <x:ExcelWorksheet>
            <x:Name>data</x:Name>
            <x:WorksheetOptions>
             <x:DefaultRowHeight>285</x:DefaultRowHeight>
             <x:Selected/>
             <x:ProtectContents>False</x:ProtectContents>
             <x:ProtectObjects>False</x:ProtectObjects>
             <x:ProtectScenarios>False</x:ProtectScenarios>
            </x:WorksheetOptions>
           </x:ExcelWorksheet>
          </x:ExcelWorksheets>
          <x:WindowHeight>9570</x:WindowHeight>
          <x:WindowWidth>16035</x:WindowWidth>
          <x:WindowTopX>0</x:WindowTopX>
          <x:WindowTopY>120</x:WindowTopY>
          <x:ProtectStructure>False</x:ProtectStructure>
          <x:ProtectWindows>False</x:ProtectWindows>
         </x:ExcelWorkbook>
        </xml><![endif]-->
    </head>

    <body link= blue vlink= purple>

        <table x:str border=0 cellpadding=0 cellspacing=0 width=540 style='border-collapse:
        collapse;table-layout:fixed;width:406pt'>
        <col width=43 style='mso-width-source:userset;mso-width-alt:1376;width:32pt'>
        <col width=50 style='mso-width-source:userset;mso-width-alt:1600;width:38pt'>
        <col width=71 style='mso-width-source:userset;mso-width-alt:2272;width:53pt'>
        <col width=89 style='mso-width-source:userset;mso-width-alt:2848;width:67pt'>
        <%for (int i = 0; i < headSize; i++) {%>
        <col width=121 style='mso-width-source:userset;mso-width-alt:3872;width:91pt'>
        <%}%>
        <col width=126 style='mso-width-source:userset;mso-width-alt:4032;width:95pt'>
        <col width=40 style='mso-width-source:userset;mso-width-alt:1280;width:30pt'>
        <col width=99 style='mso-width-source:userset;mso-width-alt:3168;width:74pt'>
        <tr height=19 style='height:14.25pt'>
        <td height=19 class=xl26 width=43 style='height:14.25pt;width:32pt'>ʡ��
    </td>
    <td class= xl26 width=50 style='border-left:none;width:38pt'>����
</td>
    <td class= xl26 width=
71 style='border-left:none;width:53pt'>��������</td>
<%
    Set set = headMap.keySet();
    Iterator iter = set.iterator();
    while (iter.hasNext()) {
%>
    <td class= xl27 width=
89 style='border-left:none;width:67pt'><%=iter.next()%></td>
<%}%>
    <td class= xl28 width=
121 style='width:91pt'>����HLR����</td>
    <td class= xl28 width=
126 style='border-left:none;width:95pt'>����HLR�����</td>
    <td class= xl29 width=
40 style='border-left:none;width:30pt'>��ע</td>
</tr>
<%
    for (int i = 0; i < size; i++) {
        TawBureaudataSHlrForExcel po = (TawBureaudataSHlrForExcel) hlrList.get(i);
        String[] segArr = po.getSegArr();
%>
    <tr height=
19 style='height:14.25pt'>
    <td height=
19 class=xl30 style='height:14.25pt;border-top:none'>����</td>
    <td class= xl30 style='border-top:none;border-left:none'><%=po.getCityName()%></td>
    <td class= xl30 style='border-top:none;border-left:none'><%=po.getZoneNum()%></td>
<%for (int j = 0; j < segArr.length; j++) {%>
    <td class= xl31 style='border-top:none;border-left:none'><%=(segArr[j] == null) ? " " : segArr[j]%></td>
<%}%>
    <td class= xl25 style='border-top:none'><%=po.getHlrName()%></td>
    <td class= xl25 style='border-top:none;border-left:none'><%=po.getHlrSignalId()%></td>
    <td class= xl25 style='border-top:none;border-left:none'>��</td>
</tr>
<%}%>
    <
![if supportMisalignedColumns]>
    <tr height=
0 style='display:none'>
    <td width=
43 style='width:32pt'></td>
    <td width=
50 style='width:38pt'></td>
    <td width=
71 style='width:53pt'></td>
    <td width=
89 style='width:67pt'></td>
    <td width=
121 style='width:91pt'></td>
    <td width=
126 style='width:95pt'></td>
    <td width=
40 style='width:30pt'></td>
</tr>
    <
![endif]>
</table>

</body>

</html>
