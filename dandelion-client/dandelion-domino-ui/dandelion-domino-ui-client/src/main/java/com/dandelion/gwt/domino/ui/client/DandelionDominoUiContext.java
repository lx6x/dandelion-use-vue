package com.dandelion.gwt.domino.ui.client;

import com.github.nalukit.nalu.client.context.IsContext;

public class DandelionDominoUiContext implements IsContext {
    private boolean loggedIn;

    public DandelionDominoUiContext() {
        // enter your constructor code here ...

    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
