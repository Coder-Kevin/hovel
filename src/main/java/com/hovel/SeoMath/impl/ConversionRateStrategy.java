package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

/**
 * 转化率得分
 */
public class ConversionRateStrategy implements CommodityScoreStrategy {
    @Override
    public double compute(double param, double average) {
        if(param >= average)
            return 20;
        return param/average*20;
    }
}
