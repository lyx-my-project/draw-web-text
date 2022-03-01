package com.lyx;

import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;

@SpringBootTest
class AppTests
{
    @Test
    public void test1()
    {
        Console.log("打印数据：测试");
    }
}
