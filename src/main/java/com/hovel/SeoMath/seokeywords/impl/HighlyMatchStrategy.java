package com.hovel.SeoMath.seokeywords.impl;

import com.hovel.SeoMath.seokeywords.KeywordsStrategy;

import java.math.BigDecimal;

/**
 * 高度匹配
 */
public class HighlyMatchStrategy implements KeywordsStrategy {
    @Override
    public BigDecimal getSeaechScore(BigDecimal searchRatio) {
        return searchRatio.multiply(new BigDecimal(0.5));
    }
}
