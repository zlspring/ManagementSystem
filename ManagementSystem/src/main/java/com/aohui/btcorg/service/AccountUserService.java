package com.aohui.btcorg.service;

import com.aohui.btcorg.domain.QueryUsersReq;
import com.aohui.btcorg.domain.SigReq;
import com.aohui.btcorg.entity.AccountUserEntity;
import com.aohui.btcorg.model.ResultMap;

import javax.servlet.http.HttpServletRequest;

public interface AccountUserService {
    String SigUp(SigReq sigReq);
    void operationRecord (String type,String token,String uid);
    int loginMessage(HttpServletRequest request,String username);
    ResultMap forgetPwd(String username, String password);
    ResultMap unverified();
    ResultMap authentication();
    ResultMap getPicture(String username);
    ResultMap updateAttestation(String username);
    ResultMap updateEmail();
    ResultMap getTheAnswer(String username);
    ResultMap comparison(String username);
    ResultMap returnStu(String username);
    String queryUsers(QueryUsersReq queryUsersReq,String authentication);
    String queryUserIdInfo(String uid,String authentication);
    String markConfirmed(String uid ,String idNum,String valid,String msg,String authentication);
    String queryUsrSecurityInfo(String uid,String authentication);
    String queryCurrency(String uid,String authentication);
    String fiatAssets(String uid,String authentication);
    String bindBankCard(String uid,String authentication);
    String withdrawalAmount(String uid,String authentication);
    String addressTheCurrency(String uid,String authentication);
    String addressOfACurrency(String uid,String authentication);
    String queryTXID(String uid,String authentication);
    String queryCoinCurrencyPrepaidPhone(String Txid,String authentication);
    String queryCoinMoneyWithdrawal(String Txid,String authentication);
    String buyRecords(String Txid,String authentication);
}
