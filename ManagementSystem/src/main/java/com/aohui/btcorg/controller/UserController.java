package com.aohui.btcorg.controller;

import com.aohui.btcorg.domain.QueryUsersReq;
import com.aohui.btcorg.entity.AccountUserEntity;
import com.aohui.btcorg.model.ResultMap;
import com.aohui.btcorg.repo.AccountUserRepo;
import com.aohui.btcorg.service.AccountUserService;
import com.aohui.btcorg.util.MD5HEXUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description user角色权限controller
 * @Date 2018-04-09
 * @Time 17:12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private  AccountUserRepo accountUserRepo;

    @Resource
    private AccountUserService accountUserService;

    @Resource
    private  ResultMap resultMap;

    /**
     * 拥有 user, admin 角色的用户可以访问下面的页面
     */
    @GetMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getMessage() {
        System.out.println(resultMap.success());
        return resultMap.success().code(200).message("Successful acquisition of information!");
    }

    /**
     * 修改密码
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/updatePassword")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap updatePassword(String username, String oldPassword, String newPassword) {
        AccountUserEntity dataBasePassword = accountUserRepo.findByUsername(username);
        String encodeMd5 = MD5HEXUtil.INSTANCE.encodeMd5(oldPassword);
        if (dataBasePassword.getPassword().equals(encodeMd5)) {
            accountUserRepo.updatePassword(username, newPassword);
        } else {
            return resultMap.fail().message("wrong password!");
        }
        return resultMap.success().code(200).message("Successful acquisition of information!");
    }

    /**
     * 忘记密码
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/forgetPwd")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap forgetPwd(@RequestParam("username") String username ,
                               @RequestParam("password")String password)
    {
        return accountUserService.forgetPwd(username, password);
    }

    /**
     *  未签约用户
     * @return
     */
    @PostMapping("/unauthenticated")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap unauthenticated()
    {
        return accountUserService.authentication();
    }


    /**
     * 未签约用户图片
     * @param username
     * @return
     */
    @PostMapping("/getPicture")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getPicture(@RequestParam("username")String username)
    {
        return accountUserService.getPicture(username);
    }

    /**
     * 未签约用户签约
     * @param username
     * @return
     */
    @PostMapping("/updateAttestation")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap updateAttestation(@RequestParam("username")String username)
    {
        return accountUserService.updateAttestation(username);
    }

    /**
     * 修改邮箱未审核用户
     * @return
     */
    @PostMapping("/updateEmail")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap updateEmail()
    {
        return accountUserService.updateEmail();
    }

    /**
     * 获取未签约邮箱的答题记录
     * @param username
     * @return
     */
    @PostMapping("/getTheAnswer")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getTheAnswer(@RequestParam("username")String username)
    {
        return accountUserService.getTheAnswer(username);
    }

    /**
     * 照片上传的对比记录
     * @param username
     * @return
     */
    @PostMapping("/comparison")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap comparison(@RequestParam("username")String username)
    {
        return accountUserService.comparison(username);
    }

    /**
     * 返回审核状态
     * @param username
     * @return
     */
    @PostMapping("/returnStu")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap returnStu(@RequestParam("username")String username)
    {
        return accountUserService.returnStu(username);
    }

    /**
     *  用以查询系统中已经存在的用户列表
     * @param queryKeyword
     * @return
     */
    @PostMapping("/queryUsers")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
        public String queryUsers(@RequestBody QueryUsersReq queryKeyword,HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String authentication = request.getHeader("Authentication");
        accountUserService.operationRecord("All user information with " + queryKeyword.getQueryKeyword(),token,queryKeyword.getQueryKeyword());
        queryKeyword.setVerified(false);
        return accountUserService.queryUsers(queryKeyword,authentication);
    }

    /**
     * 获取某个用户的身份认证信息
     * @param uid
     * @return
     */
    @PostMapping("/queryUserIdInfo")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public String queryUserIdInfo(HttpServletRequest request,
                                     @RequestParam("uid")String uid)
    {
        String token = request.getHeader("Token");
        String authentication = request.getHeader("Authentication");
        accountUserService.operationRecord(token,"Gets authentication information about a user",uid);
        return accountUserService.queryUserIdInfo(uid,authentication);
    }

    /**
     * 标记身份认证结果
     * @param uid
     * @param idNum
     * @param valid
     * @param msg
     * @return
     */
    @PostMapping("/markConfirmed")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public String markConfirmed(HttpServletRequest request,
                                   @RequestParam("uid")String uid,
                                   @RequestParam("idNum")String idNum,
                                   @RequestParam("valid")String valid,
                                   @RequestParam("msg")String msg)
    {
        String token = request.getHeader("Token");
        String authentication = request.getHeader("Authentication");
        accountUserService.operationRecord(token,"Authentication results",uid);
        return accountUserService.markConfirmed(uid,idNum,valid,msg,authentication);
    }

    /**
     * 查询用户安全状态
     * @param uid
     * @return
     */
    @PostMapping("/queryUsrSecurityInfo")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public String queryUsrSecurityInfo(HttpServletRequest request, @RequestParam("uid")String uid)
    {
        String token = request.getHeader("Token");
        String authentication = request.getHeader("Authentication");
        accountUserService.operationRecord(token,"Account security status",uid);
        return accountUserService.queryUsrSecurityInfo(uid,authentication);
    }


}
