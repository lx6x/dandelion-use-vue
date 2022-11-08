package com.dandelion.gwt.domino.ui.client.ui.application.shell;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.github.nalukit.nalu.client.component.AbstractShell;
import com.github.nalukit.nalu.client.component.annotation.Shell;
import elemental2.dom.CSSProperties;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.dominokit.domino.ui.utils.DominoElement.div;

/**
 * TODO 主题
 *
 * @author L-jf
 */
@Shell("application")
public class ApplicationShell extends AbstractShell<DandelionDominoUiContext> {
    private static Layout layout;

    private final DominoElement<HTMLDivElement> code = div();

    public ApplicationShell() {
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
        layout.getLeftPanel().setId("navigation");

        // 右下加 源码 按钮
        layout.getContentPanel().appendChild(code.setId("lower-right-button"));

        layout.getContentPanel().setId("content");
    }

    @Override
    public void detachShell() {
        layout.remove();
    }

    public static Layout getLayout() {
        return layout;
    }
}
