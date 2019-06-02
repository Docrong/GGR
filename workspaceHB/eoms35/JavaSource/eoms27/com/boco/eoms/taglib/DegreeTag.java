package com.boco.eoms.taglib;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.jsp.tagext.TagSupport;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.util.StaticVariable;

public class DegreeTag extends TagSupport {

	public int doStartTag() {
try{
		List degree = new ArrayList();

                //String stemp = StaticMethod.getString("普通用户");
                //String stemp2 = StaticMethod.getString("管理员");
                String stemp =  StaticVariable.userDegreeCom;
                String stemp2 = StaticVariable.userDegreeAdm;
                degree.add(
                        new org.apache.struts.util.LabelValueBean
                          ( stemp,String.valueOf(0)));
                degree.add(
                     new org.apache.struts.util.LabelValueBean
                        (stemp2,String.valueOf(1)));

		pageContext.setAttribute("degreeList",degree);
}
              catch(Exception e){
                e.printStackTrace();
              }
		return SKIP_BODY;

	}

}