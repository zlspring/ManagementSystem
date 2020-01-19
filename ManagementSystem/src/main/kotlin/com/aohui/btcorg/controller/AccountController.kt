//package com.aohui.btcorg.controller
//
////import com.aohui.btcorg.component.LoginManager
//import com.aohui.btcorg.model.dto.AccountInfoDto
//import com.aohui.btcorg.model.dto.AccountLoginInfoDto
//import com.aohui.btcorg.model.http.*
//import com.aohui.btcorg.service.AccountService
//import org.springframework.web.bind.annotation.*
//import javax.servlet.http.HttpServletRequest
//
//@RestController
//@RequestMapping("account")
//class AccountController(
////        val loginManager: LoginManager,
//        val accountService: AccountService
//) {
//
////    /**
////     * 注册
////     */
////    @PostMapping("signUp")
////    fun signUp(@RequestBody req: SignUpRequest): SignUpResponse {
////        accountService.signUp(AccountInfoDto(
////                username = req.username,
////                password = req.password
////        ))
////        return SignUpResponse(msg = "SUCCESS")
////    }
////
////    @PostMapping("signIn")
////    fun signIn(request: HttpServletRequest, @RequestBody req: SignInRequest): SignInResponse {
////        val ret = accountService.signIn(
////                AccountInfoDto(
////                        username = req.username,
////                        password = req.password
////                ),
////                request.remoteAddr
////        )
////        return SignInResponse(ret.token!!)
////    }
////
////    @GetMapping("loginOut")
////    fun loginOutLogin(
////            request: HttpServletRequest,
////            accountLoginInfoDto: AccountLoginInfoDto
////    ): BaseResponse {
////        loginManager.loginOut(accountLoginInfoDto.uid!!)
////        return BaseResponse("ok")
////    }
//}