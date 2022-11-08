package com.dandelion.gwt.domino.ui.client.ui.application.shell.content.lowerright;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.github.nalukit.nalu.client.component.AbstractComponentController;
import com.github.nalukit.nalu.client.component.annotation.Controller;
import elemental2.dom.HTMLElement;

/**
 * TODO 右下按钮弹框
 *
 * @author L-jf
 * @version 1.0
 * @date 2021/12/7 15:15
 */
@Controller(
        route = "/application",
        selector = "lower-right-button",
        component = LowerRightButtonComponent.class,
        componentInterface = ILowerRightButtonComponent.class
)
public class LowerRightButtonController extends AbstractComponentController<DandelionDominoUiContext, ILowerRightButtonComponent, HTMLElement> implements ILowerRightButtonComponent.Controller {
}
