package com.aohui.btcorg.controller;

import com.aohui.btcorg.model.ResultMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/unauthorized")
public class Unauthorized {

    @Resource
    private  ResultMap resultMap;

    @RequestMapping("/Token")
    public ResultMap Token()
    {
        return resultMap.code(1001).message("error token");
    }
}
