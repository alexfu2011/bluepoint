package com.identity.bluepoint.web.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.IPageParametersEncoder;

import com.identity.bluepoint.security.api.AuthorizationConstants;
import com.identity.bluepoint.util.ClassPathUtil;
import com.identity.bluepoint.util.DebugDumpable;
import com.identity.bluepoint.util.DebugUtil;
import com.identity.bluepoint.util.DisplayableValue;
import com.identity.bluepoint.web.security.BluePointApplication;
import com.identity.bluepoint.util.ExactMatchMountedMapper;
import com.identity.bluepoint.util.exception.SystemException;
import com.identity.bluepoint.util.logging.Trace;
import com.identity.bluepoint.util.logging.TraceManager;

/**
 * @author lazyman
 */
public final class DescriptorLoader implements DebugDumpable {

    private static final Trace LOGGER = TraceManager.getTrace(DescriptorLoader.class);

    private static final String[] PACKAGES_TO_SCAN = {
            "com.identity.bluepoint.web.page",
            "com.identity.bluepoint.web.page.admin.home",
            "com.identity.bluepoint.web.page.admin.users",
            "com.identity.bluepoint.web.page.admin.services",
            "com.identity.bluepoint.web.page.admin.roles",
            "com.identity.bluepoint.web.page.admin.resources",
            "com.identity.bluepoint.web.page.admin.resources.content",
            "com.identity.bluepoint.web.page.admin.workflow",
            "com.identity.bluepoint.web.page.admin.server",
            "com.identity.bluepoint.web.page.admin.reports",
            "com.identity.bluepoint.web.page.admin.configuration",
            "com.identity.bluepoint.web.page.admin.certification",
            "com.identity.bluepoint.web.page.admin.valuePolicy",
            "com.identity.bluepoint.web.page.admin.cases",
            "com.identity.bluepoint.web.page.admin.archetype",
            "com.identity.bluepoint.web.page.admin.objectCollection",
            "com.identity.bluepoint.web.page.login",
            "com.identity.bluepoint.web.page.error",
            "com.identity.bluepoint.web.page.forgetpassword",
            "com.identity.bluepoint.web.page.self",
            "com.identity.bluepoint.web.component.prism.show"
    };

    private static Map<String, DisplayableValue<String>[]> actions = new HashMap<>();
    private static List<String> permitAllUrls = new ArrayList<>();
    private static List<String> loginPages = new ArrayList<>();
    private static Map<String, Class> urlClassMap = new HashMap<>();

    public static Map<String, DisplayableValue<String>[]> getActions() {
        return actions;
    }

    public static Map<String, Class> getUrlClassMap() {
        return urlClassMap;
    }

    public static Collection<String> getPermitAllUrls() {
        return permitAllUrls;
    }

    public static List<String> getLoginPages() {
        return loginPages;
    }

    public void loadData(BluePointApplication application) {
        System.out.println("Loading data from descriptor files.");
        LOGGER.debug("Loading data from descriptor files.");

        try {
            scanPackagesForPages(application);

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("loaded:\n{}", debugDump(1));
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Error scanning packages for pages: {}", e.getMessage(), e);
            throw new SystemException("Error scanning packages for pages: "+e.getMessage(), e);
        }



    }

    private void scanPackagesForPages(BluePointApplication application)
            throws InstantiationException, IllegalAccessException {

        for (String pac : PACKAGES_TO_SCAN) {
            LOGGER.debug("Scanning package package {} for page annotations", new Object[]{pac});

            Set<Class<?>> classes = ClassPathUtil.listClasses(pac);
            for (Class<?> clazz : classes) {
                if (!WebPage.class.isAssignableFrom(clazz)) {
                    continue;
                }

                PageDescriptor descriptor = clazz.getAnnotation(PageDescriptor.class);
                if (descriptor == null) {
                    continue;
                }

                mountPage(descriptor, clazz, application);
                loadActions(descriptor);
            }
        }
    }

