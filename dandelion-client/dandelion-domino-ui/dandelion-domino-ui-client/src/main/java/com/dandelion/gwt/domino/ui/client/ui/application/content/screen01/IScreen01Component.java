package com.dandelion.gwt.domino.ui.client.ui.application.content.screen01;

import com.dandelion.gwt.domino.ui.shared.model.MyModel;
import com.github.nalukit.nalu.client.component.IsComponent;
import elemental2.dom.HTMLElement;

/**
 * TODO 页面一传值
 *
 * @author L-jf
 */
public interface IScreen01Component extends IsComponent<IScreen01Component.Controller, HTMLElement> {
    void edit(MyModel model);

    boolean isDirty();

    boolean isValid();

    void flush(MyModel model);

    interface Controller extends IsComponent.Controller {
    }
}
