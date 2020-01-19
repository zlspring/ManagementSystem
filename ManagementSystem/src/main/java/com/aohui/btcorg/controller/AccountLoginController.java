package com.aohui.btcorg.controller;

import com.aohui.btcorg.domain.SigReq;
import com.aohui.btcorg.entity.AccountUserEntity;
import com.aohui.btcorg.model.ResultMap;
import com.aohui.btcorg.repo.AccountUserRepo;
import com.aohui.btcorg.service.AccountUserService;
import com.aohui.btcorg.util.JwtUtil;
import com.aohui.btcorg.util.LoginMassage;
import com.aohui.btcorg.util.MD5HEXUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/account")
public class AccountLoginController {

    @Resource
    private AccountUserRepo accountUserRepo;

    @Resource
    private AccountUserService accountUserService;

    @Resource
    private LoginMassage loginMassage;

    @Resource
    private ResultMap resultMap;

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password, HttpServletRequest request) throws ServletException, IOException
    {
        String encodeMd5 = MD5HEXUtil.INSTANCE.encodeMd5(password);
        String sign = JwtUtil.sign(username, MD5HEXUtil.INSTANCE.encodeMd5(username));
        loginMassage.set(username,sign);
//        accountUserService.loginMessage(request,username);
        AccountUserEntity byPassword = accountUserRepo.findByUsername(username);
        if (byPassword.getPassword() == null) {
            return resultMap.fail().code(401).message("Username error");
        } else if (!byPassword.getPassword().equals(encodeMd5)) {
            return resultMap.fail().code(401).message("wrong password");
        } else {
            return resultMap.success().code(200).message(sign);
        }
    }

    /**
     * 注册
     * @param sigReq
     * @return
     */
    @RequestMapping("/sigUp")
    @RequiresRoles("admin")
    @RequiresPermissions("vip")
    @ResponseBody
    public ResultMap sigUp(@RequestBody SigReq sigReq)
    {
        String sigUp = accountUserService.SigUp(sigReq);
        return resultMap.success().code(200).message(sigUp);
    }

}
