package com.hovel.SeoMath;

public interface CommodityScoreStrategy {
    /**
     *
     * @param param 参数
     * @param average 平均值
     * @return 得分
     */
    double compute(double param, double average);
}
