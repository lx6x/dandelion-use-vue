package com.dandelion.gwt.domino.ui.client.filter;

import com.dandelion.gwt.domino.ui.client.DandelionDominoUiContext;
import com.github.nalukit.nalu.client.filter.AbstractFilter;

/**
 * TODO 过滤器
 *
 * @author L-jf
 */
public class LoginFilter extends AbstractFilter<DandelionDominoUiContext> {
    @Override
    public boolean filter(String route, String... parms) {
        /*if (!"/login/login".equals(route)) {
            if (!this.context.isLoggedIn()) {
                return false;
            }
        }*/
        return true;
    }


    @Override
    public String redirectTo() {
        return "/login/login";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
