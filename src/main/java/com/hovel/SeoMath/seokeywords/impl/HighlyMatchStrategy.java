package com.hovel.SeoMath.seokeywords.impl;

import com.hovel.SeoMath.seokeywords.KeywordsStrategy;

/**
 * 高度匹配
 */
public class HighlyMatchStrategy implements KeywordsStrategy {
    @Override
    public double getSeaechScore(double searchRatio) {
        return 0.5*searchRatio;
    }
}
