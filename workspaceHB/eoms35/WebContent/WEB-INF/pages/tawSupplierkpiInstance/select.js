function send_request(url) {
alert("3333333") ;
  	http_request = false;
  	if(window.XMLHttpRequest) {
          http_request = new XMLHttpRequest();
          if (http_request.overrideMimeType) {
              http_request.overrideMimeType('text/xml');
          }
  	}
  	else if (window.ActiveXObject) {
          try {
              http_request = new ActiveXObject("Msxml2.XMLHTTP");
          } catch (e) {
              try { http_request = new ActiveXObject("Microsoft.XMLHTTP"); } catch(e) {}
          }
  	}

  	if (!http_request) {
          window.alert("????????????XMLHttpRequest????????????.");
          return false;
  	}
  	http_request.onreadystatechange = selDlFaultTypeRequest1;
  	http_request.open("post", url, true);
  	http_request.send(null);
}


function saveInfo(obj){
	var url = document.all("editInfo").action;
    send_request(url);

}
function selDlFaultTypeRequest1() {
      if (http_request.readyState == 4) { // ??????????????????
          if (http_request.status == 200) { // ?????????????????????????????????????????????
              var returnValue =  http_request.responseText;
              alert(returnValue) ;
			  if(returnValue=="true"){
			  	rowRemove();
			  }
			  alert("?????????????????????????????????");
          } else { //???????????????
              alert("????????????????????????????????????????????????????????????");
          }
      }
}
function rowRemove(){
	var objOld = document.getElementById("old");
	alert(objOld.cells(0).innerText);
	alert(objOld.cells(1).innerText);
	alert(objOld.cells(2).innerText);
	alert(objOld.cells(3).innerText);

        var newTable = document.getElementById("xin");
	    var objTableBody = newTable.children(0);
	    var rowCount = newTable.rows.length;
	    
	    var newRow = document.createElement("TR")
	    newRow.align = "center";
	    if(rowCount%2==0){
	        newRow.style.background = "#DFEEFF";
	    }
	    else{
	        newRow.style.background = "#BFDCFF";
	    }
	    
	    var objCell1 = document.createElement("TD");
		objCell1.innerText=objOld.cells(0).innerText;
	    newRow.appendChild(objCell1);
	    
	    var objCell2 = document.createElement("TD");
		objCell2.innerText=objOld.cells(1).innerText;
	    newRow.appendChild(objCell2);
	    
	    var objCell3 = document.createElement("TD");
		objCell3.innerText=objOld.cells(2).innerText;
		newRow.appendChild(objCell3);
	    
	    var objCell4 = document.createElement("TD");
		objCell4.innerText=objOld.cells(3).innerText;
	    newRow.appendChild(objCell4);
	    
	    objTableBody.appendChild(newRow);
}
