package com.identity.bluepoint.web.page.error;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.http.WebResponse;
import org.springframework.http.HttpStatus;

import com.identity.bluepoint.gui.api.page.PageBase;
//import com.identity.bluepoint.model.api.authentication.ModuleAuthentication;
import com.identity.bluepoint.util.logging.Trace;
import com.identity.bluepoint.util.logging.TraceManager;
import com.identity.bluepoint.web.application.PageDescriptor;
import com.identity.bluepoint.web.component.AjaxButton;
import com.identity.bluepoint.web.component.form.BluePointForm;
import com.identity.bluepoint.web.component.util.VisibleEnableBehaviour;
import com.identity.bluepoint.web.security.util.SecurityUtils;

/**
 * Base class for error web pages.
 *
 * @author lazyman
 */
@PageDescriptor(url = "/error", permitAll = true)
public class PageError extends PageBase {

    private static final String ID_CODE = "code";
    private static final String ID_LABEL = "label";
    private static final String ID_MESSAGE = "message";
    private static final String ID_ERROR_MESSAGE = "errorMessage";
    private static final String ID_BACK = "back";
    private static final String ID_HOME = "home";
    private static final String ID_LOGOUT_FORM = "logoutForm";
    private static final String ID_CSRF_FIELD = "csrfField";

    private static final Trace LOGGER = TraceManager.getTrace(PageError.class);

    private final Integer code;

    private String exClass;
    private String exMessage;

    public PageError() {
        this(500);
    }

    public PageError(Integer code) {
        this(code, null);
    }

    public PageError(Exception ex) {
        this(500, ex);
    }

    public PageError(Integer code, Exception ex) {
        this.code = code;
    }

    private int getCode() {
        return code != null ? code : 500;
    }

    @Override
    protected void configureResponse(WebResponse response) {
        super.configureResponse(response);

        response.setStatus(getCode());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript("$('div.content-wrapper').css('margin-left', '0');"));
    }

    @Override
    public boolean isVersioned() {
        return false;
    }

    @Override
    public boolean isErrorPage() {
        return true;
    }

    protected String getErrorMessageKey() {
        return "PageError.message";
    }

    private String getUrlForLogout() {
        //ModuleAuthentication moduleAuthentication = SecurityUtils.getAuthenticatedModule();

        //if (moduleAuthentication == null) {
        //    return SecurityUtils.DEFAULT_LOGOUT_PATH;
        //}
        //return SecurityUtils.getPathForLogoutWithContextPath(getRequest().getContextPath(), moduleAuthentication);
    	return null;
    }
}
