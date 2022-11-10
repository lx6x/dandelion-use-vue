package com.dandelion.gwt.domino.ui.shared.service;

import org.dominokit.domino.rest.shared.request.service.annotations.RequestFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * TODO oss 对接
 *
 * @author L-jf
 * @version 1.0
 * @date 2021/12/9 13:55
 */
@RequestFactory
public interface DominoUiService {


    @Path(("/getList"))
    @GET
    List<String> getList(@QueryParam("s") String s);
}
