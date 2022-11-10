package com.dandelion.gwt.domino.ui.client.ui.application.content.screen02;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.dandelion.gwt.domino.ui.client.event.StatusChangeEvent;
import com.dandelion.gwt.domino.ui.shared.model.MyModel;
import com.github.nalukit.nalu.client.component.AbstractComponentController;
import com.github.nalukit.nalu.client.component.annotation.Controller;
import elemental2.dom.HTMLElement;

/**
 * TODO 页面一传值
 *
 * @author L-jf
 */
@Controller(
        route = "/application/screen02",
        selector = "content",
        componentInterface = IScreen02Component.class,
        component = Screen02Component.class
)
public class Screen02Controller extends AbstractComponentController<DandelionDominoUiContext, IScreen02Component, HTMLElement> implements IScreen02Component.Controller {
    private MyModel model;

    public Screen02Controller() {
    }

    @Override
    public void start() {
        // Here we simulate the creation of a model.
        // In the real world we would do a server call or
        // something else to get the data.
        model = new MyModel("This value is set using the edit method! The value is >>" + "Screen02" + "<<");
        //
        // now, move the data out of the model into the widgets - that's what we do next
        component.edit(model);
        // update the statusbar at the bottom of the screen
        eventBus.fireEvent(new StatusChangeEvent("active screen: >>Screen02<<"));
    }
}
