

  function go(wgid)
{ 

    var xmlDoc;
    var vhref = gethref()+"sop/soprule.xml";
    alert(vhref);
    if(window.ActiveXObject)
    {
    	var flag=-1;
    	var ft ;
        xmlDoc    = new ActiveXObject('Microsoft.XMLDOM');
        xmlDoc.async    = false;
        xmlDoc.load(vhref);
        alert(xmlDoc);
        var  wgids = xmlDoc.getElementsByTagName("sopwgids");
        alert(wgids.length);
        var  sopft = xmlDoc.getElementsByTagName("sopft");

        for (i=0;i<wgids.length;i++){
        	if (wgids[i].childNodes[0].nodeValue.indexOf(wgid)!=-1){
        	flag = i;
        	break;
        	}
        	
        }
        alert(flag);
if (flag==-1) {
alert("暂无SOP帮助");
return;
}
 switch (flag){
 case 0:
 ft = "er";break;
  case 1:
 ft = "huawei";break;
   case 2:
 ft = "nokia";break;
 }


        var sopwgid = sopft[flag].getElementsByTagName("sopwgid");
        var sopadress = sopft[flag].getElementsByTagName("sopadress");
         for (i=0;i<sopwgid.length;i++){
          
        	if (sopwgid[i].childNodes[0].nodeValue.indexOf(wgid)!=-1){
        	flag = i;
        	break;
        	}
   
        }
        var href = sopadress[flag].childNodes[0].nodeValue;
        
        href = gethref()+"sop/"+ft+"/jiaohuan/"+href;
        alert(href);
        window.showModalDialog(href,window,"dialogHeight:500px;dialogWidth:1020px;center:YES;help:NO;resizable:NO;status:YES;");
    }
    else
    {
        alert ("您的浏览器暂不支持，请使用IE5以上浏览器");
    }
  }  
  
  
  function win_load(wgid)
  { 
	  
var href = gethref()+"sop/soprule.xml";
alert(href);
if (wgid.length!=17){
	btn.style.visibility="hidden";
}
      var xmlDoc;

      if(window.ActiveXObject)
      {
      	var flag=-1;
      	var ft ;
          xmlDoc    = new ActiveXObject('Microsoft.XMLDOM');
          xmlDoc.async    = false;
          xmlDoc.load(href);
          alert(window.location.href);
          var  wgids = xmlDoc.getElementsByTagName("sopwgids");
          var  sopft = xmlDoc.getElementsByTagName("sopft");              

          for (i=0;i<wgids.length;i++){
          	if (wgids[i].childNodes[0].nodeValue.indexOf(wgid)!=-1){         
          	flag = i;
          	break;
          	}
          	
          }
         
  if (flag==-1) {
	  btn.style.visibility="hidden";
  }
  
  }
    } 
  
  function  gethref(){
	  var scripts = document.getElementsByTagName("script");  
	  var result = "";
      var  reg = /soprule([.-]\d)*\.js(\W|$)/i  

      for(var i = 0 , n = scripts.length ; i <n ; i++){  

          var src = !!document.querySelector ? scripts[i].src   

                        :scripts[i].getAttribute("src",4);  

          if(src && reg.test(src)){  

              result = src  
              break;  

          }  
      }
	  return result.substr( 0, result.lastIndexOf('/') + 1 );
  }
