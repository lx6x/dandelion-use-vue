package com.dandelion.gwt.domino.ui.client.ui.application.content.screen01;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.dandelion.gwt.domino.ui.shared.model.MyModel;
import com.github.nalukit.nalu.client.component.AbstractComponentController;
import com.github.nalukit.nalu.client.component.annotation.Controller;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import com.dandelion.gwt.domino.ui.client.event.StatusChangeEvent;

/**
 * TODO 页面一   需要启动 【dandelion-oss 进行测试】
 *
 * @author L-jf
 */
@Controller(
        route = "/application/screen01",
        selector = "content",
        componentInterface = IScreen01Component.class,
        component = Screen01Component.class
)
public class Screen01Controller extends AbstractComponentController<DandelionDominoUiContext, IScreen01Component, HTMLElement> implements IScreen01Component.Controller {
    private MyModel model;

    public Screen01Controller() {
    }

    @Override
    public void start() {
        model = new MyModel("文件列表");
        component.edit(model);
        // update the statusbar at the bottom of the screen
        eventBus.fireEvent(new StatusChangeEvent("active screen: >>Screen01<<"));
    }

    /**
     * The mayStop method will be called by the framework in
     * case a navigation event occured.
     * <p>
     * It is up to this method to decide if the navigation event
     * will be executed or not.
     * <p>
     * this is a good place to validate the entered data and
     * move it into the model.
     */
    @Override
    public String mayStop() {
        // check if there are changes
        if (component.isDirty()) {
            // are you sure? :-)
            if (DomGlobal.window.confirm("Do you really want to cancel?")) {
                // ok, but before, we check the entered data (type safety and required fields)
                if (component.isValid()) {
                    // move the data into the model
                    component.flush(model);
                    // navigate!
                    return null;
                } else {
                    return "Please correct the error!";
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
