package com.dandelion.gwt.domino.ui.server.feign;

import com.dandelion.gwt.domino.ui.shared.model.oss.OSSObjectSummaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TODO 调用oss
 *
 * @author L-jf
 * @version 1.0
 * @date 2021/12/10 16:39
 */
@FeignClient(value = "dandelion-oss")
public interface IOssFeign {

    @GetMapping("/getBucketList")
    List<OSSObjectSummaryDTO> getBucketList(@RequestParam(name = "keyPrefix",required = false) String keyPrefix);
}