    private void loadActions(PageDescriptor descriptor) {

        if(descriptor.loginPage()) {
            foreachUrl(descriptor, url -> loginPages.add(url));
        }

        if (descriptor.permitAll()) {
            foreachUrl(descriptor, url -> permitAllUrls.add(url));
            return;
        }

        List<AuthorizationActionValue> actions = new ArrayList<>();

        //avoid of setting guiAll authz for "public" pages (e.g. login page)
        if (descriptor.action() == null || descriptor.action().length == 0) {
            return;
        }

        boolean canAccess = true;

        for (AuthorizationAction action : descriptor.action()) {
            actions.add(new AuthorizationActionValue(action.actionUri(), action.label(), action.description()));
            if (AuthorizationConstants.AUTZ_NO_ACCESS_URL.equals(action.actionUri())) {
                canAccess = false;
                break;
            }
        }

        //add http://.../..#guiAll authorization only for displayable pages, not for pages used for development..
        if (canAccess) {

            actions.add(new AuthorizationActionValue(AuthorizationConstants.AUTZ_GUI_ALL_DEPRECATED_URL,
                    AuthorizationConstants.AUTZ_GUI_ALL_LABEL, AuthorizationConstants.AUTZ_GUI_ALL_DESCRIPTION));
            actions.add(new AuthorizationActionValue(AuthorizationConstants.AUTZ_GUI_ALL_URL,
                    AuthorizationConstants.AUTZ_GUI_ALL_LABEL, AuthorizationConstants.AUTZ_GUI_ALL_DESCRIPTION));
        }

        foreachUrl(descriptor, url -> this.actions.put(url, actions.toArray(new DisplayableValue[actions.size()])));
    }

    private void foreachUrl(PageDescriptor descriptor, Consumer<String> urlConsumer) {
        for (String url : descriptor.url()) {
            urlConsumer.accept(buildPrefixUrl(url));
        }

        for (Url url : descriptor.urls()) {
            String urlForSecurity = url.matchUrlForSecurity();
            if (StringUtils.isEmpty(urlForSecurity)) {
                urlForSecurity = buildPrefixUrl(url.mountUrl());
            }
            urlConsumer.accept(urlForSecurity);
        }
    }

    public String buildPrefixUrl(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append(url);

        if (!url.endsWith("/")) {
            sb.append("/");
        }
        sb.append("**");

        return sb.toString();
    }

    private void mountPage(PageDescriptor descriptor, Class clazz, BluePointApplication application)
            throws InstantiationException, IllegalAccessException {

        //todo remove for cycle later
        for (String url : descriptor.url()) {
            IPageParametersEncoder encoder = descriptor.encoder().newInstance();

            LOGGER.trace("Mounting page '{}' to url '{}' with encoder '{}'.", new Object[]{
                    clazz.getName(), url, encoder.getClass().getSimpleName()});

            application.mount(new ExactMatchMountedMapper(url, clazz, encoder));
            urlClassMap.put(url, clazz);
        }

        for (Url url : descriptor.urls()) {
            IPageParametersEncoder encoder = descriptor.encoder().newInstance();

            LOGGER.trace("Mounting page '{}' to url '{}' with encoder '{}'.", new Object[]{
                    clazz.getName(), url, encoder.getClass().getSimpleName()});

            application.mount(new ExactMatchMountedMapper(url.mountUrl(), clazz, encoder));
            urlClassMap.put(url.mountUrl(), clazz);
        }
    }

    @Override
    public String debugDump() {
        return debugDump(0);
    }

    @Override
    public String debugDump(int indent) {
        StringBuilder sb = new StringBuilder();
        DebugUtil.indentDebugDump(sb, indent);
        sb.append("DescriptorLoader\n");
        DebugUtil.debugDumpWithLabelLn(sb, "actions", actions, indent + 1);
        DebugUtil.debugDumpWithLabel(sb, "urlClassMap", urlClassMap, indent + 1);
        return sb.toString();
    }
}
