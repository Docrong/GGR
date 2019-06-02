function getResponseText(url) {
    var xmlhttp;
    if(eoms.isIE){
    	try{
    		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP"); 
    	}catch(e){
    		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");    		
    	}
    }else{
    	xmlhttp = new XMLHttpRequest();
    }
    
    var method = "post";
    url=url+"&"+new Date();
    xmlhttp.open(method, url, false);
    xmlhttp.setRequestHeader("content-type", "text/html; charset=GBK");
    xmlhttp.send(null);
    return xmlhttp.responseText;
}

function changeList(url,fieldName,deptId){

	var result=getResponseText(url);
	var arrOptions=result.split(",");
	var obj=$(fieldName);
	var i=0;
	var j=0;
	for(i=0;i<arrOptions.length;i++){
	   
		var opt=new Option(arrOptions[i+1],arrOptions[i]);
		obj.options[j]=opt;
		if(arrOptions[i]==deptId){
		obj.options[j].selected="true";
		}
		j++;
		i++;
	}	
	obj.options.length=arrOptions.length/2;
}
