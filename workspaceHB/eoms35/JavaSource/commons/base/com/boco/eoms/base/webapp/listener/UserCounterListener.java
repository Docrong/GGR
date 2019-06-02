package com.boco.eoms.base.webapp.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * 在线用户监听
 * <p>
 * Title:在线用户监听
 * </p>
 * <p>
 * Description:监听用户的登录和注销行为，注意，由于使用了acegi安全框架
 * 其中过滤器链的HttpSessionContextIntegrationFilter中doFilter()的finally中的代码会将SecurityContext置空
 * 所以如果通过SecurityContextHolder.getContext()或者HttpSessionBindingEvent.getValue()
 * 都是无法取到SecurityContext的，但是由于SecurityContext置空之前存如了HttpSession，因此可以从session取到SecurityContext
 * </p>
 * <p>
 * Date:2008-5-5 9:22:18
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class UserCounterListener implements ServletContextListener,
		HttpSessionAttributeListener {
	/**
	 * 在线用户数量属性的key
	 */
	public static final String COUNT_KEY = "userCounter";

	/**
	 * 在线用户集合属性的key
	 */
	public static final String USERS_KEY = "userNames";
	/**
	 * 在线值班用户集合属性的key
	 */
	public static final String DUTYUSERS_KEY = "dutyuserNames";

	/**
	 * 登录后保存session的属性名
	 */
	public static final String EVENT_KEY = "sessionform";

	/**
	 * servlet上下文，随应用启动初始化，在线用户信息保存在其中
	 */
	private static transient ServletContext servletContext;

	/**
	 * 在线用户数量
	 */
	private static int counter;

	/**
	 * 在线用户集合
	 */
	private static HashMap users;
	/**
	 * 在线值班用户集合
	 */
	private static HashMap dutyusers;
	/**
	 * 初始化ServletContext
	 */
	public synchronized void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		servletContext.setAttribute((COUNT_KEY), Integer.toString(counter));
	}

	/**
	 * ServletContext置空
	 */
	public synchronized void contextDestroyed(ServletContextEvent event) {
		servletContext = null;
		users = null;
		dutyusers = null;
		counter = 0;
	}

	/**
	 * 增加一个在线用户数量
	 * 
	 */
	synchronized void incrementUserCounter() {
		counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
		counter++;
		servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));

		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "User Count: " + counter);
		// }
	}

	/**
	 * 减少一个在线用户数量
	 * 
	 */
	synchronized void decrementUserCounter() {
		int counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
		counter--;

		if (counter < 0) {
			counter = 0;
		}

		servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));

		// if (log.isDebugEnabled()) {
		BocoLog.debug(this, "User Count: " + counter);
		// }
	}

	/**
	 * 增加一个在线用户
	 * 
	 * @param user
	 *            用户信息
	 */
	synchronized void addOnlineUser(TawSystemSessionForm sessionform) {
		users = (HashMap) servletContext.getAttribute(USERS_KEY);
		dutyusers = (HashMap) servletContext.getAttribute(DUTYUSERS_KEY); // 值班
		if (users == null) {
			users = new HashMap();
		}
	 
		if (dutyusers == null) {
			dutyusers = new HashMap();
		}
		if (users.containsKey(sessionform.getUserid())) {
			if(!sessionform.getWorkSerial().equals("0")){  // 判断是否是值班状态
				dutyusers.put(sessionform.getUserid(), sessionform);
				servletContext.setAttribute(DUTYUSERS_KEY, dutyusers);
			}
			BocoLog.debug(this, "User already logged in!");
		} else {
			users.put(sessionform.getUserid(), sessionform);
			
			servletContext.setAttribute(USERS_KEY, users);
			incrementUserCounter();
		}

	}

	/**
	 * 移除一个在线用户
	 * 
	 * @param user
	 *            用户信息
	 */
	synchronized void removeOnlineUser(TawSystemSessionForm sessionform) {
		users = (HashMap) servletContext.getAttribute(USERS_KEY);
		dutyusers = (HashMap) servletContext.getAttribute(DUTYUSERS_KEY); // 值班
		if (users != null) {
			users.remove(sessionform.getUserid());
		}
		if (dutyusers != null) {
			dutyusers.remove(sessionform.getUserid());
		}
		servletContext.setAttribute(USERS_KEY, users);
		servletContext.setAttribute(DUTYUSERS_KEY, dutyusers);
		decrementUserCounter();
	}

	/**
	 * 向session中添加属性的时候会触发该方法，如果是sessionform的变化，则增加一个在线用户
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		BocoLog.debug(this, "event.name: " + event.getName());
		if (event.getName().equals(EVENT_KEY)) {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) event
					.getValue();
			if (sessionform != null) {
				addOnlineUser(sessionform);
			} else {
				BocoLog.debug(this,
						"sessionform is null, could not add online user!");
			}
		}
	}

	/**
	 * 判断是否匿名用户（保留方法）
	 * 
	 * @return boolean
	 */
	// private boolean isAnonymous() {
	// AuthenticationTrustResolver resolver = new
	// AuthenticationTrustResolverImpl();
	// SecurityContext ctx = SecurityContextHolder.getContext();
	// if (ctx != null) {
	// Authentication auth = ctx.getAuthentication();
	// return resolver.isAnonymous(auth);
	// }
	// return true;
	// }
	/**
	 * 从session中移除属性的时候会触发该方法，如果是sessionform的变化，则删除一个在线用户
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (event.getName().equals(EVENT_KEY)) {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) event
					.getValue();
			if (sessionform != null) {
				removeOnlineUser(sessionform);
			} else {
				BocoLog.debug(this,
						"sessionform is null, could not remove online user!");
			}

		}
	}

	/**
	 * 重置session中属性的时候会触发该方法，如果是sessionform的变化，则重置在线用户信息
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		if (event.getName().equals(EVENT_KEY)) {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) event
					.getValue();
			if (sessionform != null) {
				addOnlineUser(sessionform);
			} else {
				BocoLog.debug(this,
						"sessionform is null, could not replace online user!");
			}
		}
	}

	/**
	 * 获取在线用户数量
	 * 
	 * @return int 在线用户数量
	 */
	public static int getOnlineUserCounter() {
//		counter = Integer.parseInt((String) servletContext
//				.getAttribute(COUNT_KEY));
		users = (HashMap) servletContext.getAttribute(USERS_KEY);
		return users.size();
	}

	/**
	 * 获取在线用户信息集合
	 * 
	 * @param request
	 *            HttpServletRequest 将用户迭代器存入HttpServletRequest，以便页面使用
	 */
	public static void getOnlineUsers(HttpServletRequest request) {
		users = (HashMap) servletContext.getAttribute(USERS_KEY);
		dutyusers = (HashMap) servletContext.getAttribute(DUTYUSERS_KEY); // 值班
		if (dutyusers == null) {
			dutyusers = new HashMap();
		}
		request.setAttribute("usersMap", users);
		request.setAttribute("dutyUsersMap", dutyusers);
	}
}
