package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

import java.math.BigDecimal;

/**
 * 保修得分
 */
public class GuaranteeStrategy implements CommodityScoreStrategy {
    @Override
    public BigDecimal compute(BigDecimal param, BigDecimal average) {
        return new BigDecimal(0);
    }

    public BigDecimal getScore(boolean provide){
        if(provide)
            return new BigDecimal(10);
        return new BigDecimal(0);
    }
}
