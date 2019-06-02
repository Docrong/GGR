
/*确认删除操作**************************************************************************/
function ConfirmDel(){
           if(confirm("请确认是否删除,该操作将删除对应项下的所有相关数据,请谨慎操作!")){
             return true;
           }else{
             return false;
           }
         }

/*确认修改操作**************************************************************************/
function ConfirmUpdate(){
           if(confirm("请确认是否修改,该操作将使修改信息生效并有可能影响相关操作,请谨慎操作!")){
             return true;
           }else{
             return false;
           }
         }

/*确认查询时间格式**********************************************************************/
function isDate(Year1,Month1,Day1,Year2,Month2,Day2) {
         var Year1,Year2,Month1,Month2,Day1,Day2;
           Year1 =  parseFloat(Year1);
           Year2 =  parseFloat(Year2);
           Month1 = parseFloat(Month1);
           Month2 = parseFloat(Month2);
           Day1 =   parseFloat(Day1);
           Day2 =   parseFloat(Day2);

        switch(Month1) {
                case 4: case 6: case 9: case 11:
                        if ( Day1 > 30 ) {
                                alert("起始年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                return false;
                                }
                        else break;
                case 2:
                        if ( ( ( Year1 % 4 == 0 && Year1 % 100 != 0 ) || Year1 % 400 == 0 ) && Day1 > 29 )
                         {
                                alert("起始年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        else if ( (Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28 )

                         {
                            alert("起始年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        default:
        }
        switch(Month2) {
                case 4: case 6: case 9: case 11:
                        if ( Day2 > 30 ) {
                                alert("结束年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                return false;
                                }
                        else break;
                case 2:
                        if ( ( ( Year2 % 4 == 0 && Year2 % 100 != 0 ) || Year2 % 400 == 0 ) && Day2 > 29 )
                         {
                                 alert("结束年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }

                        else if ( (Year2 % 4 != 0 || (Year2 % 100 == 0 && Year2 % 400 != 0)) && Day2 > 28 )
                         {
                                 alert("结束年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        else break;
                        default:
        }
        if(Year1>Year2){
          alert("查询开始年份:"+Year1+"-"+Month1+"-"+Day1+"大于结束年份:"+Year2+"-"+Month2+"-"+Day2);
          return false;
        }else if(Month1>Month2){
          alert("查询开始月份:"+Year1+"-"+Month1+"-"+Day1+"大于结束月份:"+Year2+"-"+Month2+"-"+Day2);
          return false;
        }else if(Day1>Day2){
          alert("查询开始天数:"+Year1+"-"+Month1+"-"+Day1+"大于结束天数:"+Year2+"-"+Month2+"-"+Day2);
          return false;
        }
        return true;
  }

/*确认下啦筐录入时间格式**********************************************************************/
function isDateInput(Year1,Month1,Day1) {
        var Year1,Month1,Day1;
           Year1 =  parseFloat(Year1);
           Month1 = parseFloat(Month1);
           Day1 =   parseFloat(Day1);

        switch(Month1) {
                case 4: case 6: case 9: case 11:
                        if ( Day1 > 30 ) {
                                alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                return false;

                                }
                        else break;
                case 2:
                        if ( ( ( Year1 % 4 == 0 && Year1 % 100 != 0 ) || Year1 % 400 == 0 ) && Day1 > 29 )
                         {
                                alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        else if ( (Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28 )

                         {
                            alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        default:
        }
        return true;
  }

  /*确认文本筐录入时间格式**********************************************************************/
function textInputIsDate(datestr) {
        var Year1,Month1,Day1;
        //利用正则表达式验证时间格式
        re=/^\d{4}-{1}\d{2}-{1}\d{2}$/;
	if (datestr.match(re)==null)
	{
	  alert("时间输入格式不对，正确格式为:YYYY-MM-DD");
	  return false;
	}
	else{

           Year1 =  parseFloat(datestr.substring(0,3));
           Month1 = parseFloat(datestr.substring(5,6));
           Day1 =   parseFloat(datestr.substring(8,9));

           switch(Month1) {
                case 4: case 6: case 9: case 11:
                        if ( Day1 > 30 ) {
                                alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                return false;

                                }
                        else break;
                case 2:
                        if ( ( ( Year1 % 4 == 0 && Year1 % 100 != 0 ) || Year1 % 400 == 0 ) && Day1 > 29 )
                         {
                                alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        else if ( (Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28 )

                         {
                            alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        default:
        	}
        }
        return true;
  }

/*确认录入时间格式**********************************************************************/
function isDateInput2(Year1,Month1,Day1) {
        var Year1,Month1,Day1;
           Year1 =  parseFloat(Year1.value);
           Month1 = parseFloat(Month1.value);
           Day1 =   parseFloat(Day1.value);

        switch(Month1) {
                case 4: case 6: case 9: case 11:
                        if ( Day1 > 30 ) {
                                alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                return false;

                                }
                        else break;
                case 2:
                        if ( ( ( Year1 % 4 == 0 && Year1 % 100 != 0 ) || Year1 % 400 == 0 ) && Day1 > 29 )
                         {
                                alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        else if ( (Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28 )

                         {
                            alert("年月日"+Year1+"-"+Month1+"-"+Day1+"有问题");
                                 return false;
                         }
                        default:
        }
        return true;
  }



  /*判断是否为数值项****************************************************************/
  function trim(str){
        if (str==null)
        {
                return "";
        }
        for (i=0;i<str.length;i++){
                if (str.charAt(0) == ' ') {
                        str = str.substr(1);
                }
                else {
                        break;
                }
        }
        return str;
  }

  /*替换字符串**********************************************************************/
  function replacechar(str,searchchar,replacechar){
        for (i=0;i<str.length;i++){
                if (str.charAt(0) == ' ') {
                        str = str.substr(1);
                }
                else {
                        break;
                }
        }
        while (str.length > 0){
                if (str.charAt(str.length-1) == ' ') {
                        str = str.substr(0,str.length-1);
                } else {
                        break;
                }
        }
        return str;
  }

  /*判断是否为数值项****************************************************************/
  function isNumber(inputVal) {

        inputStr=inputVal;
        if(inputStr.length==1){
          if((inputStr.charAt(0)<'0')||(inputStr.charAt(0)>'9')){
                  alert("输入项:"+inputStr+"必须为数值。");
                   return false;
            }
        }else if(inputStr.length>1){
            if((inputStr.charAt(0)<'0')||(inputStr.charAt(0)>'9')){
              alert("输入项:"+inputStr+"必须为数值。");
                   return false;
            }
            for(n=1;n<inputStr.length;n++){
              if((inputStr.charAt(n)!='.')){
              if((inputStr.charAt(n)> '9')||(inputStr.charAt(n)< '0')){
                   alert("输入项:"+inputStr+"必须为数值。");
                   return false;
                }
              }
            }

        }
        return true;
  }

 /*判断数值项是否超出最大限制*********************************************************/
  function isOutRange(inputVal) {
    var inputInt=parseInt(inputVal);

    var index=document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;
    if(index=="String"){
      var inputInt1=parseInt("255");
    }
    else if (index=="LongString"){
      var inputInt1=parseInt("4000");
    }
    if(inputInt>inputInt1){
      alert("输入项:"+inputVal+"超过最大值:"+inputInt1);
      return false;
    }
    return true;
  }

  /*判断是否为数值项****************************************************************/
  function isNumber2(inputVal) {

        inputStr=inputVal.value;
        if(inputStr.length==1){
          if((inputStr.charAt(0)<'0')||(inputStr.charAt(0)>'9')){
                  alert("输入项:"+inputVal.name+"的值"+inputStr+"有问题.");
                   return false;
            }
        }else if(inputStr.length>1){
            if((inputStr.charAt(0)<'1')||(inputStr.charAt(0)>'9')){
              alert("输入项:"+inputVal.name+"的值"+inputStr+"有问题.");
                   return false;
            }
            for(n=1;n<inputStr.length;n++){
              if((inputStr.charAt(n)> '9')||(inputStr.charAt(n)< '0')){
                   alert("输入项:"+inputVal.name+"的值"+inputStr+"有问题.");
                   return false;
                }
            }

        }
        return true;
  }

  /*判断颜色格式********************************************************************/
  function isColor(varColor){
          /*var varcolor=varColor;
          if(varcolor!=""){
            if(varcolor.length!=7){
              alert("显示颜色格式错误,正确格式从:#000000到#FFFFFF.");
              return false;
            }else if(varcolor.length==7){
              if(varcolor.charAt(0)!="#"){
                alert("显示颜色格式错误,正确格式从:#000000到#FFFFFF.");
                return false;
              }else{
                for(i=1;i<7;i++){
                  if((varcolor.charAt(i)<="0"||varcolor.charAt(i)>="9")&&(varcolor.charAt(i)<="a"||varcolor.charAt(i)>="f")&&(varcolor.charAt(i)<="A"||varcolor.charAt(i)>="F")){
                    alert("显示颜色格式错误,正确格式从:#000000到#FFFFFF.");
                    return false;
                  }
                }
              }
            }
          }*/
          return true;
  }

  /***返回两个数比较结果,如果左边的数大于右边的数,返回假,反之则反**********************/
  function compareTwoNum(varValue1,varValue2){
    var var1=parseFloat(varValue1);
    var var2=parseFloat(varValue2);
    if(var1>var2){
      alert("左边的数不能大于右边的数");
      return false;
    }
    else
      return true;
  }

  /***权限告警*********************************************************************/
  function powerAlert(varValue){
    var varvalue=parseFloat(varValue);
    if(varvalue==1){
      alert("对不起,你没有权限进行此项操作!");
      return false;
    }
    else
      return true;
  }

  /***判断是否为空******************************************************************/
  function isNull(varStr){
    var varstr=varStr;
    if(varstr==""||varstr==null){
       alert("带星号的项"+varStr+"不能为空.");
       return false;
    }
    return true;
  }

  /***判断是否为空******************************************************************/
  function isNull2(varStr){
    var varstr=varStr.value;
    if(varstr==""||varstr==null){
       alert("带星号的项"+varStr.name+"不能为空.");
       return false;
    }
    return true;
  }

 /***判断选择框是否选中******************************************************************/
  function isOption(selectAttr){
    var j=0;
      var SelectedIndex = selectAttr.options.selectedIndex;
      if (SelectedIndex == -1) {
        alert("请选择一个部门");
        return false;
      }else{
          return true;
      }
  }

  /***判断******************************************************************/
   function isPowerUser(poweruser){

       if (poweruser!="") {
         alert("警告:"+poweruser);
         return false;
       }else{
           return true;
       }


  }

  function vStoreTypeChange(){

 var index=document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;
 var index1=document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
     if(index=="Date" || index=="DateTime"){
	  //document.addEForm.showtype.selectedIndex=3;
      document.addEForm.showtype.disabled=true;
      document.addEForm.formWidth.disabled=true;
      document.addEForm.formHeight.disabled=true;
      document.addEForm.typeID.disabled=true;
     }else if(index=="Integer" || index=="Float"){
        document.addEForm.showtype.selectedIndex=0;
        document.addEForm.showtype.disabled=true;
        document.addEForm.typeID.selectedIndex=0;
        document.addEForm.typeID.disabled=true;
        if(index1==0 || index1==5){
          document.addEForm.formWidth.disabled=false;
          document.addEForm.formHeight.disabled=false;
          }
        str.innerHTML="输入串最大长";
     }else if(index=="String" || index=="LongString"){
        document.addEForm.showtype.disabled=false;
        document.addEForm.typeID.selectedIndex=0;
        document.addEForm.typeID.disabled=true;
         if(index1==0 || index1==5){
          document.addEForm.formWidth.disabled=false;
          document.addEForm.formHeight.disabled=false;
          }
     }
     else{
	  //document.addEForm.showtype.selectedIndex=0;
      document.addEForm.showtype.disabled=false;
      document.addEForm.formWidth.disabled=false;
      document.addEForm.formHeight.disabled=false;
      document.addEForm.typeID.disabled=false;

	 }
  }
function showtypeChange(){
  var index=document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
    if(index==0 || index==5){
      document.addEForm.typeID.disabled=true;
      document.addEForm.formWidth.disabled=false;
      document.addEForm.formHeight.disabled=false;
      document.addEForm.vStoreType.disabled=false;
      if(index==5)
        {str.innerHTML="文本编辑框高<font color=\"red\">(*)</font>";
         document.addEForm.height.disabled=true;
         if(document.addEForm.isedit.value=="0")
         document.addEForm.formHeight.value=3;}
      else
        {str.innerHTML="输入串最大长<font color=\"red\">(*)</font>";
         if(document.addEForm.isedit.value=="0")
         document.addEForm.formHeight.value=20;}
    }else{
      document.addEForm.typeID.disabled=false;
      document.addEForm.formWidth.disabled=true;
      document.addEForm.formHeight.disabled=true;
      document.addEForm.vStoreType.selectedIndex=0;
      document.addEForm.vStoreType.disabled=true;
      str.innerHTML="输入串最大长<font color=\"red\">(*)</font>";

   }
  }
  function openColorWin(){
//	if (window.secondwindow&&window.secondwindow.closed==false){
//		secondwindow.document.bgColor='red';secondwindow.focus();
//	}else
		secondwindow=window.showModalDialog("color.jsp",window,"dialogHeight:235px;dialogWidth:230px;center:YES;help:NO;resizable:NO;status:NO;");
		//alert(secondwindow);
		document.addEForm.color.value=secondwindow;
		return true;
	}



	function checkValueInput(){
     var vName=document.addEForm.vName.value;
     var index=document.addEForm.index.value;
     var color   =document.addEForm.color.value;
     var colspan=document.addEForm.colspan.value;
     var rowspan=document.addEForm.rowspan.value;
     var width=document.addEForm.width.value;
     var height=document.addEForm.height.value;
     var formWidth=document.addEForm.formWidth.value;
     var formHeight=document.addEForm.formHeight.value;
     var formWidth2=document.addEForm.formWidth;

    if(isOutRange(formHeight)&&isNull(vName)&&isNull(index)&&isNull(colspan)&&isNull(rowspan)&&
		isNull(formWidth)&&isNull(formHeight)&&isNumber(colspan)&&isNumber(rowspan)&&
		isNumber(width)&&isNumber(index)&&isNumber(height)&&isNumber(formWidth)&&isNumber(formHeight)&&isVariable(document.addEForm.vName)){
      return true;
    }else{
      return false;
    }
  }

function isVariable(variable){
	var literal='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var number='0123456789_';
	if(variable.value==null)
		alert('此项不能为空');
	var value=trim(variable.value);
	for(i=0;i<value.length;i++){
		if(i==0){
			if(literal.indexOf(value.charAt(i))<0){
				alert('首字母必须是英文字母');
				return variable.focus();

			}
		}else{
			if(literal.indexOf(value.charAt(i))<0&&number.indexOf(value.charAt(i))<0){
				alert('请输入英文或数字或下划线"_"组合');
				return variable.focus();

			}
		}

	}
	return true;
}

function initEditValue(){
 var index=document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
 var index1=document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;

      if(index==5)
        {str.innerHTML="文本编辑框高";
         document.addEForm.height.disabled=true;
         }
      else
        {str.innerHTML="输入串最大长";
         }
      if(index==0||index==5)
      document.addEForm.typeID.disabled=true;

      if(index=="Integer" || index=="Float"){
        str.innerHTML="输入串最大长";
     }
}
