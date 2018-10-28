package com.hovel.SeoMath.impl;

import com.hovel.SeoMath.CommodityScoreStrategy;

/**
 * 点击量得分
 */
public class ClickCountStrategy implements CommodityScoreStrategy {
    @Override
    public double compute(double param, double average) {
        if(param >= average)
            return 10;
        return param/average*10;
    }
}
