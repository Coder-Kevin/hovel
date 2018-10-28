package com.hovel.SeoMath.seokeywords.impl;

import com.hovel.SeoMath.seokeywords.KeywordsStrategy;

/**
 * 部分匹配
 */
public class PartialMatchStrategy implements KeywordsStrategy {
    @Override
    public double getSeaechScore(double searchRatio) {
        return 0.2*searchRatio;
    }
}
