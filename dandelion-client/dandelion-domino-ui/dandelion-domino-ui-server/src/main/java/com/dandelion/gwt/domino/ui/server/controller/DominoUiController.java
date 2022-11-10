package com.dandelion.gwt.domino.ui.server.controller;

import com.dandelion.gwt.domino.ui.shared.service.DominoUiService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * TODO
 *
 * @author LJF
 * @version 1.0
 * @date 2021/12/09 23:21
 */
@CrossOrigin
@RestController
public class DominoUiController implements DominoUiService {

    @Override
    @RequestMapping(value = "/getList", method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public List<String> getList(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i<=Integer.parseInt(s); i++){
            list.add(String.valueOf(i));
        }
        return list;
    }
}
