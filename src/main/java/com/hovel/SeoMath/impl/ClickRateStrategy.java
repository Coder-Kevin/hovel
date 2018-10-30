package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

import java.math.BigDecimal;

/**
 * 商品点击率得分
 */
public class ClickRateStrategy implements CommodityScoreStrategy {

    @Override
    public BigDecimal compute(BigDecimal param, BigDecimal average) {
        if(param.compareTo(average) >= 0)
            return new BigDecimal(20);
        BigDecimal result = param.divide(average);
        return result.multiply(new BigDecimal(20));
    }
}
