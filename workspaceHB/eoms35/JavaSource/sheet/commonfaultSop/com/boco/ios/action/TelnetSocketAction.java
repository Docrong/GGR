package com.boco.ios.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.ios.bo.TelnetSocket;

public class TelnetSocketAction extends BaseAction {

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long neid = StaticMethod.nullObject2Long(request.getParameter("neid"));
		HttpSession session = request.getSession(true);
		StringBuffer sb = new StringBuffer();
		TelnetSocket st = (TelnetSocket) session.getAttribute("TelnetSocket");
		if(st!=null&&st.getStatus()==-1){
			st.init(new Long(neid));
			session.setAttribute("status", "状态:连接网元成功\r\n");
			session.setAttribute("TelnetSocket", st);
			return mapping.findForward("init");
		}
		st = new TelnetSocket();
		st.init(new Long(neid));
		switch (st.getStatus()) {
		case -3:
			sb.append("状态:未连接统一指令平台\r\n");
			break;
		case -2:
			sb.append("状态:连接统一指令平台成功\r\n");
			break;
		case -1:
			sb.append("状态:连接网元成功\r\n");
			break;
		case 0:
			sb.append("状态:命令成功结束，收到期望值\r\n");
			break;
		case 1:
			sb.append("状态:未收到期望值，时间超时\r\n");
			break;
		case 5:
			sb.append("状态:统一指令平台状态切换异常\r\n");
			break;
		default:
			sb.append("状态:其它\r\n");
		}
		session.setAttribute("status", sb);
		session.setAttribute("TelnetSocket", st);
		
		return mapping.findForward("init");
	}

	public void send(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonRoot = new JSONObject();
		HttpSession session = request.getSession(true);
		TelnetSocket st = (TelnetSocket) session.getAttribute("TelnetSocket");
		String cmd = StaticMethod.nullObject2String(request.getParameter("cmd"));
		long time =  StaticMethod.nullObject2Long(request.getParameter("time"));
		StringBuffer res= new StringBuffer();
		if(st==null){
		}
		if(!"".equals(cmd)){
			res.append(st.send(cmd+";", new Long(time), ""));
			session.setAttribute("result", res);
		}
		jsonRoot.put("data", res.toString());
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void close(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		TelnetSocket st = (TelnetSocket) session.getAttribute("TelnetSocket");
		st.close();
		session.setAttribute("TelnetSocket", null);
	}

}
