package com.identity.bluepoint.web.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.model.IModel;

/**
 * @author lazyman
 */
public abstract class AjaxButton extends AjaxLink<String> {

    public AjaxButton(String id) {
        super(id);
    }

    public AjaxButton(String id, IModel<String> model) {
        super(id, model);
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        String text = getModelObject();
        if (StringUtils.isNotEmpty(text)) {
            replaceComponentTagBody(markupStream, openTag, text);
            return;
        }

        super.onComponentTagBody(markupStream, openTag);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (tag.isOpenClose()) {
            tag.setType(XmlTag.TagType.OPEN);
        }
    }
}
