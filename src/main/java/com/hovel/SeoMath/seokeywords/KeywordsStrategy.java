package com.hovel.SeoMath.seokeywords;

import java.math.BigDecimal;

public interface KeywordsStrategy {

    /**
     *
     * @param searchRatio 搜索相关性系数
     * @return 关键词排名得分
     */
    BigDecimal getSeaechScore(BigDecimal searchRatio);
}
