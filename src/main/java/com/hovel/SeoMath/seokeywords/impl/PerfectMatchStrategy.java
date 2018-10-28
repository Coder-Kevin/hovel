package com.hovel.SeoMath.seokeywords.impl;

import com.hovel.SeoMath.seokeywords.KeywordsStrategy;

/**
 * 完全匹配
 */
public class PerfectMatchStrategy implements KeywordsStrategy {
    @Override
    public double getSeaechScore(double searchRatio) {
        return searchRatio;
    }
}
