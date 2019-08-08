package com.boco.eoms.commons.log.aop.support;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:aop在执行方法前调用记录日志
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 11, 2008 1:06:25 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public class LogBeforeMethodAdvice implements MethodBeforeAdvice {
    /**
     * log4j
     */
    private static final Logger logger = Logger
            .getLogger(LogBeforeMethodAdvice.class);

    /**
     * 不进行记录的方法
     */
    private List exclude;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method,
     *      java.lang.Object[], java.lang.Object)
     */
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        if (exclude.contains(StaticMethod.method2class(method))) {
            return;
        }
        // Authentication currentUser =
        // SecurityContextHolder.getContext().getAuthentication();
        // User user = (User) currentUser.getPrincipal();
        logger.info(StaticMethod.method2class(method));
        // String userId = EOMSContext.get().getUser().getUserid() == null ? ""
        // : EOMSContext.get().getUser().getUserid();
        // String remoteAddr = EOMSContext.get().getUser().getRomteaddr() ==
        // null ? ""
        // : EOMSContext.get().getUser().getRomteaddr();
        // TawCommonLog.saveLog(target, userId, remoteAddr, "0",
        // target.toString()
        // + method.toString());

    }

    /**
     * @param exclude the exclude to set
     */
    public void setExclude(List exclude) {
        this.exclude = exclude;
    }

}
