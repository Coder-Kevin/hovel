package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

/**
 * 保修得分
 */
public class GuaranteeStrategy implements CommodityScoreStrategy {
    @Override
    public double compute(double param, double average) {
        return 0;
    }

    public double getScore(boolean provide){
        if(provide)
            return 10;
        return 0;
    }
}
