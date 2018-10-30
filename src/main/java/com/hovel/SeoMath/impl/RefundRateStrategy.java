package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

import java.math.BigDecimal;

/**
 * 退单率得分
 */
public class RefundRateStrategy implements CommodityScoreStrategy {
    @Override
    public BigDecimal compute(BigDecimal param, BigDecimal average) {
        return new BigDecimal(0);
    }

    public BigDecimal getScore(BigDecimal refundRate){
        BigDecimal result = new BigDecimal(1).subtract(refundRate);
        return result.multiply(new BigDecimal(30));
    }
}
