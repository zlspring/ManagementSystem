package com.aohui.btcorg.service.impl;

import com.aohui.btcorg.service.PropertyService;
import com.aohui.btcorg.util.Http;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PropertyServiceImpl implements PropertyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String http = null;

    private static Map<String,String> bodyStringMap = null;

    private static Map<String,Object> bodyObjMap = null;

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
            logger.error("PropertyServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String changesInAssets(String authentication) {

        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String fiatTopUp(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String fiatMoneyWithdrawal(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String fiatChange(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String biddingToBuy(String authentication) {

        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl getTXID e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String auctionHistory(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl auctionHistory e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String involvedInMining(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl involvedInMining e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String digThe(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl digThe e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String theCurrentBuying(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl theCurrentBuying e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

    @Override
    public String aFinancialSettlement(String authentication) {
        bodyStringMap = new HashMap<>(16);
        bodyStringMap.put("Txid","Txid");
        try
        {
            http = Http.http("http://47.103.159.142:8095/api/markConfirmed", bodyStringMap,authentication);
        }
        catch (Exception e)
        {
            logger.error("PropertyServiceImpl aFinancialSettlement e = " +
                    e.getMessage() + "http = " + http);
        }
        if (Strings.isEmpty(http))
        {
            return "No unauthenticated users";
        }
        return http;
    }

}
