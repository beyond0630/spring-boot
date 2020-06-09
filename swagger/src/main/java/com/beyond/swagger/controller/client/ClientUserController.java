package com.beyond.swagger.controller.client;

import com.beyond.swagger.controller.common.UserController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/11/14
 */
@Api(tags = "客户端 -- 用户管理")
@RestController
@RequestMapping("/api/client/user")
public class ClientUserController extends UserController {


}
