package com.dandelion.gwt.domino.ui.client.ui.application.content.screen01;

import com.dandelion.gwt.domino.ui.shared.model.MyModel;
import com.dandelion.gwt.domino.ui.shared.model.oss.OSSObjectSummaryDTO;
import com.dandelion.gwt.domino.ui.shared.service.OssServiceFactory;
import com.github.nalukit.nalu.client.component.AbstractComponent;
import com.google.gwt.user.client.Window;
import elemental2.dom.*;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.GridLayout;
import org.dominokit.domino.ui.grid.Row_12;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.style.Color;

import static org.jboss.elemento.Elements.input;
import static org.jboss.elemento.Elements.p;

/**
 * TODO 页面一
 *
 * @author L-jf
 */
public class Screen01Component extends AbstractComponent<IScreen01Component.Controller, HTMLElement> implements IScreen01Component {
    private GridLayout gridLayout;
    private Card card1;
    private Card card2;


    private HTMLInputElement hiddenFileInput;

    public Screen01Component() {
        super();
    }

    @Override
    public void edit(MyModel model) {
        // That's a good place to move your data out of the model into the widgets.
        //
        // Using GWT 2.x you can use the editor framework and in this case
        // it is a good idea to edit and flush the data inside the presenter.


        ListGroup<OSSObjectSummaryDTO> listGroup = ListGroup.create();

        OssServiceFactory.INSTANCE.getBucketList(null).onSuccess(listGroup::setItems).send();

        card1.appendChild(listGroup.setItemRenderer((listGroup1, listItem) -> {
            listItem.setSelectable(true).appendChild(
                    FlexLayout.create().apply(self -> {
                        OSSObjectSummaryDTO value = listItem.getValue();
                        String key = value.getKey();

//                    StringUtil
//
//                    GWT.log(key.substring());
                        self.css(Color.GREY_LIGHTEN_4.getBackground());

                        self.appendChild(
                                FlexItem.create().apply(self1 -> {

                                    self1.appendChild(Row_12.create().appendChild(Column.span12().appendChild(p().add(key))));
                                })
                        );

                    })
            );
        }));

        card1.setTitle(model.getActiveScreen());
    }


    @Override
    public void render() {
        gridLayout = GridLayout.create();
        card1 = Card.create("");

        gridLayout.getContentElement().appendChild(card1);
        gridLayout.getContentElement().appendChild(card2());

        initElement(gridLayout.element());
    }

    /**
     * 文件上传
     *
     * @return card
     * @author L-jf
     */
    private Card card2() {
        Card card2 = Card.create("");
        Button button = Button.create("文件上传");
        button.addClickListener(evt -> {

            createHiddenInput();
            hiddenFileInput.click();
            hiddenFileInput.addEventListener("change", evt1 -> {
                FileList files = hiddenFileInput.files;
                for (int i = 0; i < files.length; i++) {
                    File file = files.item(i);
                    int size = file.size;
                    Window.alert(size + "");
                }
            });
        });
        card2.getBody().appendChild(button);
        return card2;
    }

    /**
     * 创建文件上传输入框
     *
     * @author L-jf
     */
    private void createHiddenInput() {
        hiddenFileInput = input("file")
                .style("visibility: hidden; position: absolute; top: 0px; left: 0px; height: 0px; width: 0px;").element();
        DomGlobal.document.body.appendChild(hiddenFileInput);
    }


    @Override
    public boolean isDirty() {
        return true;
    }

    @Override
    public boolean isValid() {
        // check if you widgets are valid (if the widgets you are using support validation)
        // This is a good place to check type-safety and required field
        //
        // In this example the data (cause there is none) will always be valid!
        return true;
    }

    @Override
    public void flush(MyModel model) {
        // move your data from the widgets to the model here ...
        //
        // It is a good idea to check the type before moving it into an object
    }
}
