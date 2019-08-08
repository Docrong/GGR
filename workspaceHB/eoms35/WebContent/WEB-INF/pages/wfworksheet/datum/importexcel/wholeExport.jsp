<html xmlns:v="urn:schemas-microsoft-com:vml"
      xmlns:o="urn:schemas-microsoft-com:office:office"
      xmlns:x="urn:schemas-microsoft-com:office:excel"
      xmlns="http://www.w3.org/TR/REC-html40">
    <%@page contentType="application/vnd.ms-excel;charset=UTF-8" %>
    <%@page import="java.util.*,com.boco.eoms.datum.webapp.form.TawBureaudataSHlrForExcel" %>
    <%
        ArrayList hlrList = (ArrayList) request.getAttribute("EXCELList");
        int size = hlrList.size();
    %>
    <head>
        <meta http-equiv= Content-Type content="text/html; charset=UTF-8">
        <meta name= ProgId content= Excel.Sheet>
        <meta name= Generator content="Microsoft Excel 11">
        <!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author><%=request.getAttribute("Creator")%></o:Author>
  <o:LastAuthor><%=request.getAttribute("Creator")%></o:LastAuthor>
  <o:Created>2008-05-06T03:24:40Z</o:Created>
  <o:LastSaved>2008-05-08T08:44:05Z</o:LastSaved>
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
                vertical-align:middle;
                white-space:nowrap;
                mso-rotate:0;
                mso-background-source:auto;
                mso-pattern:auto;
                color:windowtext;
                font-size:12.0pt;
                font-weight:400;
                font-style:normal;
                text-decoration:none;
                font-family:宋体;
                mso-generic-font-family:auto;
                mso-font-charset:134;
                border:none;
                mso-protection:locked visible;
                mso-style-name:常规;
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
                font-family:宋体;
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
            .xl24
                {mso-style-parent:style0;
                mso-number-format:"\@";}
            .xl25
                {mso-style-parent:style0;
                mso-number-format:"\@";
                text-align:left;
                vertical-align:bottom;}
            .xl26
                {mso-style-parent:style0;
                font-family:"Times New Roman", serif;
                mso-font-charset:0;}
            .xl27
                {mso-style-parent:style0;
                font-size:11.0pt;
                font-weight:700;}
            ruby
                {ruby-align:left;}
            rt
                {color:windowtext;
                font-size:9.0pt;
                font-weight:400;
                font-style:normal;
                text-decoration:none;
                font-family:宋体;
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
            <x:Name>全省号段归属汇总表</x:Name>
            <x:WorksheetOptions>
             <x:DefaultRowHeight>285</x:DefaultRowHeight>
             <x:Print>
              <x:ValidPrinterInfo/>
              <x:PaperSizeIndex>9</x:PaperSizeIndex>
              <x:HorizontalResolution>200</x:HorizontalResolution>
              <x:VerticalResolution>200</x:VerticalResolution>
             </x:Print>
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
        </xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="1028"/>
</xml><![endif]-->
    </head>

    <body link= blue vlink= purple>

        <table x:str border=0 cellpadding=0 cellspacing=0 width=1169 style='border-collapse:
        collapse;table-layout:fixed;width:876pt'>
        <col width=59 style='mso-width-source:userset;mso-width-alt:1888;width:44pt'>
        <col width=61 style='mso-width-source:userset;mso-width-alt:1952;width:46pt'>
        <col width=72 span=3 style='width:54pt'>
        <col width=92 style='mso-width-source:userset;mso-width-alt:2944;width:69pt'>
        <col width=61 style='mso-width-source:userset;mso-width-alt:1952;width:46pt'>
        <col width=87 style='mso-width-source:userset;mso-width-alt:2784;width:65pt'>
        <col width=147 style='mso-width-source:userset;mso-width-alt:4704;width:110pt'>
        <col width=183 style='mso-width-source:userset;mso-width-alt:5856;width:137pt'>
        <col width=191 style='mso-width-source:userset;mso-width-alt:6112;width:143pt'>
        <col width=72 style='width:54pt'>
        <tr height=19 style='height:14.25pt'>
        <td height=19 width=59 style='height:14.25pt;width:44pt'>
    </td>
    <td width=61 style='width:46pt'>
</td>
    <td width=
72 style='width:54pt'></td>
    <td width=
72 style='width:54pt'></td>
    <td width=
72 style='width:54pt'></td>
    <td width=
92 style='width:69pt'></td>
    <td width=
61 style='width:46pt'></td>
    <td width=
87 style='width:65pt'></td>
    <td width=
147 style='width:110pt'></td>
    <td width=
183 style='width:137pt'></td>
    <td width=
191 style='width:143pt'></td>
    <td width=
72 style='width:54pt'></td>
</tr>

    <tr height=
19 style='height:14.25pt'>
    <td class= xl27>ID</td>
    <td class= xl27>地市</td>
    <td class= xl27>hlr名称</td>
    <td class= xl27></td>
    <td class= xl27></td>
    <td class= xl27>HLR信令点</td>
    <td class= xl27></td>
    <td class= xl27>HLR ID</td>
    <td class= xl27>新增（局数据编号）</td>
    <td class= xl27>地市调整（局数据编号）</td>
    <td class= xl27 colspan=
2 style='mso-ignore:colspan'>HLR调整（地市申请编号）</td>
</tr>
<%
    for (int i = 0; i < size; i++) {
        TawBureaudataSHlrForExcel po = (TawBureaudataSHlrForExcel) hlrList.get(i);
%>
    <tr height=
21 style='height:15.75pt'>
    <td height=
21 style='height:15.75pt'><%=i%></td>
    <td class= xl24><%=po.getCityName()%></td>
    <td><%=po.getHlrName()%></td>
    <td colspan=
2 style='mso-ignore:colspan'></td>
    <td class= xl25><%=po.getHlrSignalId()%></td>
    <td></td>
    <td class= xl26 align= right x:num="<%=po.getSegmentId()%>"><%=po.getSegmentId()%></td>
    <td><%=po.getNewBureauId()%></td>
    <td><%=po.getAdjustBureauId()%></td>
    <td><%=po.getBureaudataSheet()%></td>
    <td></td>
</tr>
<%}%>
    <
![if supportMisalignedColumns]>
    <tr height=
0 style='display:none'>
    <td width=
59 style='width:44pt'></td>
    <td width=
61 style='width:46pt'></td>
    <td width=
72 style='width:54pt'></td>
    <td width=
72 style='width:54pt'></td>
    <td width=
72 style='width:54pt'></td>
    <td width=
92 style='width:69pt'></td>
    <td width=
61 style='width:46pt'></td>
    <td width=
87 style='width:65pt'></td>
    <td width=
147 style='width:110pt'></td>
    <td width=
183 style='width:137pt'></td>
    <td width=
191 style='width:143pt'></td>
    <td width=
72 style='width:54pt'></td>
</tr>
    <
![endif]>
</table>
</body>
</html>
