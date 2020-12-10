package com.identity.bluepoint.web.security;

//import com.identity.bluepoint.gui.api.util.WebComponentUtil;
//import com.identity.bluepoint.gui.api.util.WebModelServiceUtils;
import com.identity.bluepoint.security.api.Authorization;
import com.identity.bluepoint.security.api.BluePointPrincipal;
import com.identity.bluepoint.util.DebugDumpable;
import com.identity.bluepoint.util.DebugUtil;
import com.identity.bluepoint.util.logging.Trace;
import com.identity.bluepoint.util.logging.TraceManager;
import com.identity.bluepoint.web.security.util.SecurityUtils;
//import com.identity.bluepoint.web.session.SessionStorage;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import java.util.Locale;

public class BluePointAuthWebSession extends AuthenticatedWebSession {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Trace LOGGER = TraceManager.getTrace(BluePointAuthWebSession.class);

	public BluePointAuthWebSession(Request request) {
	    super(request);
	    Injector.get().inject(this);
	
	    Locale locale = getLocale();
	    LOGGER.debug("Found locale {}", locale);
	    if (locale == null || !BluePointApplication.containsLocale(locale)) {
	        //default locale for web application
	        //setLocale(BluePointApplication.getDefaultLocale());
	    }
	    LOGGER.debug("Using {} as locale", getLocale());
	}

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        //todo - used for wicket auth roles...
        BluePointPrincipal principal = SecurityUtils.getPrincipalUser();
        if (principal == null) {
            return roles;
        }
        for (Authorization authz : principal.getAuthorities()) {
            roles.addAll(authz.getAction());
        }

        return roles;
    }

	@Override
	protected boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
