package com.dandelion.gwt.domino.ui.server.controller;

import com.dandelion.gwt.domino.ui.server.feign.IOssFeign;
import com.dandelion.gwt.domino.ui.shared.model.oss.OSSObjectSummaryDTO;
import com.dandelion.gwt.domino.ui.shared.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration
@RestController
public class OssController implements OssService {

    @Autowired
    private IOssFeign iOssFeign;

    @Override
    @RequestMapping(value = "/getBucketList", method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public List<OSSObjectSummaryDTO> getBucketList(String keyPrefix) {
//        return iOssFeign.getBucketList(keyPrefix);
        return new ArrayList<>();
    }
}
