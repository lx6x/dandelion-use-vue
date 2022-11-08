package com.dandelion.gwt.domino.ui.client.ui.application.shell.content.statusbar;

import com.github.nalukit.nalu.client.component.IsComponent;
import elemental2.dom.HTMLElement;

/**
 * TODO controller 与 component传参使用
 *
 * @author L-jf
 */
public interface IStatusbarComponent extends IsComponent<IStatusbarComponent.Controller, HTMLElement> {


    /**
     * 编辑
     *
     * @author L-jf
     */
    void edit(String message);

    interface Controller extends IsComponent.Controller {
    }
}
