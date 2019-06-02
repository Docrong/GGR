package com.boco.eoms.commons.system.user.sox.support.acegi;

import org.acegisecurity.event.authentication.AbstractAuthenticationEvent;
import org.acegisecurity.event.authentication.AbstractAuthenticationFailureEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 21, 2008 4:43:27 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SOXAuthenticationLoggerListener implements ApplicationListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof AbstractAuthenticationEvent) {
			AbstractAuthenticationEvent authEvent = (AbstractAuthenticationEvent) event;

			if (event instanceof AbstractAuthenticationFailureEvent) {
				System.out.println(authEvent.getAuthentication().getName()
						+ "fail");
				System.out.println(authEvent.getAuthentication().getDetails()
						+ "fail");

			} else {
				System.out.println(authEvent.getAuthentication().getName()
						+ "success");
				System.out.println(authEvent.getAuthentication().getDetails()
						+ "success");
			}
		}

	}

}
