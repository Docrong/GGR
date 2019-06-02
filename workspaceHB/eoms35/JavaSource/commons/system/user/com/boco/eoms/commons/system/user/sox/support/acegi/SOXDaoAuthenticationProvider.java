package com.boco.eoms.commons.system.user.sox.support.acegi;

import java.util.Date;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;

import com.boco.eoms.base.util.DateUtil;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 23, 2008 10:46:06 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SOXDaoAuthenticationProvider extends DaoAuthenticationProvider {
	/**
	 * 用户mgr
	 */
	private ITawSystemUserManager tawSystemUserManager;

	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		TawSystemUser user = (TawSystemUser) userDetails;

		// SOX，连续登陆n（6）次将锁定用户不得登陆
		try {
			super.additionalAuthenticationChecks(userDetails, authentication);
			System.out.println(authentication.isAuthenticated());
			System.out.println(authentication.getCredentials().toString());
			System.out.println(authentication.getPrincipal().toString());
			System.out.println(authentication.getDetails().toString());
			if(authentication.isAuthenticated())
			System.out.println(authentication.getAuthorities().toString());
			
		} catch (AuthenticationException e) {
			// 若登陆失败，则将失败次数累加，到达一定次数则锁住用户
			user.setFailCount(user.getFailCount() != null ? new Integer(user
					.getFailCount().intValue() + 1) : new Integer(0));
			if (user.getFailCount().intValue() >= UserMgrLocator
					.getUserAttributes().getPasswdRepeatNum().intValue() * 2) {
				// 锁定用户
				user.setAccountLocked(true);
			}
			tawSystemUserManager.saveTawSystemUser(user);
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"), userDetails);
		}

		// SOX，判断用户有效期为n(90)天
		if (new Date().compareTo(DateUtil.addDate(user.getSavetime(),
				UserMgrLocator.getUserAttributes().getPasswdAvailableDay()
						.intValue())) > 0) {
			// 设置user用户不可用
			user.setEnabled(false);
			tawSystemUserManager.saveTawSystemUser(user);
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"), userDetails);
		}
		//判断用户是否有用deleted=0 是, 1 否
       if("1".equals(user.getDeleted())){
    	 // 若登陆失败，则将失败次数累加，到达一定次数则锁住用户
			user.setFailCount(user.getFailCount() != null ? new Integer(user
					.getFailCount().intValue() + 1) : new Integer(0));
			if (user.getFailCount().intValue() >= UserMgrLocator
					.getUserAttributes().getPasswdRepeatNum().intValue() * 2) {
				// 锁定用户
				user.setAccountLocked(true);
			}
    	  tawSystemUserManager.saveTawSystemUser(user);
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"), userDetails);
       }
		// 登陆成功则置零
       if (user.getFailCount()!= null && user.getFailCount().intValue()!=0){
    	   user.setFailCount(new Integer(0));
    	   tawSystemUserManager.saveTawSystemUser(user);
       }

	}

	/**
	 * @param tawSystemUserManager
	 *            the tawSystemUserManager to set
	 */
	public void setTawSystemUserManager(
			ITawSystemUserManager tawSystemUserManager) {
		this.tawSystemUserManager = tawSystemUserManager;
	}
}
