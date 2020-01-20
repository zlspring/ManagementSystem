package com.aohui.btcorg.service;

public interface PropertyService {

    //货币买入记录
    String buyRecords(String Txid,String authentication);
    //币币资产变更记录
    String changesInAssets(String authentication);
    //法币充值
    String fiatTopUp(String authentication);
    //法币提现
    String fiatMoneyWithdrawal(String authentication);
    //法币资产变动记录
    String fiatChange(String authentication);

    String biddingToBuy(String authentication);
    //拍卖历史记录
    String auctionHistory(String authentication);
    //挖矿参与
    String involvedInMining(String authentication);
    //挖矿所得
    String digThe(String authentication);
    //当前买入
    String theCurrentBuying(String authentication);
    //理财交割
    String aFinancialSettlement(String authentication);

}
