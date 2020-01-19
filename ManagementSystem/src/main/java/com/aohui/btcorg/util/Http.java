package com.aohui.btcorg.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Http {
    private static Logger logger = LoggerFactory.getLogger(Http.class);

    public static String http(String url,Map<String,String> bodyMap,String header) {
        RestTemplate restTemplate=new RestTemplate();
        /* 注意：必须 http、https……开头，不然报错，浏览器地址栏不加 http 之类不出错是因为浏览器自动帮你补全了 */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authentication",header);
        /* 这个对象有add()方法，可往请求头存入信息 */
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String body = JSON.toJSONString(bodyMap);
        /* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*/
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        /* body是Http消息体例如json串 */
        ResponseEntity<String> exchange = null;
        try {
            exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        }catch (Exception e){
            logger.error("Http http e = " + e);
        }

        return exchange.getBody();
    }

    public static String httpPost(String url,Map<String,Object> bodyMap,String header) {
        RestTemplate restTemplate=new RestTemplate();
        /* 注意：必须 http、https……开头，不然报错，浏览器地址栏不加 http 之类不出错是因为浏览器自动帮你补全了 */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authentication",header);
        /* 这个对象有add()方法，可往请求头存入信息 */
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String body = JSON.toJSONString(bodyMap);
        /* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*/
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        /* body是Http消息体例如json串 */
        ResponseEntity<String> exchange = null;
        try {
            exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        }catch (Exception e){
            logger.error("Http http e = " + e);
        }
        return exchange.getBody();
    }
}
