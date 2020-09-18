package com.beyond.distributedlock.advice;

import com.beyond.distributedlock.common.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * flash correct
 *
 * @author Gent Liu
 * @date 2019/6/4 18:34
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public Object handleException(final RuntimeException e) {
        LOGGER.error(e.getMessage(), e);
        return ApiResult.error(e.getMessage());
    }

}
