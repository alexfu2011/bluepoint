package com.identity.bluepoint.web.page.error;

//import com.identity.bluepoint.gui.api.component.result.OpResult;
import com.identity.bluepoint.gui.api.page.PageBase;
//import com.identity.bluepoint.schema.result.OperationResult;
import com.identity.bluepoint.util.logging.Trace;
import com.identity.bluepoint.util.logging.TraceManager;
import com.identity.bluepoint.web.application.PageDescriptor;
import com.identity.bluepoint.web.application.Url;
import com.identity.bluepoint.web.component.AjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Page that displays just the operation result. Comes handy
 * for places where the operation result cannot be displayed
 * on the main page (e.g. object list warnings, projection list, etc.)
 *
 * @author semancik
 */
@PageDescriptor(
        urls = {
                @Url(mountUrl = "/result", matchUrlForSecurity = "/result")
        }, permitAll = true)
public class PageOperationResult extends PageBase {

    private static final long serialVersionUID = 1L;

    private static final String ID_BACK = "back";

    private static final Trace LOGGER = TraceManager.getTrace(PageOperationResult.class);

    //private OperationResult result = null;

    public PageOperationResult() {
        super();
    }

}
