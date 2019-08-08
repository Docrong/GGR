package com.boco.eoms.workplan.plugin;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.flow.TawwpFlowManage;

/**
 * <p>
 * Title:作业计划plugin
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 16, 2008 5:16:28 PM
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class InitWorkplanPlugin implements PlugIn {

    public void destroy() {

    }

    /**
     * 加载作业计划
     */
    public void init(ActionServlet arg0, ModuleConfig arg1)
            throws ServletException {
        // 作业计划解藕，在struts-workplan.xml中做一个plugin加载
        // 作业计划模版初始化
        BocoLog.info(this, 8, "开始作业计划环境初始化");
        TawwpUtilDAO.initInfor();
        TawwpFlowManage.getInstance(StaticMethod.CLASSPATH_FLAG
                + "com/boco/eoms/workplan/config/workflow.xml");
        BocoLog.info(this, 9, "完成作业计划环境初始化");
    }

}
