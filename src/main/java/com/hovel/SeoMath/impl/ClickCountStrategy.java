package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

import java.math.BigDecimal;

/**
 * 点击量得分
 */
public class ClickCountStrategy implements CommodityScoreStrategy {
    @Override
    public BigDecimal compute(BigDecimal param, BigDecimal average) {
        if(param.compareTo(average) >= 0)
            return new BigDecimal(10);
        BigDecimal result = param.divide(average,4);
        return result.multiply(new BigDecimal(10));
    }
}
