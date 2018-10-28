package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

/**
 * 退单率得分
 */
public class RefundRateStrategy implements CommodityScoreStrategy {
    @Override
    public double compute(double param, double average) {
        return 0;
    }

    public double getScore(double refundRate){
        return (1-refundRate)*30;
    }
}
