<%@ page contentType="text/html;charset=GB2312"%>

<form>

<SELECT id="regionId" name="regionId" onChange="refresh(this)">
<OPTION value="-1">����</OPTION>
<option value="0">����</option>
<option value="1">�ص�</option>
</SELECT>

<SELECT id="departmentId" name="departmentId" onChange="getDepartmentRoom(document.all.departmentId)">
<OPTION value="-1">����</OPTION>
</SELECT>

<SELECT id="roomId" name="roomId">
<OPTION value="-1" >����</OPTION>
</SELECT>

</form>


<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript" SRC="../xmltree/xtree.js" />

<script>getDepartmentRoom(document.all.departmentId);</script>

<SCRIPT language="JavaScript">

function refresh(region){
   this.location.replace("setQueryDuty.jsp?selectedRegion="+region.value+"&r="+Math.random());
}

function getDepartmentRoom(rg){
        //listRotaActionForm.departmentId.ready=false;
        var Url="getDepartmentRooms.jsp?selectedId="+rg.value+"&r="+Math.random();
        ReceiveUrl(Url);
}

function reXml(temp){
    alert(temp.xml);
    if (temp.xml!=""){
         if (temp.firstChild.getAttribute("err")=="0" ){
               xmlDom=temp;
               //alert(temp.firstChild.xml);
               var output=temp.firstChild.xml;
//             document.all.roomId.outerHTML=output;
         }else if(temp.firstChild.getAttribute("err")=="1"){
         }
    } else {
         alert("�쳣:"+temp.parseError.reason);
    }
//             document.all.departmentId.ready=true;
}

</SCRIPT>





