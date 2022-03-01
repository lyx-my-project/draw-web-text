package com.lyx.config;

import cn.hutool.core.util.ReflectUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorConfig
{
    /**
     * Validator对象称为校验器
     * 要获得constraint的较验结果，都得通过这个校验器.
     */
    private static Validator validator;

    /**
     * 初始化Validator对象
     * @return 较验器
     */
    public static synchronized Validator getValidator()
    {
        if (Objects.isNull(validator))
        {
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            return validatorFactory.getValidator();
        }
        return validator;
    }

    /**
     * 校验成员方法参数（不支持静态方法）
     * 如果有通不过校验的，直接抛出异常.
     * @param object          用来获取方法-要校验哪个对象的方法
     * @param methodName      用来获取方法-方法名称
     * @param paramTypes      用来获取方法-方法参数类型
     * @param parameterValues 参数值
     * @param groups          用哪些组的constraint
     * @param <T>             约束的泛型
     */
    public static <T> void validateParameters(T object, String methodName, Class[] paramTypes, Object[] parameterValues, Class[] groups)
    {
        Method method = ReflectUtil.getMethod(object.getClass(), methodName, paramTypes);

        final Set<ConstraintViolation<T>> violationSet;
        if (Objects.isNull(groups) || groups.length==0)
        {
            violationSet = ValidatorConfig.getValidator().forExecutables().validateParameters(object, method, parameterValues);
        }
        else
        {
            violationSet = ValidatorConfig.getValidator().forExecutables().validateParameters(object, method, parameterValues, groups);
        }

        if (!violationSet.isEmpty())
        {
            String errorMsg = violationSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("、"));
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 校验成员方法返回值（不支持静态方法）
     * 如果有通不过校验的，直接抛出异常.
     * @param object      用来获取方法-要校验哪个对象的方法
     * @param methodName  用来获取方法-方法名称
     * @param paramTypes  用来获取方法-方法参数类型
     * @param returnValue 方法的返回值
     * @param groups      用哪些组的constraint
     * @param <T>         约束的泛型
     */
    public static <T> void validateReturnValue(T object, String methodName, Class[] paramTypes, Object returnValue, Class[] groups)
    {
        Method method = ReflectUtil.getMethod(object.getClass(), methodName, paramTypes);

        final Set<ConstraintViolation<T>> violationSet;
        if (Objects.isNull(groups) || groups.length==0)
        {
            violationSet = ValidatorConfig.getValidator().forExecutables().validateReturnValue(object, method, returnValue);
        }
        else
        {
            violationSet = ValidatorConfig.getValidator().forExecutables().validateReturnValue(object, method, returnValue, groups);
        }

        if (!violationSet.isEmpty())
        {
            String errorMsg = violationSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("、"));
            throw new RuntimeException(errorMsg);
        }
    }
}
