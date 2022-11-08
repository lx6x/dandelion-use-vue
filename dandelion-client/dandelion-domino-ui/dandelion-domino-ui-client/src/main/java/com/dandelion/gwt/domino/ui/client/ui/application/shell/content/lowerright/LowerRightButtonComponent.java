package com.dandelion.gwt.domino.ui.client.ui.application.shell.content.lowerright;

import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.modals.IsModalDialog;
import org.dominokit.domino.ui.modals.ModalDialog;
import org.dominokit.domino.ui.style.Color;

/**
 * TODO 右下按钮弹框
 *
 * @author L-jf
 * @version 1.0
 * @date 2021/12/7 15:16
 */
public class LowerRightButtonComponent extends AbstractComponent<ILowerRightButtonComponent.Controller, HTMLElement> implements ILowerRightButtonComponent {

    private final ModalDialog modal = ModalDialog.create("Lower Right Button");

    @Override
    public void render() {
        modal.setType(IsModalDialog.ModalType.RIGHT_SHEET)
                .setSize(IsModalDialog.ModalSize.LARGE);

        Button addButton = Button.create(Icons.ALL.file_code_mdi())
                .setBackground(Color.BLUE_GREY_LIGHTEN_4)
                .setContent("Code")
                .styler(style -> style.add("gwt-code-button"))
                .addClickListener((evt) -> modal.open());

        initElement(addButton.element());
    }
}
