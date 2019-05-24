package com.boco.eoms.commons.cache.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.cache.application.ApplicationCacheMgr;
import com.boco.eoms.commons.cache.sample.DemoBean;

/**
 * <p>
 * Title:缓存管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 2, 2007 9:50:07 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ApplicationCacheMgrAction extends BaseAction {

	// public Object getBean(String name) {
	// ApplicationContext ctx = WebApplicationContextUtils
	// .getRequiredWebApplicationContext(servlet.getServletContext());
	// return ctx.getBean(name);
	// }

	/**
	 * 列出所有组件cache信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addCache(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// TODO 测试需删除
//		DemoBean bean = (DemoBean) this.getBean("DemoBean");
//		for (int i = 0; i < 5; i++) {
//			log.debug("date=" + bean.getDate());
//			log.debug("random=" + bean.getRandom());
//			// 延迟一秒
//		}
		return new ActionForward(mapping.findForward("forwardListCache")
				.getPath(), false);
	}

	/**
	 * 列出所有组件cache信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listCache(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");

		List list = cacheMgr.getApplicationCache();

		// 所有缓存内存大小
		Long cachesInMemorySize = new Long(cacheMgr.calculateInMemorySize());
		request.setAttribute("cachesInMemorySize", cachesInMemorySize);
		request.setAttribute("list", list);
		return mapping.findForward("listCache");
	}

	/**
	 * 列出所组件cacheName信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailCache(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cacheName = request.getParameter("cacheName");
		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");
		Map cacheMap = cacheMgr.getCacheObject(cacheName);
		request.setAttribute("cacheName", cacheName);
		// 所有缓存内存大小
		request.setAttribute("cacheMap", cacheMap);
		return mapping.findForward("detailCache");
	}

	/**
	 * 刷新组件缓存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward flushCache(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cacheName = request.getParameter("cacheName");
		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");
		// 刷新cacheName组件缓存
		cacheMgr.flush(cacheName);

		ActionForward forward = mapping.findForward("forwardListCache");
		// String path = forward.getPath() + "?method=displayMenu&act="
		// + getActionByType(mf.getType()) + "&menuId=" + menu.getId();// 加参数
		String path = forward.getPath();
		return new ActionForward(path, false);

	}

	/**
	 * 刷新所有缓存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward flushAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");
		// 刷新所有组件缓存
		cacheMgr.flush();
		ActionForward forward = mapping.findForward("forwardListCache");
		return new ActionForward(forward.getPath(), false);
	}

	/**
	 * 刷新某组件中缓存对象
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward flushObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cacheName = request.getParameter("cacheName");
		String cacheKey = request.getParameter("cacheKey");
		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");
		cacheMgr.remove(cacheName, cacheKey);

		ActionForward forward = mapping.findForward("forwardDetailCache");
		String path = forward.getPath() + "&cacheName=" + cacheName
				+ "&cacheKey=" + cacheKey;// 加参数
		return new ActionForward(path, false);
	}

	/**
	 * 所有组件缓存统计信息清零
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cleanStatAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");
		// 所有统计信息清零
		cacheMgr.clearStatistics();
		ActionForward forward = mapping.findForward("forwardListCache");
		return new ActionForward(forward.getPath(), false);
	}

	/**
	 * 某组件缓存统计信息清零
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cleanStat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cacheName = request.getParameter("cacheName");

		ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) this
				.getBean("ApplicationCacheMgr");
		// 某组件缓存统计信息清零
		cacheMgr.clearStatistics(cacheName);
		ActionForward forward = mapping.findForward("forwardListCache");
		return new ActionForward(forward.getPath(), false);
	}

}
