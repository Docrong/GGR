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
  <o:Created>2008-04-07T06:56:38Z</o:Created>
  <o:LastSaved>2008-05-15T03:51:08Z</o:LastSaved>
  <o:Company>wh</o:Company>
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
                font-size:10.0pt;
                font-weight:700;
                mso-number-format:"\@";
                text-align:center;
                border:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;}
            .xl25
                {mso-style-parent:style0;
                font-size:10.0pt;
                font-weight:700;
                mso-number-format:"\@";
                border:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;}
            .xl26
                {mso-style-parent:style0;
                color:red;
                font-size:10.0pt;
                mso-number-format:"\@";
                text-align:center;
                border:.5pt solid windowtext;
                background:silver;
                mso-pattern:auto none;
                white-space:normal;}
            .xl27
                {mso-style-parent:style0;
                mso-number-format:"\@";}
            .xl28
                {mso-style-parent:style0;
                font-size:10.0pt;
                mso-number-format:"\@";
                border:.5pt solid windowtext;}
            .xl29
                {mso-style-parent:style0;
                border:.5pt solid windowtext;}
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
            <x:Name>data</x:Name>
            <x:WorksheetOptions>
             <x:DefaultRowHeight>285</x:DefaultRowHeight>
             <x:Selected/>
             <x:Panes>
              <x:Pane>
               <x:Number>3</x:Number>
               <x:ActiveRow>10</x:ActiveRow>
               <x:ActiveCol>6</x:ActiveCol>
              </x:Pane>
             </x:Panes>
             <x:ProtectContents>False</x:ProtectContents>
             <x:ProtectObjects>False</x:ProtectObjects>
             <x:ProtectScenarios>False</x:ProtectScenarios>
            </x:WorksheetOptions>
           </x:ExcelWorksheet>
          </x:ExcelWorksheets>
          <x:WindowHeight>8415</x:WindowHeight>
          <x:WindowWidth>14715</x:WindowWidth>
          <x:WindowTopX>360</x:WindowTopX>
          <x:WindowTopY>360</x:WindowTopY>
          <x:ProtectStructure>False</x:ProtectStructure>
          <x:ProtectWindows>False</x:ProtectWindows>
         </x:ExcelWorkbook>
        </xml><![endif]-->
    </head>

    <body link= blue vlink= purple>

        <table x:str border=0 cellpadding=0 cellspacing=0 width=775 style='border-collapse:
        collapse;table-layout:fixed;width:582pt'>
        <col width=40 style='mso-width-source:userset;mso-width-alt:1280;width:30pt'>
        <col width=82 style='mso-width-source:userset;mso-width-alt:2624;width:62pt'>
        <col width=107 style='mso-width-source:userset;mso-width-alt:3424;width:80pt'>
        <col width=82 style='mso-width-source:userset;mso-width-alt:2624;width:62pt'>
        <col width=113 style='mso-width-source:userset;mso-width-alt:3616;width:85pt'>
        <%for (int i = 0; i < headSize; i++) {%>
        <col width=96 style='mso-width-source:userset;mso-width-alt:3072;width:72pt'>
        <%}%>
        <col width=115 style='mso-width-source:userset;mso-width-alt:3680;width:86pt'>
        <col width=140 style='mso-width-source:userset;mso-width-alt:4480;width:105pt'>
        <tr class= xl27 height=19 style='height:14.25pt'>
        <td height=19 class=xl24 width=40 style='height:14.25pt;width:30pt'>省份
    </td>
    <td class= xl24 width=82 style='border-left:none;width:62pt'>原归属城市
</td>
    <td class= xl24 width=
107 style='border-left:none;width:80pt'>原归属城市区号</td>
    <td class= xl25 width=
82 style='border-left:none;width:62pt'>现归属城市</td>
    <td class= xl25 width=
113 style='border-left:none;width:85pt'>现归属城市区号</td>
<%
    Set set = headMap.keySet();
    Iterator iter = set.iterator();
    while (iter.hasNext()) {
%>
    <td class= xl25 width=
96 style='border-left:none;width:72pt'><%=iter.next()%></td>
<%}%>
    <td class= xl26 width=
115 style='border-left:none;width:86pt'>归属HLR名称</td>
    <td class= xl26 width=
140 style='border-left:none;width:105pt'>归属HLR信令点</td>
</tr>
<%
    for (int i = 0; i < size; i++) {
        TawBureaudataSHlrForExcel po = (TawBureaudataSHlrForExcel) hlrList.get(i);
        String[] segArr = po.getSegArr();
%>
    <tr class= xl27 height=
19 style='height:14.25pt'>
    <td height=
19 class=xl28 style='height:14.25pt;border-top:none'>湖北</td>
    <td class= xl28 style='border-top:none;border-left:none'><%=po.getPrecityName()%></td>
    <td class= xl28 style='border-top:none;border-left:none'><%=po.getPrezoneNum()%></td>
    <td class= xl28 style='border-top:none;border-left:none'><%=po.getCityName()%></td>
    <td class= xl28 style='border-top:none;border-left:none'><%=po.getZoneNum()%></td>
<%for (int j = 0; j < segArr.length; j++) {%>
    <td class= xl28 style='border-top:none;border-left:none'><%=(segArr[j] == null) ? " " : segArr[j]%></td>
<%}%>
    <td class= xl29 style='border-top:none;border-left:none'><%=po.getHlrName()%></td>
    <td class= xl29 style='border-top:none;border-left:none'><%=po.getHlrSignalId()%></td>
</tr>
<%}%>
    <
![if supportMisalignedColumns]>
    <tr height=
0 style='display:none'>
    <td width=
40 style='width:30pt'></td>
    <td width=
82 style='width:62pt'></td>
    <td width=
107 style='width:80pt'></td>
    <td width=
82 style='width:62pt'></td>
    <td width=
113 style='width:85pt'></td>
    <td width=
96 style='width:72pt'></td>
    <td width=
115 style='width:86pt'></td>
    <td width=
140 style='width:105pt'></td>
</tr>
    <
![endif]>
</table>

</body>

</html>
