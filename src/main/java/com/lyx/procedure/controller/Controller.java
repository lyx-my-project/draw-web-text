package com.lyx.procedure.controller;

import cn.hutool.core.lang.Console;
import com.lyx.common.CommonResult;
import com.lyx.procedure.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{
    @Autowired
    @Qualifier("service")
    private Service service;

    @GetMapping("/getText")
    public CommonResult<String> getText(@RequestParam String url)
    {
        return service.getText(url);
    }
}
