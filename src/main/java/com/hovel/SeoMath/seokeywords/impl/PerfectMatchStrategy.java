package com.hovel.SeoMath.seokeywords.impl;

import com.hovel.SeoMath.seokeywords.KeywordsStrategy;

import java.math.BigDecimal;

/**
 * 完全匹配
 */
public class PerfectMatchStrategy implements KeywordsStrategy {
    @Override
    public BigDecimal getSeaechScore(BigDecimal searchRatio) {
        return searchRatio;
    }
}
