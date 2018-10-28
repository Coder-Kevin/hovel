package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

/**
 * 商品点击率得分
 */
public class ClickRateStrategy implements CommodityScoreStrategy {

    @Override
    public double compute(double param, double average) {
        if(param >= average)
            return 20;
        return param/average*20;
    }
}
