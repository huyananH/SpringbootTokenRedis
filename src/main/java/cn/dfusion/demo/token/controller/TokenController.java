package cn.dfusion.demo.token.controller;

import cn.dfusion.demo.token.authorization.Authorization;
import cn.dfusion.demo.token.entity.TokenModel;
import cn.dfusion.demo.token.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TokenController {

    @Autowired
    private TokenManager tokenManager;

    /**
     * 登录
     */
    @GetMapping("/signin")
    public @ResponseBody
    TokenModel signin(@RequestParam String userId) {
        TokenModel tokenModel = tokenManager.createToke(Long.parseLong(userId));
        return tokenModel;
    }

    /**
     * 退出登录
     */
    @Authorization
    @GetMapping("/signout")
    public String signout(@RequestParam String userId) {
        tokenManager.deleteToke(Long.parseLong(userId));
        return "删除成功";
    }
}
