package com.identity.bluepoint.web.component.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.Response;

import com.identity.bluepoint.web.security.util.SecurityUtils;

/**
 * @author Viliam Repan (lazyman)
 * @author shood
 * @author Radovan Semancik
 */
public class BluePointForm<T> extends Form<T> {

    private boolean addFakeInputFields = false;

    public BluePointForm(String id) {
        super(id);
    }

    /**
     * Use this constructor when a form needs to display empty input field:
     * &lt;input style="display:none"&gt;
     * &lt;input type="password" style="display:none"&gt;
     * <p>
     * To overcome Chrome auto-completion of password and other form fields
     */
    public BluePointForm(String id, boolean addFakeInputFields) {
        super(id);
        this.addFakeInputFields = addFakeInputFields;
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        super.onComponentTagBody(markupStream, openTag);

        Response resp = getResponse();

        // add hidden input for CSRF
        SecurityUtils.appendHiddenInputForCsrf(resp);

        if (addFakeInputFields) {
            resp.write("<input style=\"display:none\">");
        }
    }
}
