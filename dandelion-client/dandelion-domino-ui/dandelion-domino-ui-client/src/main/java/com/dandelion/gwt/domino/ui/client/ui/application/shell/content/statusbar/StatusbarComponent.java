package com.dandelion.gwt.domino.ui.client.ui.application.shell.content.statusbar;

import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.style.Style;
import org.jboss.elemento.Elements;

/**
 * TODO 底部状态栏组件
 *
 * @author L-jf
 */
public class StatusbarComponent extends AbstractComponent<IStatusbarComponent.Controller, HTMLElement> implements IStatusbarComponent {
    private HTMLDivElement messageInfo;

    public StatusbarComponent() {
        super();
    }

    @Override
    public void edit(String message) {
        messageInfo.textContent = message;
    }

    @Override
    public void render() {

        HTMLElement illustrate = Elements.span().css("title").style("mar").element();
        illustrate.textContent="Copyright © 2020 - 2021 www.liujf.xyz 备案/许可证编号：京ICP备2021004282号";
        messageInfo = Elements.div().element();
        initElement(Row.create()
                .style()
                .setMargin("0px")
                .setMinHeight("24px")
                .add("demo-footer")
                .get()
                .addColumn(Style.of(Column.span6())
                        .setTextAlign("left")
                        .get()
                        .appendChild(this.messageInfo))
                .addColumn(Style.of(Column.span6())
                        .setTextAlign("right")
                        .setMarginRight("20px")
                        .get()
                        .appendChild(illustrate))
                .element());
    }
}
