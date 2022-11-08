package com.dandelion.gwt.domino.ui.client;

import com.github.nalukit.nalu.client.application.IsApplication;
import com.github.nalukit.nalu.client.application.annotation.Application;
import com.github.nalukit.nalu.client.application.annotation.Debug;
import com.github.nalukit.nalu.client.application.annotation.Filters;
import com.github.nalukit.nalu.plugin.elemental2.client.DefaultElemental2Logger;
import com.dandelion.gwt.domino.ui.client.filter.LoginFilter;

@Application(
        context = DandelionDominoUiContext.class,
        startRoute = "/login/login",
        loader = DandelionDominoUiLoader.class
)
@Debug(
        logger = DefaultElemental2Logger.class,
        logLevel = Debug.LogLevel.DETAILED
)
@Filters(
        filterClasses = LoginFilter.class
)
public interface DandelionDominoUiApplication extends IsApplication {
}
