package com.hovel.SeoMath.seokeywords;

public interface KeywordsStrategy {

    /**
     *
     * @param searchRatio 搜索相关性系数
     * @return 关键词排名得分
     */
    double getSeaechScore(double searchRatio);
}
