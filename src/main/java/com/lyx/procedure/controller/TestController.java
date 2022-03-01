package com.lyx.procedure.controller;

import cn.hutool.core.lang.Console;
import com.lyx.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @GetMapping("/test")
    public CommonResult<String> test()
    {
        CommonResult<String> stringCommonResult = CommonResult.<String>successDataMsg("这是数据", "你成功了");

        Console.log("打印数据：{}", stringCommonResult);

        return stringCommonResult;
    }
}
