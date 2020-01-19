package com.aohui.btcorg.util;

import jodd.util.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginMassage {

    RedissonClient redisson;

    @Autowired
    public LoginMassage(RedissonClient redisson) {
        this.redisson = redisson;
        this.redismap = redisson.getMap("user");
    }
    public LoginMassage(){}

    private RMap<String, String> redismap;

    public void set( String key,String value)
    {
        if ("".equals(redismap.get(key))){
            redismap.remove(key);
        }
        redismap.put(key,value);
    }

    public boolean tokenLoseEfficacy(String username,String token)
    {
        if ((!(token.equals(redismap.get(username))))
        && !(StringUtil.isEmpty(redismap.get(username))))
        {
            return true;
        }
        return false;
    }
}
