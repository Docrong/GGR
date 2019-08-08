package com.boco.eoms.sheet.autodistribute.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;


public class AutoDistributeListDisplaytagDecoratorHelper extends TableDecorator {

    public String getFlowName() {
        AutoDistribute tmpForm = (AutoDistribute) getCurrentRowObject();
        String url = "<a href=autodistribute.do?method=showInputPage&id=" + tmpForm.getId() + ">" + tmpForm.getFlowName() + "</a>";
        return url;
    }

    public String getAutoType() {
        AutoDistribute temForm = (AutoDistribute) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        String autoType = temForm.getAutoType();
        String tmpStr = service.id2Name(autoType, "ItawSystemDictTypeDao");
        return tmpStr;
    }

    public String getRoleId() {
        AutoDistribute temForm = (AutoDistribute) getCurrentRowObject();
        ITawSystemRoleManager roleManager = (ITawSystemRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemRoleManager");
        String roleId = temForm.getRoleId();
        String tmpStr = roleManager.getRoleNameById(Long.parseLong(roleId));
        return tmpStr;
    }

    public String getThreshold() {
        AutoDistribute temForm = (AutoDistribute) getCurrentRowObject();
        String autoType = temForm.getAutoType();
        String tmpStr = "";
        if (autoType.equals("101010603")) {
            tmpStr = temForm.getThreshold();
        } else {
            tmpStr = "无";
        }
        return tmpStr;
    }
}
