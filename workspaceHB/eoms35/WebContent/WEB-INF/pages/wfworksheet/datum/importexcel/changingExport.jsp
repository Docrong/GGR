<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<%@page contentType="application/vnd.ms-excel;charset=GBK" %>
<%@page import="java.util.*,com.boco.eoms.datum.webapp.form.TawBureaudataSHlrForExcel"%>
<%
Map excelMap = (Map)request.getAttribute("EXCELMap");
Map headMap = (Map)request.getAttribute("HEADMap");
int headSize = headMap.size();
Iterator iter = null;
%>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 11">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author><%=request.getAttribute("Creator")%></o:Author>
  <o:LastAuthor><%=request.getAttribute("Creator")%></o:LastAuthor>
  <o:Created>2008-05-14T09:33:10Z</o:Created>
  <o:LastSaved>2008-05-19T03:56:38Z</o:LastSaved>
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
.font0
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Helv, sans-serif;
	mso-font-charset:0;}
.font8
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
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
	mso-style-name:常规;
	mso-style-id:0;}
td
	{mso-style-parent:style0;
	padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Helv, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:none;
	mso-background-source:auto;
	mso-pattern:auto;
	mso-protection:locked visible;
	white-space:nowrap;
	mso-rotate:0;}
.xl24
	{mso-style-parent:style0;
	font-weight:700;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:silver;
	mso-pattern:auto none;}
.xl25
	{mso-style-parent:style0;
	font-weight:700;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:silver;
	mso-pattern:auto none;}
.xl26
	{mso-style-parent:style0;
	font-weight:700;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:silver;
	mso-pattern:auto none;}
.xl27
	{mso-style-parent:style0;
	color:black;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:silver;
	mso-pattern:auto none;}
.xl28
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;}
.xl29
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;}
.xl30
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;}
.xl31
	{mso-style-parent:style0;
	mso-number-format:"\@";
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;}
.xl32
	{mso-style-parent:style0;
	mso-number-format:"\@";
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;}
.xl33
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;}
.xl34
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid black;
	border-left:.5pt solid windowtext;}
.xl35
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:silver;
	mso-pattern:auto none;
	white-space:normal;}
.xl36
	{mso-style-parent:style0;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:silver;
	mso-pattern:auto none;
	white-space:normal;}
.xl37
	{mso-style-parent:style0;
	color:#FF6600;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:silver;
	mso-pattern:auto none;
	white-space:normal;}
.xl38
	{mso-style-parent:style0;
	color:#FF6600;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:silver;
	mso-pattern:auto none;
	white-space:normal;}
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
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
      <x:HorizontalResolution>200</x:HorizontalResolution>
      <x:VerticalResolution>200</x:VerticalResolution>
     </x:Print>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>8</x:ActiveRow>
       <x:ActiveCol>9</x:ActiveCol>
      </x:Pane>
     </x:Panes>
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

<body link=blue vlink=purple>

<table x:str border=0 cellpadding=0 cellspacing=0 width=1058 style='border-collapse:collapse;table-layout:fixed;width:796pt'>
 <col width=38 style='mso-width-source:userset;mso-width-alt:1389;width:29pt'>
 <col width=44 style='mso-width-source:userset;mso-width-alt:1609;width:33pt'>
 <col width=62 style='mso-width-source:userset;mso-width-alt:2267;width:47pt'>
<%for(int i=0;i<headSize;i++){%>
 <col width=96 style='mso-width-source:userset;mso-width-alt:3072;width:72pt'>
<%}%>
 <col width=124 style='mso-width-source:userset;mso-width-alt:4534;width:93pt'>
 <col width=126 style='mso-width-source:userset;mso-width-alt:4608;width:95pt'>
 <col width=116 style='mso-width-source:userset;mso-width-alt:4242;width:87pt'>
 <col width=131 style='mso-width-source:userset;mso-width-alt:4790;width:98pt'>
 <col width=204 style='mso-width-source:userset;mso-width-alt:7460;width:153pt'>
 <col width=106 span=11 style='mso-width-source:userset;mso-width-alt:3876;width:80pt'>
 <tr height=17 style='height:12.75pt'>
  <td height=17 class=xl24 width=38 style='height:12.75pt;width:29pt'>省份</td>
  <td class=xl25 width=44 style='width:33pt'>城市</td>
  <td class=xl25 width=62 style='width:47pt'>城市区号</td>
<%iter = headMap.keySet().iterator();
while(iter.hasNext()){%>
  <td class=xl26 width=80 style='width:60pt'><%=iter.next()%></td>
<%}%>
  <td class=xl35 width=106 style='width:80pt'>原归属HLR名称</td>
  <td class=xl36 width=106 style='width:80pt'>原归属HLR信令点</td>
  <td class=xl37 width=122 style='width:92pt'>申请归属HLR名称</td>
  <td class=xl38 width=129 style='width:97pt'>申请归属HLR信令点</td>
  <td class=xl27 width=204 style='width:153pt'>申请编号</td>
 </tr>
<%iter = excelMap.keySet().iterator();
while(iter.hasNext()){
  String keyStr = (String)iter.next();
  List hlrList = (ArrayList)excelMap.get(keyStr);
  for(int i = 0; i < hlrList.size(); i++){
    TawBureaudataSHlrForExcel po = (TawBureaudataSHlrForExcel)hlrList.get(i);
    String[] segArr = po.getSegArr();
%>
 <tr height=17 style='height:12.75pt'>
  <td height=17 class=xl28 style='height:12.75pt'>湖北</td>
  <td class=xl29><%=po.getCityName()%></td>
  <td class=xl29><%=po.getZoneNum()%></td>
<%for(int j=0; j<segArr.length; j++){%>
  <td class=xl30><%=(segArr[j]==null)?" ":segArr[j]%></td>
<%}%>
  <td class=xl31><%=po.getHlrName()%></td>
  <td class=xl32><%=po.getHlrSignalId()%></td>
  <td class=xl31><%=po.getPrehlrName()%></td>
  <td class=xl32><%=po.getPrehlrSignalId()%></td>
<%if(i==0){%>
  <td rowspan=<%=hlrList.size()%> class=xl33 style='border-bottom:.5pt solid black;border-top:none'><%=keyStr%></td>
<%}%>
 </tr>
<%}}%>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=38 style='width:29pt'></td>
  <td width=44 style='width:33pt'></td>
  <td width=62 style='width:47pt'></td>
  <td width=80 style='width:60pt'></td>
  <td width=83 style='width:62pt'></td>
  <td width=84 style='width:63pt'></td>
  <td width=122 style='width:92pt'></td>
  <td width=129 style='width:97pt'></td>
  <td width=106 style='width:80pt'></td>
  <td width=106 style='width:80pt'></td>
  <td width=204 style='width:153pt'></td>
 </tr>
 <![endif]>
</table>

</body>

</html>
