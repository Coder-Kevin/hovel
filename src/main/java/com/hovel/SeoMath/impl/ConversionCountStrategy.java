package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

import java.math.BigDecimal;

/**
 * 转化量得分
 */
public class ConversionCountStrategy implements CommodityScoreStrategy {
    @Override
    public BigDecimal compute(BigDecimal param, BigDecimal average) {
        if(param.compareTo(average) >= 0)
            return new BigDecimal(10);
        BigDecimal result = param.divide(average);
        return result.multiply(new BigDecimal(10));
    }
}
