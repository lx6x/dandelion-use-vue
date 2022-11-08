package com.dandelion.gwt.domino.ui.client.ui.application.shell.content.navigation;

import com.dandelion.gwt.domino.ui.client.ui.application.shell.ApplicationShell;
import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.icons.MdiIcon;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;
import org.jboss.elemento.Elements;

import static org.dominokit.domino.ui.grid.Column.span;

/**
 * TODO 左面板
 *
 * @author L-jf
 */
public class NavigationComponent extends AbstractComponent<INavigationComponent.Controller, HTMLElement> implements INavigationComponent {
    private TreeItem Screen01Item;

    private TreeItem Screen02Item;

    public NavigationComponent() {
        super();
    }

    @Override
    public void render() {
        this.Screen01Item = TreeItem.create("Screen01", Icons.ALL.list())
                .addClickListener(e -> getController().doNavigateTo("screen01"));
        this.Screen02Item = TreeItem.create("Screen02", Icons.ALL.list())
                .addClickListener(e -> getController().doNavigateTo("screen02"));
        Tree tree = Tree.create();
        addLock(tree);
        tree.appendChild(Screen01Item);
        tree.appendChild(Screen02Item);
        initElement(tree.element());
    }


    private void addLock(Tree<String> tree) {
        MdiIcon lockMdiIcon = Icons.ALL.lock_mdi();

        MdiIcon lockOpenMdiIcon = Icons.ALL.lock_open_outline_mdi();
        // 导航lock
        Column span = span();
        span.appendChild(lockMdiIcon);
        span.appendChild(lockOpenMdiIcon);
        lockOpenMdiIcon.style().setCursor("pointer").setFloat("right");
        lockMdiIcon.style().setDisplay("none");

        lockMdiIcon.addClickListener(evt -> {
            // 取消固定左面板
            ApplicationShell.getLayout().unfixLeftPanelPosition().hideLeftPanel();
            lockMdiIcon.style().setDisplay("none");
            lockOpenMdiIcon.style().setDisplay("block").setCursor("pointer").setFloat("right");
        });

        lockOpenMdiIcon.addClickListener(evt -> {
            ApplicationShell.getLayout().fixLeftPanelPosition();
            lockMdiIcon.style().setDisplay("block").setCursor("pointer").setFloat("right");
            lockOpenMdiIcon.style().setDisplay("none");
        });

        HTMLElement title = Elements.span().css("title").element();
        title.textContent="navigation";
        tree.getHeader().appendChild(
                span.appendChild(title)
        );
        tree.getHeader().setAttribute("style", "display: block;");
    }
}
