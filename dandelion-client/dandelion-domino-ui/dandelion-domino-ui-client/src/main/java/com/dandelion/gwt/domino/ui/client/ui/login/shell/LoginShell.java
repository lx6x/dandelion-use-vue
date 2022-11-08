package com.dandelion.gwt.domino.ui.client.ui.login.shell;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.github.nalukit.nalu.client.component.AbstractShell;
import com.github.nalukit.nalu.client.component.annotation.Shell;
import elemental2.dom.CSSProperties;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;

/**
 * TODO 登录页
 */
@Shell("login")
public class LoginShell extends AbstractShell<DandelionDominoUiContext> {
    private Layout layout;

    public LoginShell() {
        super();
    }

    @Override
    public void attachShell() {
        layout = Layout.create("阿巴~阿巴~阿巴~~")
            .show(ColorScheme.INDIGO);
        layout.showFooter()
            .fixFooter()
            .getFooter()
            .element().style.minHeight = CSSProperties.MinHeightUnionType.of("24px");
        layout.getFooter().setId("footer");
        layout.disableLeftPanel();
        layout.getContentPanel().setId("content");
    }

    @Override
    public void detachShell() {
        this.layout.remove();
    }
}
