package com.aohui.btcorg.service.impl;

import com.alibaba.fastjson.JSON;
import com.aohui.btcorg.domain.QueryUsersReq;
import com.aohui.btcorg.domain.SigReq;
import com.aohui.btcorg.entity.AccountLoginEntity;
import com.aohui.btcorg.entity.AccountUserEntity;
import com.aohui.btcorg.entity.BackgroundOperationRecordEntity;
import com.aohui.btcorg.model.ResultMap;
import com.aohui.btcorg.repo.AccountUserRepo;
import com.aohui.btcorg.repo.BackgroundOperationRecordRepo;
import com.aohui.btcorg.repo.LoginMassageRepo;
import com.aohui.btcorg.service.AccountUserService;
import com.aohui.btcorg.util.*;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class AccountUserServiceImpl implements AccountUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String http = null;

    String pwd = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";

    @Resource
    private AccountUserRepo accountUserRepo;

    @Resource
    private ResultMap resultMap;

    @Resource
    private BackgroundOperationRecordRepo backgroundOperationRecordRepo;

    @Resource
    private LoginMassageRepo loginMassageRepo;

    private static Map<String,String> bodyStringMap = null;

    private static Map<String,Object> bodyObjMap = null;

    @Override
    public String SigUp(SigReq sigReq)
    {
        AccountUserEntity accountUserEntity = new AccountUserEntity();
        if (Pattern.matches(sigReq.getPassword(),pwd))
        {
            logger.debug("AccountUserServiceImpl SigUp message = " +
                    "At least 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 digit and 1 special character " +
                    "Password = " + sigReq.getPassword());
            return "At least 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 digit and 1 special character";
        }
        AccountUserEntity byUsername = accountUserRepo.findByUsername(sigReq.getUsername());

        if (null != byUsername)
        {
            if (sigReq.getUsername().equals(byUsername.getUserName()))
            {
                logger.debug("AccountUserServiceImpl SigUp message = Account registered username is" + byUsername.getUserName());
                return "Account registered";
            }
        }
        accountUserEntity.setUid(UUIDUtill.getUUID().longValue());
        String encodeMd5Pwd = MD5HEXUtil.INSTANCE.encodeMd5(sigReq.getPassword());
        accountUserEntity.setUserName(sigReq.getUsername());
        accountUserEntity.setPassword(encodeMd5Pwd);
        if (sigReq.getRole().equals("1"))
        {
            accountUserEntity.setRole("admin");
        }else {
            accountUserEntity.setRole("user");
        }
        if (sigReq.getPermission().equals("1"))
        {
            accountUserEntity.setPermission("vip");
        }else {
            accountUserEntity.setPermission("normal");
        }
        accountUserEntity.setBan(0);
        AccountUserEntity save = accountUserRepo.save(accountUserEntity);

        if (null == save)
        {
            return "fail to register";
        }
        return "registered successfully";
    }

    @Override
    public void operationRecord(String type,String token,String uid) {
        String usertoken = JwtUtil.getUsername(token);
        Integer uuid = UUIDUtill.getUUID();
        AccountUserEntity byUsername = accountUserRepo.findByUsername(usertoken);
        BackgroundOperationRecordEntity backgroundOperationRecordEntity = new BackgroundOperationRecordEntity();
        backgroundOperationRecordEntity.setId(uuid.longValue());
        backgroundOperationRecordEntity.setOperation_type(type);
        backgroundOperationRecordEntity.setOperation_time(DateUtil.getCurrentTimesTamp());
        backgroundOperationRecordEntity.setUid(byUsername.getUid());
        backgroundOperationRecordEntity.setOperator_mailbox(usertoken);
        backgroundOperationRecordRepo.save(backgroundOperationRecordEntity);

    }

    @Override
    public int loginMessage(HttpServletRequest request, String username) {
        AccountLoginEntity accountLogin = new AccountLoginEntity();
        String ipAddr = UserUtil.getIpAddr(request);
        accountLogin.setLoginIP(ipAddr);
        AccountUserEntity byUsername = accountUserRepo.findByUsername(username);
        accountLogin.setAccountLoginId(byUsername.getUid());
        try
        {
            String addresses = AddressUtils.getAddresses(ipAddr, "UTF-8");
            System.out.println("addresses = " + addresses);
            accountLogin.setLoginaddresses(addresses);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.debug("e = " + e.getMessage());
        }
        String currentTimesTamp = DateUtil.getCurrentTimesTamp();
        accountLogin.setLoginTime(currentTimesTamp);
        accountLogin.setFacilityInformation("equipment");
        loginMassageRepo.save(accountLogin);
        return 0;
    }

    @Override
    public ResultMap forgetPwd(String username,String password) {

        AccountUserEntity byUsername = accountUserRepo.findByUsername(username);
        if (!(byUsername.getUserName().equals(username)))
        {
            return resultMap.code(401).message("Without this user");
        }
        String encodeMd5 = MD5HEXUtil.INSTANCE.encodeMd5(password);
        AccountUserEntity accountUserEntity = new AccountUserEntity();
        accountUserEntity.setPassword(encodeMd5);
        accountUserRepo.save(accountUserEntity);
        return resultMap.code(200).message("modify successfully");
    }

    @Override
    public ResultMap unverified() {
        return null;
    }

    //String phone,String account,String applicationTime
    @Override
    public ResultMap authentication() {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","");
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        return resultMap.code(200).message(http);
    }

    @Override
    public ResultMap getPicture(String username) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","user picture");
        bodyStringMap.put("parameter",username);
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        return resultMap.code(200).message(http);
    }

    @Override
    public ResultMap updateAttestation(String username) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","certified");
        bodyStringMap.put("parameter",username);
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        Map maps2 = (Map)JSON.parse(http);
        String status = maps2.get("").toString();
        return resultMap.code(200).message(status);
    }

    @Override
    public ResultMap updateEmail() {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","amend unverified Email");
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        return resultMap.code(200).message(http);
    }

    @Override
    public ResultMap getTheAnswer(String username) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","Answer the questions on record");
        bodyStringMap.put("parameter",username);
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        return resultMap.code(200).message(http);
    }

    @Override
    public ResultMap comparison(String username) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","Compare the record");
        bodyStringMap.put("parameter",username);
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        return resultMap.code(200).message(http);
    }

    @Override
    public ResultMap returnStu(String username) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("msg","return certified");
        bodyStringMap.put("parameter",username);
        String http = Http.http("www.baidu.com", bodyStringMap,"");
        if (Strings.isEmpty(http)){
            return resultMap.code(500).message("No unauthenticated users");
        }
        Map maps2 = (Map)JSON.parse(http);
        String status = maps2.get("").toString();
        return resultMap.code(200).message(status);
    }

    @Override
    public String queryUsers(QueryUsersReq queryKeyword,String authentication) {
        bodyObjMap = new HashMap<>(16);
        bodyObjMap.put("filter",queryKeyword);
        try
        {
            http = Http.httpPost("http://47.103.159.142:8095/api/queryUsers", bodyObjMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl queryUsers e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String queryUserIdInfo(String uid,String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/queryUserIdInfo", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl queryUserIdInfo e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String markConfirmed(String uid, String idNum, String valid, String msg,String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        bodyStringMap.put("idNum",idNum);
        bodyStringMap.put("valid",valid);
        bodyStringMap.put("msg",msg);
        try {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }catch (Exception e){
            logger.error("AccountUserServiceImpl markConfirmed e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String queryUsrSecurityInfo(String uid,String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/queryUsrSecurityInfo", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl queryUsrSecurityInfo e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String queryCurrency(String uid,String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl queryCurrency e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String fiatAssets(String uid,String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl fiatAssets e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String bindBankCard(String uid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl bindBankCard e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String withdrawalAmount(String uid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl withdrawalAmount e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String addressTheCurrency(String uid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl addressTheCurrency e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String addressOfACurrency(String uid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl addressTheCurrency e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String queryTXID(String uid,String authentication) {

        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("uid",uid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String queryCoinCurrencyPrepaidPhone(String Txid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid",Txid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String queryCoinMoneyWithdrawal(String Txid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid",Txid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String buyRecords(String Txid, String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid",Txid);
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("AccountUserServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }


}
