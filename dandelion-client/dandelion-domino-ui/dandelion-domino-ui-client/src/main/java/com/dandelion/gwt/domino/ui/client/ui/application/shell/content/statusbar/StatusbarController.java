package com.dandelion.gwt.domino.ui.client.ui.application.shell.content.statusbar;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.github.nalukit.nalu.client.component.AbstractComponentController;
import com.github.nalukit.nalu.client.component.annotation.Controller;
import elemental2.dom.HTMLElement;
import com.dandelion.gwt.domino.ui.client.event.StatusChangeEvent;
import org.gwtproject.event.shared.HandlerRegistration;

/**
 * TODO 加载底部组件
 *
 * @author L-jf
 */
@Controller(
        route = "/application/",
        selector = "footer",
        componentInterface = IStatusbarComponent.class,
        component = StatusbarComponent.class
)
public class StatusbarController extends AbstractComponentController<DandelionDominoUiContext, IStatusbarComponent, HTMLElement> implements IStatusbarComponent.Controller {
    private HandlerRegistration registration;

    public StatusbarController() {
    }

    @Override
    public void start() {
        this.registration = this.eventBus.addHandler(StatusChangeEvent.TYPE, e -> component.edit(e.getStatus()));
    }

    @Override
    public void stop() {
        this.registration.removeHandler();
    }
}
