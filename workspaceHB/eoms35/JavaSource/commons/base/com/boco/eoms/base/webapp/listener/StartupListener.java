package com.boco.eoms.base.webapp.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.rememberme.RememberMeAuthenticationProvider;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.LookupManager;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>StartupListener class used to initialize and database settings
 * and populate any application-wide drop-downs.
 *
 * <p>Keep in mind that this listener is executed outside of OpenSessionInViewFilter,
 * so if you're using Hibernate you'll have to explicitly initialize all loaded data at the
 * Dao or service level to avoid LazyInitializationException. Hibernate.initialize() works
 * well for doing this.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @web.listener
 */
public class StartupListener extends ContextLoaderListener
        implements ServletContextListener {

//    private static final Log log = LogFactory.getLog(StartupListener.class);

    public void contextInitialized(ServletContextEvent event) {
//        if (log.isDebugEnabled()) {
        BocoLog.debug(this, "initializing context...");
//        }

        // call Spring's context ContextLoaderListener to initialize
        // all the context files specified in web.xml
        super.contextInitialized(event);

        ServletContext context = event.getServletContext();

        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
        Map config = (HashMap) context.getAttribute(Constants.CONFIG);

        if (config == null) {
            config = new HashMap();
        }

        if (context.getInitParameter("theme") != null) {
            config.put("theme", context.getInitParameter("theme"));
        }

        ApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        boolean encryptPassword = false;
        try {
            ProviderManager provider = (ProviderManager) ctx.getBean("authenticationManager");
            for (Iterator it = provider.getProviders().iterator(); it.hasNext(); ) {
                AuthenticationProvider p = (AuthenticationProvider) it.next();
                if (p instanceof RememberMeAuthenticationProvider) {
                    config.put("rememberMeEnabled", Boolean.TRUE);
                }
            }

            if (ctx.containsBean("passwordEncoder")) {
                encryptPassword = true;
                config.put(Constants.ENCRYPT_PASSWORD, Boolean.TRUE);
                String algorithm = "SHA";
                if (ctx.getBean("passwordEncoder") instanceof Md5PasswordEncoder) {
                    algorithm = "MD5";
                }
                config.put(Constants.ENC_ALGORITHM, algorithm);
            }
        } catch (NoSuchBeanDefinitionException n) {
            // ignore, should only happen when testing
        }

        context.setAttribute(Constants.CONFIG, config);

        // output the retrieved values for the Init and Context Parameters
//        if (log.isDebugEnabled()) {
        BocoLog.debug(this, "Remember Me Enabled? " + config.get("rememberMeEnabled"));
        BocoLog.debug(this, "Encrypt Passwords? " + encryptPassword);
        if (encryptPassword) {
            BocoLog.debug(this, "Encryption Algorithm: " + config.get(Constants.ENC_ALGORITHM));
        }
        BocoLog.debug(this, "Populating drop-downs...");
//        }

        setupContext(context);
    }

    public static void setupContext(ServletContext context) {
        ApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        LookupManager mgr = (LookupManager) ctx.getBean("lookupManager");

        // get list of possible roles
        context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());

//        if (log.isDebugEnabled()) {
        BocoLog.debug(StartupListener.class, "Drop-down initialization complete [OK]");
//        }
    }
}
