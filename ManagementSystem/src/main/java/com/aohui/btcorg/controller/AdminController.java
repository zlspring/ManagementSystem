package com.aohui.btcorg.controller;

import com.aohui.btcorg.entity.AccountUserEntity;
import com.aohui.btcorg.model.ResultMap;
import com.aohui.btcorg.repo.AccountUserRepo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description admin角色权限controller
 * @Date 2020-01-15
 * @Time 17:32
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AccountUserRepo accountUserRepo;

    @Resource
    private  ResultMap resultMap;

    @RequestMapping("/getUser")
    @RequiresRoles("admin")
    @RequiresPermissions("vip")
    public ResultMap getUserLogin() {
        List<AccountUserEntity> list = accountUserRepo.findAllBy();
        return resultMap.success().code(200).message(list);
    }

    /**
     * 封号操作
     */
    @PostMapping("/banUser")
    @RequiresRoles("admin")
    @RequiresPermissions("vip")
    public ResultMap updatePassword(String username) {
        accountUserRepo.updateBan(username);
        return resultMap.success().code(200).message("Successful titles！");
    }
//
//    @PostMapping("/banUser")
//    @RequiresRoles("admin")
//    @RequiresPermissions("vip")
//    public ResultMap authentication() {
//
//        return resultMap.success().code(200).message("Successful titles！");
//    }

}
