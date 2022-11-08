package com.dandelion.gwt.domino.ui.client;

import com.github.nalukit.nalu.plugin.elemental2.client.NaluPluginElemental2;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import org.dominokit.domino.api.shared.extension.ContextAggregator;
import org.dominokit.domino.rest.DominoRestConfig;
import org.dominokit.domino.rest.shared.Response;
import org.dominokit.domino.rest.shared.request.FailedResponseBean;
import org.dominokit.domino.rest.shared.request.RequestInterceptor;
import org.dominokit.domino.rest.shared.request.ResponseInterceptor;
import org.dominokit.domino.rest.shared.request.ServerRequest;


/**
 * TODO 程序主入口
 *
 * @author L-jf
 */
public class DandelionDominoUi implements EntryPoint {


    /**
     * 入口点方法，通过加载将实现类声明为入口点的模块自动调用。
     */
    @Override
    public void onModuleLoad() {

        DominoRestConfig.initDefaults();
        DominoRestConfig instance = DominoRestConfig.getInstance();
//        instance .setDefaultServiceRoot("http://192.168.1.4:30009/");
        instance.setDefaultServiceRoot("http://127.0.0.1:30005/");
//        instance.setDefaultServiceRoot("http://192.168.206.1:30005/");

        // 设置全局请求拦截
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void interceptRequest(ServerRequest serverRequest, ContextAggregator.ContextWait<ServerRequest> contextWait) {
                // 全局请求头封装
                serverRequest.setHeader("token", "1");

                contextWait.complete(serverRequest);
            }
        };
        instance.addRequestInterceptor(requestInterceptor);

        // 设置全局响应拦截器
        ResponseInterceptor responseInterceptor = new ResponseInterceptor() {
            @Override
            public void interceptOnSuccess(ServerRequest serverRequest, Response response) {
                ResponseInterceptor.super.interceptOnSuccess(serverRequest, response);
                //  成功响应拦截
                GWT.log("================ 成功拦截了");
            }

            @Override
            public void interceptOnFailed(ServerRequest serverRequest, FailedResponseBean failedResponse) {
//                ResponseInterceptor.super.interceptOnFailed(serverRequest, failedResponse);
                // 失败响应拦截
                GWT.log("================ 失败拦截了");
                int statusCode = failedResponse.getStatusCode();
                GWT.log("statusCode：" + statusCode);


            }
        };
        instance.addResponseInterceptor(responseInterceptor);


        DandelionDominoUiApplication application = new DandelionDominoUiApplicationImpl();
        application.run(new NaluPluginElemental2());
    }
}
