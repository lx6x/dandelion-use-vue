package com.dandelion.gwt.domino.ui.client;

import com.github.nalukit.nalu.client.Router;
import com.github.nalukit.nalu.client.application.AbstractApplicationLoader;
import com.github.nalukit.nalu.client.application.IsApplicationLoader;
import org.gwtproject.event.shared.SimpleEventBus;

/**
 * TODO 加载
 *
 * @author L-jf
 */
public class DandelionDominoUiLoader extends AbstractApplicationLoader<DandelionDominoUiContext> {

    @Override
    public void setEventBus(SimpleEventBus eventBus) {
        super.setEventBus(eventBus);
    }

    @Override
    public void setRouter(Router router) {
        super.setRouter(router);
    }

    @Override
    public void load(IsApplicationLoader.FinishLoadCommand finishLoadCommand) {
        // enter your code here ...
        finishLoadCommand.finishLoading();
    }
}
