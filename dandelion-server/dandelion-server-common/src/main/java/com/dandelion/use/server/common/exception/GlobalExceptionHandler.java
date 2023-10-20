package com.dandelion.use.server.common.exception;

import com.dandelion.use.server.common.result.R;
import com.dandelion.use.server.common.result.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;


/**
 * 全局异常处理捕获
 *
 * @author L
 * @version 1.0
 * @date 2022/06/15 10:13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req .
     * @param e   .
     * @return 自定义返回
     * @author L
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public R<String> globalException(HttpServletRequest req, GlobalException e) {
        logger.error("## 发生业务异常！原因是：{}", e.getErrorMessage());
        return R.fail(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param req .
     * @param e   .
     * @return 自定义返回
     * @author L
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public R<String> exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("## 空指针异常！原因是: ", e);
        return R.fail(ResultCode.FAIL.getMessage());
    }

    /**
     * 处理违反唯一键的约束
     *
     * @param req .
     * @param e   .
     * @return 自定义返回
     * @author L
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public R<String> sqlExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("## SQL异常！原因是: ", e);
        return R.fail(ResultCode.FAIL.getCode(), "唯一，不可重复");
    }
}
