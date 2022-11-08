package com.dandelion.gwt.domino.ui.client.ui.application.content.screen02;

import com.dandelion.gwt.domino.ui.shared.model.MyModel;
import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.cards.Card;

/**
 * TODO 页面一传值
 *
 * @author L-jf
 */
public class Screen02Component extends AbstractComponent<IScreen02Component.Controller, HTMLElement> implements IScreen02Component {
    private Card card;

    public Screen02Component() {
        super();
    }

    @Override
    public void edit(MyModel model) {
        // That's a good place to move your data out of the model into the widgets.
        //
        // Using GWT 2.x you can use the editor framework and in this case
        // it is a good idea to edit and flush the data inside the presenter.
        card.setTitle(model.getActiveScreen());
    }

    @Override
    public void render() {
        card = Card.create("");
        initElement(card.element());
    }
}
